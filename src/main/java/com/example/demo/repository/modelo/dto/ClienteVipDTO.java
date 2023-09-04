package com.example.demo.repository.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;

public class ClienteVipDTO {
	

	private String cedula;
	private String nombre;
	private String apellido;
	private BigDecimal valorSubtotal;
	private BigDecimal valorTotal;
	
	public ClienteVipDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ClienteVipDTO(String cedula, String nombre, String apellido, BigDecimal valorSubtotal,
			BigDecimal valorTotal) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.valorSubtotal = valorSubtotal;
		this.valorTotal = valorTotal;
	}

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}
	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

}
