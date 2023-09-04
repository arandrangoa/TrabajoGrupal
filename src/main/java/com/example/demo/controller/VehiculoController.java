package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculos")

public class VehiculoController {
	
	@Autowired
    private IVehiculoService iVehiculoService;
    
	@GetMapping("/buscar")
    public String buscarVehiculos(@RequestParam("marca") String marca,
                                  @RequestParam("modelo") String modelo,
                                  Model model) {
        List<Vehiculo> vehiculosFiltrados = this.iVehiculoService.buscarPorMarcaModelo(marca, modelo); 
        model.addAttribute("vehiculos", vehiculosFiltrados);
        
        return "vistaListaVehiculos";
    }

}
