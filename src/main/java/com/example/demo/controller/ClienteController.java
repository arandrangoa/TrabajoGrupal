package com.example.demo.controller;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Cliente;
import com.example.demo.service.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	private static final Logger LOG = Logger.getLogger(ReservaController.class);

	@PostMapping("/registrar")
	public String registrarCliente(Cliente cliente) {
		try {
			
			this.clienteService.guardarCliente(cliente);
			LOG.info("Se registro un CLiente como Cliente");
        	
			return "redirect:/paginas/cliente";
		} catch (Exception e) {
			
        	LOG.error("No se registro el cliente");
        	
			return "redirect:/clientes/registroCliente";
		}
	
	}

	@GetMapping("/registroCliente")
	public String registo(Cliente c, Model modelo) {
		modelo.addAttribute("cliente", c);
		LOG.info("Direcciona a vistaClienteNuevo");
        
		return "vistaClienteNuevo";
	}

}
