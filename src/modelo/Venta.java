/**
 * 
 */
package modelo;

import java.time.LocalDate;

/**
 * @author diego
 *
 */
public class Venta {

	private Cliente cliente;
	private Vehiculo vehiculo;
	private Number monto;
	private LocalDate fecha;
	
	/**
	 * @param cliente
	 * @param vehiculo
	 * @param monto
	 * @param fecha
	 */
	public Venta(Cliente cliente, Vehiculo vehiculo, Number monto, LocalDate fecha) {
		super();
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.monto = monto;
		this.fecha = fecha;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @return the vehiculo
	 */
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	/**
	 * @return the monto
	 */
	public Number getMonto() {
		return monto;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	
	
	
}
