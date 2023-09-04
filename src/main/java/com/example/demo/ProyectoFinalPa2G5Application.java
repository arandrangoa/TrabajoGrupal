package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.modelo.Vehiculo;

import com.example.demo.service.IVehiculoService;

@SpringBootApplication
public class ProyectoFinalPa2G5Application implements CommandLineRunner{

	
	@Autowired
	IVehiculoService iVehiculoService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2G5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	
	}

}
