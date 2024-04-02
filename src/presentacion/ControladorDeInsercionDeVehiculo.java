/**
 * 
 */
package presentacion;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.ErrorDeInsercion;
import modelo.Vehiculo;

/**
 * @author diego
 *
 */
public class ControladorDeInsercionDeVehiculo implements ControladorDeAccion {

	private Component gestorDeVehiculos;
	private Formulario formularioDeNuevoVehiculo;
	private VehiculoAgregable modeloTablaDeVehiculos;
	private ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion;
	
	/**
	 * @param formularioDeNuevoVehiculo
	 * @param modeloTablaDeVehiculos
	 * @param controladorDeCierreDeAplicacion 
	 */
	public ControladorDeInsercionDeVehiculo(Formulario formularioDeNuevoVehiculo,
			VehiculoAgregable modeloTablaDeVehiculos, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		super();
		this.formularioDeNuevoVehiculo = formularioDeNuevoVehiculo;
		this.modeloTablaDeVehiculos = modeloTablaDeVehiculos;
		this.controladorDeCierreDeAplicacion= controladorDeCierreDeAplicacion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int opcionElegida=JOptionPane.showOptionDialog(this.gestorDeVehiculos, this.formularioDeNuevoVehiculo, "Agregar"/*"Nuevo vehículo"*/, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		
		List<String> valores=formularioDeNuevoVehiculo.obtenerValores();
		
		if(opcionElegida==JOptionPane.OK_OPTION) {
			Vehiculo vehiculo= new Vehiculo(
						valores.get(0), 
						valores.get(1),
						valores.get(2), 
						valores.get(3), 
						valores.get(4));
				try {
					this.modeloTablaDeVehiculos.agregarVehiculo(vehiculo);
				    this.controladorDeCierreDeAplicacion.registrarCambiosSinGuardar();
				} catch (ErrorDeInsercion e1) {
					JOptionPane.showMessageDialog(this.gestorDeVehiculos, "Ya hay un auto en el listado con igual patente");

				}
			
		}
		formularioDeNuevoVehiculo.limpiar();
	}

	@Override
	public void setComponente(Component component) {
        this.gestorDeVehiculos= component;

	}

}
