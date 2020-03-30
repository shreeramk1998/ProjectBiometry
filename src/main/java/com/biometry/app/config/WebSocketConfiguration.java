package com.biometry.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;



@Configuration
@EnableWebSocket
//@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements 
//WebSocketMessageBrokerConfigurer 
WebSocketConfigurer 
{
//	
//	@Override
//	  public void configureMessageBroker(MessageBrokerRegistry config) {
//		/*
//		 * enable a simple memory-based message broker to carry the greeting messages 
//		 * back to the client on destinations prefixed with
//		 */
//		config.enableSimpleBroker("/topic");
//		
//		/*
//		 *prefix for messages that are bound for methods annotated with @MessageMapping
//		 *eg - /app/hello is mapped in controller 
//		 */
//	    config.setApplicationDestinationPrefixes("/app");
//	  }
//
//	  @Override
//	  public void registerStompEndpoints(StompEndpointRegistry registry) {
//	    registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
//	  }
//
	
	
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxBinaryMessageBufferSize(1024000);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(), "/socket").setAllowedOrigins("*");
    }
}