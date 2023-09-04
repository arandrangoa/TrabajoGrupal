package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.repository.modelo.dto.ClienteVipDTO;
import com.example.demo.repository.modelo.dto.ReservaDTO;
import com.example.demo.repository.modelo.dto.VehiculoVipDTO;


public interface IReservaService {
	
	public void reservarVehiculo(String placa,String cedula,LocalDate fechaInicio,LocalDate fechaFin,String numeroTarjeta);
	public boolean verificarDisponibilidad(String placa,LocalDate fechaInicio,LocalDate fechaFin);
	public LocalDate obtenerFechaDisponible(String placa, LocalDate fechaInicio, LocalDate fechaFin);
	public BigDecimal valorTotalAPagar(String placa, LocalDate fechaInicio, LocalDate fechaFin);
	
	public List<ReservaDTO> reporteReservas(LocalDate fechaInicio, LocalDate fechaFin);
	public List<ClienteVipDTO> reporteClientesVIP();
	public List<VehiculoVipDTO> reporteVehiculosVIP(int mesSeleccionado,int anioSeleccionado);
	

}
