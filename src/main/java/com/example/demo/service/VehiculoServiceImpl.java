package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IVehiculoRepository;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.repository.modelo.dto.VehiculoDTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class VehiculoServiceImpl implements IVehiculoService{

	@Autowired
	private IVehiculoRepository iVehiculoRepository;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void guardar(Vehiculo vehiculo) {
		this.iVehiculoRepository.insertar(vehiculo);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Vehiculo buscarPorId(Integer id) {
		return this.iVehiculoRepository.seleccionarPorId(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Vehiculo vehiculo) {
		this.iVehiculoRepository.actualizar(vehiculo);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void borrar(Integer id) {
		this.iVehiculoRepository.eliminar(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public List<Vehiculo> buscarPorMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.seleccionarPorMarcaModelo(marca, modelo);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Vehiculo buscarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.seleccionarPorPlaca(placa);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public VehiculoDTO buscarDto(String noReserva) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.seleccionarDTO(noReserva);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public List<Vehiculo> buscarDisponibles() {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.seleccionarDisponibles();
	}

}