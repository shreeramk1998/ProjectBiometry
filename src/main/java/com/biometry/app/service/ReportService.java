package com.biometry.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.biometry.app.entity.AttendanceMaster;
import com.biometry.app.repository.AttendanceMasterRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
	AttendanceMasterRepository attendanceMasterrepo;
	public boolean exportReport(String filename,Map<String, Object> parameter,List list) throws JRException, FileNotFoundException {
		File file =  ResourceUtils.getFile("classpath:AttendanceReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		System.out.println(parameter);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter,dataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, System.getenv("BIOMETRY_HOME")+"\\"+filename+".pdf");
		return true;
	}
	
}
