package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.modelo.Cliente;
import com.example.demo.service.IClienteService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired
	private IClienteService clienteService;
	
	// CLIENTE
	// registro cliente
	@GetMapping("/cliente/registro")
	public String registraCliente(Cliente cliente) {
		return "vistaNuevoClienteEmpleado";
	}

	@PostMapping("/insertar/cliente")
	public String insertarCliente(Cliente cliente) {
		this.clienteService.guardarEmpleado(cliente);
		return "redirect:/empleados/cliente/registro";
	}
	
	@RequestMapping("/cliente/buscar")
	public String buscarPorCedula(Model model) {
	    model.addAttribute("cliente", new Cliente());
	    return "vistaBuscarCliente";
	}
	
	@GetMapping("/cliente/encontrado")
	public String clienteBuscado(@RequestParam("cedula") String cedula, Model modelo) {
	    List<Cliente> clientes = this.clienteService.buscarCliente(cedula);
	    modelo.addAttribute("clientes", clientes);
	    return "vistaCliente";
	}
	
	@GetMapping("/listaClientes")
	public String listaClientes(Model modelo) {
		List<Cliente> clientes = this.clienteService.buscarTodos();
		modelo.addAttribute("clientes", clientes);
		return "vistaListaClientes";
	}
	
	

}
