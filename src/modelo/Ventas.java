/**
 * 
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

import utilidades.Calendario;

/**
 * @author diego
 *
 */
public class Ventas {

	private List<Venta> ventas=new ArrayList<Venta>();
    private Calendario calendario = new Calendario();
	/**
	 * 
	 */
	public Ventas() {
		super();
	}
	
	
	public void insertar(Venta laVenta) {
		this.ventas.add(laVenta);
		
	}
	
	public Number ingresosDiarios() {
		Number ingresos=0;
		for(Venta venta : ventas) {
			calendario.fijarFecha(venta.getFecha());
			if(calendario.esDiaActual()) {
				ingresos=ingresos.floatValue()+venta.getMonto().floatValue();
			}
		}
		return ingresos;
	}

	public Number ingresosMensuales() {
		Number ingresos=0;
		for(Venta venta : ventas) {
			calendario.fijarFecha(venta.getFecha());
			if(calendario.esMesActual()) {
				ingresos=ingresos.floatValue()+venta.getMonto().floatValue();
			}
		}
		return ingresos;
	}
	
	public Number ingresosAnuales() {
		Number ingresos=0;
		for(Venta venta : ventas) {
			calendario.fijarFecha(venta.getFecha());
			if(calendario.esAnioActual()) {
				ingresos=ingresos.floatValue()+venta.getMonto().floatValue();
			}
		}
		return ingresos;
	}
	
	public Number ingresosAcumulados() {
		Number ingresos=0;
		for(Venta venta : ventas) {
				ingresos=ingresos.floatValue()+venta.getMonto().floatValue();
		}
		return ingresos;
	}
	
	public int cantidad() {
		return this.ventas.size();
	}	

	public Venta obtener(int indice) {
		return this.ventas.get(indice);
	}
}
