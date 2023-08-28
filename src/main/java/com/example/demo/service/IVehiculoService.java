package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Vehiculo;

public interface IVehiculoService {
	public void guardar(Vehiculo vehiculo);
	public Vehiculo buscarPorId(Integer id);
	public void actualizar(Vehiculo vehiculo);
	public void borrar(Integer id);
	public List<Vehiculo> buscarPorMarcaModelo(String marca, String modelo);
	
}