package com.example.demo.service;

import java.time.LocalDate;

import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.dto.ReservaDTO;

public interface IReservaService {
	
	public Boolean reservarVehiculo(ReservaDTO reservaDTO);
	public String verificarDisponibilidad(ReservaDTO reservaDTO);
	

}
