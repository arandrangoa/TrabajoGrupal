package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;

import com.example.demo.repository.modelo.dto.VehiculoDTO;

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

	@Override
	public VehiculoDTO seleccionarDTO(String noReserva) {
		TypedQuery<Reserva> query= this.entityManager.createQuery("Select r From Reserva r Where r.numeroReserva=:DatoReserva",Reserva.class);
		query.setParameter("DatoReserva", noReserva);
		Reserva r =query.getSingleResult();
		Vehiculo v = r.getVehiculo();
		VehiculoDTO vd= new VehiculoDTO();
		vd.setCedulaCliente(r.getCliente().getCedula());
		vd.setEstado(v.getEstado());
		vd.setFechaInicio(r.getFechaInicio());
		vd.setFechaFin(r.getFechaFin());
		vd.setModelo(v.getModelo());
		vd.setPlaca(v.getPlaca());
		vd.setNoReserva(noReserva);
		vd.setMarca(v.getMarca());
		vd.setValorPorDia(v.getValorPorDia());
		return vd;
	}

	@Override
	public List<Vehiculo> seleccionarDisponibles() {
		TypedQuery<Vehiculo> query= this.entityManager.createQuery("Select v From Vehiculo v Where v.estado='D'",Vehiculo.class);
		return query.getResultList();
	}

}