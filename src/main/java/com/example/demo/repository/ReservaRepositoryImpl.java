package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Reserva;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva seleccionarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Reserva.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Reserva reserva) {
		this.entityManager.merge(reserva);
		
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.seleccionarPorId(id));
	}

	@Override
	public List<Reserva> seleccionarListaPorPlacaV(String placa) {
		TypedQuery<Reserva> query= this.entityManager.createQuery("Select r From Reserva r Feach Join Vehiculo v Where v.placa=:DatoPlaca",Reserva.class);
		query.setParameter("DatoPlaca", query);
		return query.getResultList();
	}

}