package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.modelo.dto.ClienteVipDTO;
import com.example.demo.repository.modelo.dto.ReservaDTO;
import com.example.demo.repository.modelo.dto.VehiculoVipDTO;
import com.example.demo.service.IReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
	
	@Autowired
	private IReservaService iReservaService;
	
	@PostMapping("/guardar")
	public String reservarVehiculo(@RequestParam("placa")String placa,
			@RequestParam("cedula")String cedula,
			@RequestParam("fechaInicio")LocalDate fechaInicio,
			@RequestParam("fechaFin")LocalDate fechaFin, Model model) {
		String aux="";
		
		if(this.iReservaService.verificarDisponibilidad(placa,fechaInicio,fechaFin)) {
			BigDecimal totalAPagar = this.iReservaService.valorTotalAPagar(placa,fechaInicio, fechaFin);
	        model.addAttribute("totalAPagar", totalAPagar);
	     // Almacenar los datos en el modelo para la vista
	        model.addAttribute("placa", placa);
	        model.addAttribute("cedula", cedula);
	        model.addAttribute("fechaInicio", fechaInicio);
	        model.addAttribute("fechaFin", fechaFin);
	        
			aux="vistaRegistrarReserva";
		}else {
			LocalDate fechaDisponible =this.iReservaService.obtenerFechaDisponible(placa, fechaInicio, fechaFin);
			 model.addAttribute("fecha_inicio_seleccionada", fechaInicio.toString());
		     model.addAttribute("fecha_fin_seleccionada", fechaFin.toString());
		     model.addAttribute("fecha_disponible", fechaDisponible.toString());
			aux="vistaVehiculoIndisponible";
		}
		return aux;
	}
	@PostMapping("/registrar")
	public String registrarReserva(@RequestParam("placa") String placa,
	        @RequestParam("cedula") String cedula,
	        @RequestParam("fechaInicio") LocalDate fechaInicio,
	        @RequestParam("fechaFin") LocalDate fechaFin,
	        @RequestParam("tarjetaCredito") String tarjetaCredito) {
	  
		this.iReservaService.reservarVehiculo(placa, cedula, fechaInicio, fechaFin, tarjetaCredito);
	    return "redirect:/paginas/cliente";
	}
	@GetMapping("/reporteReserva")
	public String reporteReservas(@RequestParam("fechaInicio")LocalDate fechaInicio,
			@RequestParam("fechaFin") LocalDate fechaFin, Model modelo) {
		List<ReservaDTO> lista= this.iReservaService.reporteReservas(fechaInicio, fechaFin);
		modelo.addAttribute("reservasDTO",lista);
		return "vistaListaReservas";
	}
	@GetMapping("/reporteClientesVip")
	public String reporteClientesVip(Model modelo) {
		List<ClienteVipDTO> lista=this.iReservaService.reporteClientesVIP();
		modelo.addAttribute("clientesVipDTO",lista);
		return "vistaListaClientesVip";
	}
	@GetMapping("/reporteVehiculosVip")
	public String reporteVehiculosVip(@RequestParam("mesSeleccionado") int mesSeleccionado,
	        @RequestParam("anioSeleccionado") int anioSeleccionado, Model modelo) {
	    List<VehiculoVipDTO> lista = this.iReservaService.reporteVehiculosVIP(mesSeleccionado, anioSeleccionado);
	    modelo.addAttribute("vehiculosVipDTO", lista);
	    return "vistaListaVehiculosVip";
	}

	
}
