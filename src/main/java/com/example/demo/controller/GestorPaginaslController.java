package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paginas")
public class GestorPaginaslController {

	// http://localhost:8080/concesionario/paginas/principal
	
	@GetMapping("/principal")
	public String paginaPrincipal() {
		return "vistaPaginaPrincipal";
	}
	
	@GetMapping("/cliente")
	public String paginaCliente() {
		return "vistaClientePrincipal";
	}
	
	@GetMapping("/empleado")
	public String paginaEmpleado() {
		return "vInicioE";
	}
	
	@GetMapping("/reporte")
	public String paginaReporte() {
		return "vistaReportePrincipal";
	}

}
