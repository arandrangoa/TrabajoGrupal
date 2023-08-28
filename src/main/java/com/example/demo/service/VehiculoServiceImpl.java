package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IVehiculoRepository;
import com.example.demo.repository.modelo.Vehiculo;

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
	public List<Vehiculo> buscarPorMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.seleccionarPorMarcaModelo(marca, modelo);
	}

}