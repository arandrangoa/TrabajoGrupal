package com.example.demo.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.repository.modelo.dto.VehiculoDTO;


@Controller
@RequestMapping("/vehiculos")

public class VehiculoController {
	
	@Autowired
    private IVehiculoService iVehiculoService;
    @Autowired
    private IReservaService iReservaService;

    private static final Logger LOG = Logger.getLogger(ReservaController.class);
  
    
    //http://localhost:8080/concesionario/vehiculos/buscarPorPlaca/?
    @GetMapping("/buscarPorPlaca")
    public String buscaPorPlaca(@RequestParam("placa") String placa, Model modelo){
        Vehiculo buscado = this.iVehiculoService.buscarPorPlaca(placa);
       
        modelo.addAttribute("vehiculo",buscado);
        LOG.info("Direcciona a la vista vistaPorPlaca a partir de una placa");
    
        return "vistaPorPlaca";
    }
    @GetMapping("/buscarPorReserva")
    public String buscarPorReserva(@RequestParam("noReserva") String noReserva,Model modelo){
        VehiculoDTO vDto= this.iVehiculoService.buscarDto(noReserva);
        modelo.addAttribute("vehiculoDTO", vDto);
        LOG.info("Direcciona a la vistaPorReserva a partir de numero de reserva");
       
        return "vistaPorReserva";
    }

    @GetMapping("/disponibles")
    public String buscarDisponibles(Model modelo){
        List<Vehiculo> disponibles = this.iVehiculoService.buscarDisponibles();
        modelo.addAttribute("vehiculos", disponibles);
        LOG.info("Direcciona a la vistaVehiculosDisponibles, envia una Lista de Vehiculos");
        
        return "vistaVehiculosDisponibles";
    }
    //http://localhost:8080/concesionario/vehiculos/buscar
	@GetMapping("/buscar")
    public String buscarVehiculos(@RequestParam("marca") String marca,
                                  @RequestParam("modelo") String modelo,
                                  Model model) {
        List<Vehiculo> vehiculosFiltrados = this.iVehiculoService.buscarPorMarcaModelo(marca, modelo); 
        model.addAttribute("vehiculos", vehiculosFiltrados);
        LOG.info("Direcciona a la vistaListaVehiculos a partir de marca y modelo de vehiculo");
       
        return "vistaListaVehiculos";
    }
    @GetMapping("/buscarSinReserva")
    public String buscarVehiculosSinReserva(@RequestParam("marca") String marca,
                                  @RequestParam("modelo") String modelo,
                                  Model model) {
        List<Vehiculo> vehiculosFiltrados = this.iVehiculoService.buscarPorMarcaModelo(marca, modelo); 
        model.addAttribute("vehiculos", vehiculosFiltrados);
        LOG.info("Busca los vehiculos sin reserva a partir de marca y modelo");
        
        return "vistaVehiculosDisponibles";
    }

    @PutMapping("/retirar/{noReserva}")
    public String retirarReservado(@PathVariable("noReserva") String numeroReserva,Model modelo){
        Reserva r = this.iReservaService.buscarPorReserva(numeroReserva); 
        Vehiculo v = r.getVehiculo();
        r.setEstado("E");
        v.setEstado("I");
        this.iVehiculoService.actualizar(v);
        this.iReservaService.actualizar(r);
        LOG.info("Cambia el estado de reserva y el estado de vehiculo");
        
        return "success.html";
    }
    @PostMapping("/registrar")
	public String registrarCliente(Vehiculo vehiculo) {
		try {
			this.iVehiculoService.guardar(vehiculo);
            LOG.info("Redirecciona a /paginas/empleado luego de registrar un Cliente");
        
			return "redirect:/paginas/empleado";
		} catch (Exception e) {
         
            LOG.error("Redirecciona a /vehiculos/registroVehiculo registro de Cliente fallida");
    
			return "redirect:/vehiculos/registroVehiculo";
		}
	}
	
}
