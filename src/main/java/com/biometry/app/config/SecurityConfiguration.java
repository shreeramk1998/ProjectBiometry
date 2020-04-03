package com.biometry.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.biometry.app.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/subAdmin/**").hasRole("SUBADMIN")
		.antMatchers("/teacher/**").hasRole("TEACHER")
		.antMatchers("/student/**").hasRole("STUDENT")
		.antMatchers("/").permitAll()
		.and()
		.formLogin()
//		.loginProcessingUrl("/signin")
		.loginPage("/login").successHandler(new CustomSuccessRedirectionHandler()).permitAll()
	//		.failureHandler
	//		((req,res,exp)->{  // Failure handler invoked after authentication failure
	//	         String errMsg="";
	//	         if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
	//	            errMsg="Invalid username or password.";
	//	         }else{
	//	            errMsg="Unknown error - "+exp.getMessage();
	//	         }
	//	         req.getSession().setAttribute("message", errMsg);
	//	         res.sendRedirect("/login");}
	//		)
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.permitAll()
		.and().exceptionHandling();
	}
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	                .ignoring()
	                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	    }
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsServiceImpl);

        return daoAuthenticationProvider;
    }
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
