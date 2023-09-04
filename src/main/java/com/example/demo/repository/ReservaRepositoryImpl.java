package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.dto.ClienteVipDTO;
import com.example.demo.repository.modelo.dto.ReservaDTO;
import com.example.demo.repository.modelo.dto.VehiculoVipDTO;

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
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> seleccionarListaPorPlacaV(String placa) {
		TypedQuery<Reserva> query= this.entityManager.createQuery("SELECT r FROM Reserva r JOIN FETCH r.vehiculo v WHERE v.placa =:datoPlaca"
				,Reserva.class);
		query.setParameter("datoPlaca", placa);
		return query.getResultList();
	}

	@Override
	public Reserva seleccionarPorNoReserva(String numeroReserva) {
		TypedQuery<Reserva> query= this.entityManager.createQuery("Select r From Reserva r Where r.numeroReserva=:DatoNumero",Reserva.class);
		query.setParameter("DatoNumero", numeroReserva);
		return query.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<ReservaDTO> seleccionarListaPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		TypedQuery<ReservaDTO> query=this.entityManager.createQuery("SELECT New com.example.demo.repository.modelo.dto.ReservaDTO(r.numeroReserva,"
				+ "r.fechaCobro,r.estado,r.valorSubtotal,r.valorTotal,"
				+ "c.cedula,c.nombre,c.apellido,v.placa,v.modelo)"
				+ " FROM Reserva r "
				+ " JOIN  r.cliente c "
				+ " JOIN  r.vehiculo v "
				+ " WHERE r.fechaCobro "
				+ " BETWEEN :DatoFechaInicio AND :DatoFechaFin",
				ReservaDTO.class);
		query.setParameter("DatoFechaInicio", fechaInicio);
		query.setParameter("DatoFechaFin", fechaFin);
		return query.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<ClienteVipDTO> seleccionarListaClientesVIPOrdenados() {
		TypedQuery<ClienteVipDTO> query=this.entityManager.createQuery("SELECT New com.example.demo.repository.modelo.dto.ClienteVipDTO(c.cedula,"
				+ " c.nombre,c.apellido,SUM(r.valorSubtotal), SUM(r.valorTotal)) "
				+ " FROM Reserva r "
				+ " JOIN r.cliente c "
				+ " GROUP BY c.id, c.nombre "
				+ " ORDER BY SUM(r.valorTotal) DESC",
				ClienteVipDTO.class);
		return query.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<VehiculoVipDTO> seleccionarListaVehiculosMesAnio(int mesSeleccionado, int anioSeleccionado) {
		TypedQuery<VehiculoVipDTO> query=this.entityManager.createQuery("SELECT New "
				+ " com.example.demo.repository.modelo.dto.VehiculoVipDTO(v.placa,v.modelo,v.marca,v.anioFabricacion,"
				+ " v.cilindraje,SUM(r.valorICE),SUM(r.valorTotal)) "
				+ " FROM Reserva r "
				+ " JOIN r.vehiculo v "
				+ " WHERE MONTH(r.fechaCobro)=:DatoMes AND YEAR(r.fechaCobro)=:DatoAnio "
				+ " GROUP BY v.id, v.placa "
				+ " ORDER BY SUM(r.valorTotal) DESC",
				VehiculoVipDTO.class);
		query.setParameter("DatoMes", mesSeleccionado);
		query.setParameter("DatoAnio", anioSeleccionado);
		return query.getResultList();
	}

}