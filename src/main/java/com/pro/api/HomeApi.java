package com.pro.api;

import java.io.InputStream;
import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RestController
@RequestMapping("/api")
public class HomeApi {

	@GetMapping
	public ResponseEntity<String> index() {
		String index = "----- Springboot Jasper Reports | Index -----";
		return ResponseEntity.ok().body(index);
	}

	@GetMapping("/relatorio")
	public ResponseEntity<byte[]> gerarRelatorio() throws Exception {
		InputStream input = getClass().getResourceAsStream("/relatorio.jrxml");
		JasperReport report = JasperCompileManager.compileReport(input);
		JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), new JREmptyDataSource());
		byte[] pdf = JasperExportManager.exportReportToPdf(print);

		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=relatorio.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(pdf);
	}
}