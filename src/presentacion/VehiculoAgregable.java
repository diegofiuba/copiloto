/**
 * 
 */
package presentacion;

import modelo.ErrorDeInsercion;
import modelo.Vehiculo;

/**
 * @author diego
 *
 */
public interface VehiculoAgregable {

	void agregarVehiculo(Vehiculo vehiculo) throws ErrorDeInsercion;
	
}
