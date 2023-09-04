package com.example.demo.repository.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.cglib.core.Local;


public class VehiculoVipDTO {

	private String placa;

	private String modelo;

	private String marca;

	private LocalDate anioFabricacion;

	private Double cilindraje;
		
	private BigDecimal valorICE;
	
	private BigDecimal valorTotal;
	
	public VehiculoVipDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public VehiculoVipDTO(String placa, String modelo, String marca, LocalDate anioFabricacion,
			Double cilindraje, BigDecimal valorICE, BigDecimal valorTotal) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.anioFabricacion = anioFabricacion;
		this.cilindraje = cilindraje;
		this.valorICE = valorICE;
		this.valorTotal = valorTotal;
	}


	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public LocalDate getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(LocalDate anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public Double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}

	public BigDecimal getValorICE() {
		return valorICE;
	}

	public void setValorICE(BigDecimal valorICE) {
		this.valorICE = valorICE;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
