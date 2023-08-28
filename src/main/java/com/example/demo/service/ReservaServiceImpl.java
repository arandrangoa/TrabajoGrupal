package com.example.demo.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IReservaRepository;
import com.example.demo.repository.IVehiculoRepository;
import com.example.demo.repository.iClienteRepository;
import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.dto.ReservaDTO;

@Service
public class ReservaServiceImpl implements IReservaService{
	
	@Autowired
	private iClienteRepository clienteRepository;
	@Autowired
	private IVehiculoRepository iVehiculoRepository;
	
	@Autowired
	private IReservaRepository iReservaRepository;

	@Override
	public Boolean reservarVehiculo(ReservaDTO reservaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String verificarDisponibilidad(ReservaDTO reservaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	

}
