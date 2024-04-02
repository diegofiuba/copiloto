/**
 * 
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego
 *
 */
public class Vehiculos {
	
	private List<Vehiculo> vehiculos=new ArrayList<Vehiculo>();
    
	/**
	 * 
	 */
	public Vehiculos() {
		super();
	}
	
	public void insertar(Vehiculo elVehiculo) throws ErrorDeInsercion {
		for(Vehiculo vehiculo : vehiculos) {
			if( vehiculo.getPatente().equals( elVehiculo.getPatente() ) ) {
				throw new ErrorDeInsercion();
			}
		}
		this.vehiculos.add(elVehiculo);
		
	}

	public void vender(int indice) {
		Vehiculo vehiculo =this.vehiculos.get(indice);
		vehiculo.vender();
	}
	
	public void eliminar(int indice) throws ErrorDeEliminacion {

		Vehiculo vehiculo =this.vehiculos.get(indice);
		if(vehiculo.estaUsado()) {
			throw new ErrorDeEliminacion();
		}
		this.vehiculos.remove(indice);
	}
	
	public int cantidad() {
		return this.vehiculos.size();
	}	
	
	public Vehiculo obtener(int indice) {
		return this.vehiculos.get(indice);
	}

	public Vehiculo obtenerVehiculoPorPatente(String patente) {
		for(Vehiculo vehiculo : vehiculos) {
			if(vehiculo.getPatente().equals(patente)) {
				return vehiculo;
			}
		}
		return null;

	}
	
	public boolean estaUsado(int indice) {
		return this.vehiculos.get(indice).estaUsado();
	}
	
	public boolean esUnicoTipo(String tipo, int fila) {
		for(int i=0;i<vehiculos.size();i++) {
			if(vehiculos.get(i).getTipo().equals(tipo)) {
				if(i!=fila) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean esUnicaMarca(String marca, int fila) {
		for(int i=0;i<vehiculos.size();i++) {
			if(vehiculos.get(i).getMarca().equals(marca)) {
				if(i!=fila) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean esUnicoModelo(String modelo, int fila) {
		for(int i=0;i<vehiculos.size();i++) {
			if(vehiculos.get(i).getModelo().equals(modelo)) {
				if(i!=fila) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean esUnicaPatente(String patente, int fila) {
		for(int i=0;i<vehiculos.size();i++) {
			if(vehiculos.get(i).getPatente().equals(patente)) {
				if(i!=fila) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean esUnicoColor(String color, int fila) {
		for(int i=0;i<vehiculos.size();i++) {
			if(vehiculos.get(i).getColor().equals(color)) {
				if(i!=fila) {
					return false;
				}
			}
		}
		return true;
	}
	

}
