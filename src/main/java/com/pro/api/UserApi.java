package com.pro.api;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.model.User;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/users")
public class UserApi {

	@GetMapping
	public ResponseEntity<byte[]> gerarRelatorioUsuarios() throws Exception {

		List<User> usuarios = List.of(
				new User(1L, "João", "joao@email.com"), 
				new User(2L, "Maria", "maria@email.com"),
				new User(3L, "Pedro", "pedro@email.com"));

		InputStream input = getClass().getResourceAsStream("/users.jrxml");

		JasperReport report = JasperCompileManager.compileReport(input);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usuarios);

		JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

		byte[] pdf = JasperExportManager.exportReportToPdf(print);

		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=users.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(pdf);
	}
}