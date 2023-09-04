package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Cliente;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.IClienteService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private IClienteService clienteService;

    private static final Logger LOG = Logger.getLogger(ReservaController.class);
    //http://localhost:8080/concesionario/empleados/principal
    @GetMapping("/principal")
	public String principal(Cliente cliente,Vehiculo vehiculo, Model modelo) {
        List<Cliente> lista = this.clienteService.buscarTodos();
		modelo.addAttribute("clientes", lista);
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("vehiculo", vehiculo);
        LOG.info("Direcciona a VistaEmpleadoPrincipal");
        
		return "vistaEmpleadoPrincipal";
	}

    @PostMapping("/registrar")
    public String registrarCliente(Cliente cliente){
        try {
			LOG.info("Registra un Cliente como Empleado");
        
			this.clienteService.guardarEmpleado(cliente);
			return "redirect:/empleados/principal";
		} catch (Exception e) {
         
            LOG.error("No Registro al Cliente como Empleado");
        
			return "redirect:../paginas/principal";
		}
    }
   
    @GetMapping("/detalle/{idCliente}")
    public String detalle(@PathVariable("idCliente") Integer id, Model modelo){
        Cliente cliente = this.clienteService.buscarPorId(id);
        modelo.addAttribute("cliente",cliente);
        LOG.info("Direcciona a la vistaEmpleadoDetalle");
        
        return "vistaEmpleadoDetalle";
    }
}
