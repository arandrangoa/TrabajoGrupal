package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.modelo.Cliente;
import com.example.demo.service.IClienteService;

@SpringBootApplication
public class ProyectoFinalPa2G5Application implements CommandLineRunner{

	
	@Autowired
	private IClienteService clienteService;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2G5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Cliente c=this.clienteService.buscarPorCedula("1727193847");
//		System.out.println(c);
	
	}

}
