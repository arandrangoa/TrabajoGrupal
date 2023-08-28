package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Reserva;

public interface IReservaRepository {
	public void insertar(Reserva reserva);
	public Reserva seleccionarPorId(Integer id);
	public void actualizar(Reserva reserva);
	public void eliminar(Integer id);
	public List<Reserva> seleccionarListaPorPlacaV(String placa);
	
}
