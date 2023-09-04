package com.example.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IReservaRepository;
import com.example.demo.repository.IVehiculoRepository;
import com.example.demo.repository.iClienteRepository;
import com.example.demo.repository.modelo.Cliente;
import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.repository.modelo.dto.ClienteVipDTO;
import com.example.demo.repository.modelo.dto.ReservaDTO;
import com.example.demo.repository.modelo.dto.VehiculoVipDTO;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private iClienteRepository clienteRepository;
	@Autowired
	private IVehiculoRepository iVehiculoRepository;

	@Autowired
	private IReservaRepository iReservaRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void reservarVehiculo(String placa, String cedula, LocalDate fechaInicio, LocalDate fechaFin,
			String numeroTarjeta) {

		BigDecimal[] valores = calcularValores(placa, fechaInicio, fechaFin);
		Cliente cliente = this.clienteRepository.seleccionarPorCedula(cedula);
		Vehiculo vehiculo = this.iVehiculoRepository.seleccionarPorPlaca(placa);

		Reserva reserva = new Reserva();
		Random rand = new Random();
		int randomNumero = rand.nextInt(1000) + 1;

		reserva.setNumeroReserva(Integer.toString(randomNumero));
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFin(fechaFin);
		reserva.setFechaCobro(LocalDate.now());
		reserva.setEstado("Generada");
		reserva.setValorSubtotal(valores[0]);
		reserva.setValorICE(valores[1]);
		reserva.setValorTotal(valores[2]);
		reserva.setNumeroTarjetaCredito(numeroTarjeta);
		reserva.setCliente(cliente);
		reserva.setVehiculo(vehiculo);

		this.iReservaRepository.insertar(reserva);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public boolean verificarDisponibilidad(String placa, LocalDate fechaInicio, LocalDate fechaFin) {
		boolean aux = false;

		List<Reserva> lista = this.iReservaRepository.seleccionarListaPorPlacaV(placa);
		System.out.println(lista.size());
		if (lista.size() != 0) {
			for (Reserva r : lista) {
				LocalDate fechaInicioRango1 = r.getFechaInicio();
				LocalDate fechaFinRango1 = r.getFechaFin();
				
				// Definir el rango de fechas 2
				LocalDate fechaInicioRango2 = fechaInicio;
				LocalDate fechaFinRango2 = fechaFin;

				// Verificar si el rango de fechas 2 está dentro del rango de fechas 1
				boolean estaDentro = !(fechaFinRango2.isBefore(fechaInicioRango1)
						|| fechaInicioRango2.isAfter(fechaFinRango1));
				if (estaDentro) {
					//"El rango de fechas 2 está completamente dentro del rango de fechas 1.
					aux = false;
					break;
				} else {
					//"El rango de fechas 2 NO está dentro del rango de fechas 1.
					aux = true;
				}
			}
		} else {
			aux = true;
		}
		return aux;
	}

	@Override
	public LocalDate obtenerFechaDisponible(String placa, LocalDate fechaInicio, LocalDate fechaFin) {
		List<Reserva> reservas = this.iReservaRepository.seleccionarListaPorPlacaV(placa);
		LocalDate fechaDisponible = LocalDate.now();
		for (Reserva reserva : reservas) {
			LocalDate fechaInicioReserva = reserva.getFechaInicio();
			LocalDate fechaFinReserva = reserva.getFechaFin();

			// Verifica si el rango de fechas se solapa con alguna reserva existente
			if (!(fechaFin.isBefore(fechaInicioReserva) || fechaInicio.isAfter(fechaFinReserva))) {
				fechaDisponible = fechaFinReserva.plusDays(1);
			}
		}
		System.err.println(fechaDisponible);
		return fechaDisponible;
	}

	@Override
	public BigDecimal valorTotalAPagar(String placa, LocalDate fechaInicio, LocalDate fechaFin) {

		BigDecimal[] valores = calcularValores(placa, fechaInicio, fechaFin);
		BigDecimal valorTotal = valores[2];
		return valorTotal;
	}

	// Metodo para que guarde los calulos en un arreglo

	public BigDecimal[] calcularValores(String placa, LocalDate fechaInicio, LocalDate fechaFin) {
		Vehiculo vehiculo = this.iVehiculoRepository.seleccionarPorPlaca(placa);
		long diasDiferencia = ChronoUnit.DAYS.between(fechaInicio, fechaFin);

		BigDecimal valorSubTotal = vehiculo.getValorPorDia().multiply(new BigDecimal(diasDiferencia));
		BigDecimal valorICE = valorSubTotal.multiply(new BigDecimal(0.15));
		BigDecimal valorTotal = valorSubTotal.add(valorICE);

		// Redondear a dos decimales
		BigDecimal valorSubTotalRedondear = valorSubTotal.setScale(2, RoundingMode.HALF_UP);
		BigDecimal valorICERedondear = valorICE.setScale(2, RoundingMode.HALF_UP);
		BigDecimal valorTotalRedondear = valorTotal.setScale(2, RoundingMode.HALF_UP);
		BigDecimal[] valores = { valorSubTotalRedondear, valorICERedondear, valorTotalRedondear };
		return valores;
	}

	@Override
	public List<ReservaDTO> reporteReservas(LocalDate fechaInicio, LocalDate fechaFin) {
		return this.iReservaRepository.seleccionarListaPorFechas(fechaInicio, fechaFin);
	}

	@Override
	public List<ClienteVipDTO> reporteClientesVIP() {
		return this.iReservaRepository.seleccionarListaClientesVIPOrdenados();
	}

	@Override
	public List<VehiculoVipDTO> reporteVehiculosVIP(int mesSeleccionado, int anioSeleccionado) {
		return this.iReservaRepository.seleccionarListaVehiculosMesAnio(mesSeleccionado, anioSeleccionado);
	}
}
