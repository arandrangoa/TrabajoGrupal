package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.repository.modelo.dto.ClienteVipDTO;
import com.example.demo.repository.modelo.dto.ReservaDTO;
import com.example.demo.repository.modelo.dto.VehiculoVipDTO;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private IReservaService iReservaService;
	@Autowired
	IVehiculoService iVehiculoService;

	private boolean disponibilidad;
	private static final Logger LOG = Logger.getLogger(ReservaController.class);

	@RequestMapping("/reservar")
	public String reservar(@RequestParam("numeroReserva") String numeroReserva, Model modelo) {
		Reserva r = this.iReservaService.cambiarEstadoReserva(numeroReserva);
		modelo.addAttribute("reserva", r);
		LOG.info("Cambia el estado de la reserva a partir de un numero de Reserva y el estado del vehiculo");

		return "redirect:../vehiculos/buscarPorPlaca";
	}

	@PostMapping("/guardar")
	public String reservarVehiculo(@RequestParam("placa") String placa, @RequestParam("cedula") String cedula,
			@RequestParam("fechaInicio") LocalDate fechaInicio, @RequestParam("fechaFin") LocalDate fechaFin,
			Model model) {
		String aux = "";

		if (this.iReservaService.verificarDisponibilidad(placa, fechaInicio, fechaFin)) {
			BigDecimal totalAPagar = this.iReservaService.valorTotalAPagar(placa, fechaInicio, fechaFin);
			model.addAttribute("totalAPagar", totalAPagar);
			// Almacenar los datos en el modelo para la vista
			model.addAttribute("placa", placa);
			model.addAttribute("cedula", cedula);
			model.addAttribute("fechaInicio", fechaInicio);
			model.addAttribute("fechaFin", fechaFin);
			LOG.info("Direcciona a la vistaRegistrarReserva, disponibilidad es true");

			aux = "vistaRegistrarReserva";
		} else {
			LocalDate fechaDisponible = this.iReservaService.obtenerFechaDisponible(placa, fechaInicio, fechaFin);
			model.addAttribute("fecha_inicio_seleccionada", fechaInicio.toString());
			model.addAttribute("fecha_fin_seleccionada", fechaFin.toString());
			model.addAttribute("fecha_disponible", fechaDisponible.toString());
			LOG.info("Direcciona a la vistaVehiculoIndisponible, disponibilidad es false");

			aux = "vistaVehiculoIndisponible";
		}
		return aux;
	}

	@PostMapping("/registrar")
	public String registrarReserva(@RequestParam("placa") String placa, @RequestParam("cedula") String cedula,
			@RequestParam("fechaInicio") LocalDate fechaInicio, @RequestParam("fechaFin") LocalDate fechaFin,
			@RequestParam("tarjetaCredito") String tarjetaCredito, Model model) {
		String numeroReserva = this.iReservaService.reservarVehiculo(placa, cedula, fechaInicio, fechaFin,
				tarjetaCredito);
		model.addAttribute("numeroReserva", numeroReserva);
		LOG.info("Registra la reserva a partir de placa,cedula,fecha inicio,fecha fin y tarjeta Credito");

		return "vistaConfimarReserva";

	}

	@GetMapping("/reporteReserva")
	public String reporteReservas(@RequestParam("fechaInicio") LocalDate fechaInicio,
			@RequestParam("fechaFin") LocalDate fechaFin, Model modelo) {
		List<ReservaDTO> lista = this.iReservaService.reporteReservas(fechaInicio, fechaFin);
		modelo.addAttribute("reservasDTO", lista);
		LOG.info("Direcciona a la vistaListaReservas a partir de fecha inicio, fecha fin");

		return "vistaListaReservas";
	}

	@GetMapping("/reporteClientesVip")
	public String reporteClientesVip(Model modelo) {
		List<ClienteVipDTO> lista = this.iReservaService.reporteClientesVIP();
		modelo.addAttribute("clientesVipDTO", lista);
		LOG.info("Direcciona a la vistaListaClientesVip a partir de una Lista de ClienteVipDTO");

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
