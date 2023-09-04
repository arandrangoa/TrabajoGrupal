package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Cliente;

public interface IClienteRepository {
	public void insertar(Cliente cliente);
	public Cliente seleccionarPorId(Integer id);
	public void actualizar(Cliente cliente);
	public void eliminar(Integer id);
	
	public Cliente seleccionarPorCedula(String cedula);
    public List<Cliente> seleccionarTodos();
	public List<Cliente> seleccionarCliente(String cedula);
    
}
