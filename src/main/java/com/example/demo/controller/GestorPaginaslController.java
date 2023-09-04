package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class GestorPaginaslController {

	// http://localhost:8080/concesionario/paginas/principal
	private static final Logger LOG = Logger.getLogger(ReservaController.class);
	
	@GetMapping("/paginas/principal")
	public String paginaPrincipal() {
		LOG.info("Direcciona a la vistaPaginaPrincipal");
     
		return "vistaPaginaPrincipal";
	}
	
	@GetMapping("/paginas/cliente")
	public String paginaCliente() {
		LOG.info("Direcciona a la vistaClientePrincipal");
       
		return "vistaClientePrincipal";
	}
	
	@GetMapping("/paginas/empleado")
	public String paginaEmpleado() {
		LOG.info("Redirecciona al metodo /empleados/principal");
     
		return "redirect:../empleados/principal";
	}
	
	@GetMapping("/paginas/reporte")
	public String paginaReporte() {
		LOG.info("Direcciona a la vistaReportePrincipal");
        
		return "vistaReportePrincipal";
	}

}
