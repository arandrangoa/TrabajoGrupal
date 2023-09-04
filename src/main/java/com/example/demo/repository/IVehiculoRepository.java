package com.example.demo.repository;


import java.util.List;

import com.example.demo.repository.modelo.Vehiculo;


public interface IVehiculoRepository {
	public void insertar(Vehiculo vehiculo);
	public Vehiculo seleccionarPorId(Integer id);
	public void actualizar(Vehiculo vehiculo);
	public void eliminar(Integer id);
	
	public Vehiculo seleccionarPorPlaca(String placa);
	public List<Vehiculo> seleccionarPorMarcaModelo(String marca, String modelo);
}