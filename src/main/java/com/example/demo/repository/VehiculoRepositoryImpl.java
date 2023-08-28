package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Vehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo seleccionarPorId(Integer id) {
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Vehiculo vehiculo) {
		this.entityManager.merge(vehiculo);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		this.entityManager.merge(this.seleccionarPorId(id));
	}

	@Override
	public Vehiculo seleccionarPorPlaca(String placa) {
		TypedQuery<Vehiculo> query =this.entityManager.createQuery("Select v From Vehiculo v Where v.placa=:DatoPlaca", Vehiculo.class);
		query.setParameter("DatoPlaca", placa);
		return query.getSingleResult();
	}

	@Override
	public List<Vehiculo> seleccionarPorMarcaModelo(String marca, String modelo) {
		TypedQuery<Vehiculo> query = this.entityManager.createQuery("Select v From Vehiculo v Where v.marca=:DatoMarca AND v.modelo=:DatoModelo", Vehiculo.class);
		query.setParameter("DatoMarca", marca);
		query.setParameter("DatoModelo", modelo);
		return  query.getResultList();
	}

}