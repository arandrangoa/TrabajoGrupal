package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class ClienteRepositoryImpl implements iClienteRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Cliente cliente) {
		this.entityManager.persist(cliente);
		
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente seleccionarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Cliente cliente) {
		this.entityManager.merge(cliente);
		
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		this.entityManager.remove(this.seleccionarPorId(id));
		
	}

	@Override
	public Cliente seleccionarPorCedula(String cedula) {
			// TODO Auto-generated method stub
			TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cedula =:datoCedula", Cliente.class);
			myQuery.setParameter("datoCedula", cedula);
			
			return myQuery.getSingleResult();
			
		}

	@Override
	public List<Cliente> seleccionarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> myQuery= this.entityManager.createQuery("Select c from Cliente c",Cliente.class);
		return myQuery.getResultList();
	}

}
