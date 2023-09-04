package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.modelo.Cliente;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class ClienteServiceImpl implements IClienteService{

	
	@Autowired
	private IClienteRepository clienteRepository;
	

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Cliente buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.clienteRepository.seleccionarPorId(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Cliente cliente) {
		this.clienteRepository.actualizar(cliente);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void borrar(Integer id) {
		this.clienteRepository.eliminar(id);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void guardarCliente(Cliente cliente) {
		cliente.setRegistro('C');
		this.clienteRepository.insertar(cliente);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void guardarEmpleado(Cliente cliente) {
		cliente.setRegistro('E');
		this.clienteRepository.insertar(cliente);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public List<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return this.clienteRepository.seleccionarTodos();
	}

}
