package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/cliente/buscar")
	public String buscarPorCedula(Cliente cliente) {
		return "vistaBuscarCliente";
	}
	
	@GetMapping("/cliente/encontrado/{cedula}")
	public String clienteBuscado(@PathVariable("cedula") String cedula,Model modelo) {
		Cliente cliente=this.clienteService.buscarPorCedula(cedula);
		modelo.addAttribute("cliente", cliente);
		return "vistaCliente";
	}
	
	@GetMapping("/listaClientes")
	public String listaClientes(Model modelo) {
		List<Cliente> clientes = this.clienteService.seleccionarTodos();
		modelo.addAttribute("clientes", clientes);
		return "vistaListaClientes";
	}
	
	
	
	

}
