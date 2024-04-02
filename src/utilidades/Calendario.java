/**
 * 
 */
package utilidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author diego
 *
 */
public class Calendario {

	private Calendar calendario;
	private Calendar calendarioDeHoy;

	/**
	 * 
	 */
	public Calendario() {
		super();
		this.calendario=GregorianCalendar.getInstance();
		this.calendarioDeHoy=GregorianCalendar.getInstance();
	}

	public void fijarFecha(LocalDate fecha) {
		/**/Date lafecha=Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
		calendario.setTime(lafecha);
		this.calendarioDeHoy.setTime(new Date());
	}

	public boolean esDiaActual() {
		int anio=this.calendario.get(Calendar.YEAR);
		int esteAnio=this.calendarioDeHoy.get(Calendar.YEAR);
		int mes=this.calendario.get(Calendar.MONTH);
		int esteMes=this.calendarioDeHoy.get(Calendar.MONTH);
		int dia=this.calendario.get(Calendar.DAY_OF_MONTH);
		int esteDia=this.calendarioDeHoy.get(Calendar.DAY_OF_MONTH);
		return anio==esteAnio && mes==esteMes && dia==esteDia;
	}
	
	public boolean esMesActual() {
		int anio=this.calendario.get(Calendar.YEAR);
		int esteAnio=this.calendarioDeHoy.get(Calendar.YEAR);
		int mes=this.calendario.get(Calendar.MONTH);
		int esteMes=this.calendarioDeHoy.get(Calendar.MONTH);
		return anio==esteAnio && mes==esteMes;
	}
	
	public boolean esAnioActual() {
		int anio=this.calendario.get(Calendar.YEAR);
		int esteAnio=this.calendarioDeHoy.get(Calendar.YEAR);
		return anio==esteAnio;
	}
	
}
