/**
 * 
 */
package presentacion;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * @author diego
 *
 */
public class ControladorDeEdicionDeVehiculo implements ControladorDeAccion {

	private Component gestor;
	private Formulario formulario;
	private JTable tabla;
	private VehiculoEditable modeloDeTabla;
	private ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion;
	
	/**
	 * @param formulario
	 * @param tabla
	 * @param modeloDeTabla
	 * @param controladorDeCierreDeAplicacion 
	 */
	public ControladorDeEdicionDeVehiculo(Formulario formulario, JTable tabla, VehiculoEditable modeloDeTabla, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		super();
		this.formulario = formulario;
		this.tabla = tabla;
		this.modeloDeTabla = modeloDeTabla;
		this.controladorDeCierreDeAplicacion= controladorDeCierreDeAplicacion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int fila=this.tabla.getSelectedRow();
        if(fila==-1) {
        	return;
        }
		int cantidadDeAtributos=this.modeloDeTabla.getColumnCount();
        for(int columna=1;columna<cantidadDeAtributos;columna++) {
        	Object valor=this.modeloDeTabla.getValueAt(fila, columna);

        	this.formulario.rellenarCon(valor.toString(), columna-1);

        }
		int opcionElegida=JOptionPane.showOptionDialog(this.gestor, this.formulario, "Editar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		
		if(opcionElegida==JOptionPane.OK_OPTION) {
			
			boolean laPatenteEsUnica = this.modeloDeTabla.esUnico(this.formulario.obtenerValorDelCampo(3), fila, 4);
			if(laPatenteEsUnica) {
		        this.controladorDeCierreDeAplicacion.registrarCambiosSinGuardar();
				for(int columna=0;columna<cantidadDeAtributos-1;columna++) {
		        	String nuevoValor=this.formulario.obtenerValorDelCampo(columna);
	                this.modeloDeTabla.setValueAt(nuevoValor,fila, columna+1);
		        }
			}else {
				JOptionPane.showMessageDialog(this.gestor, "Ya hay un auto en el listado con igual patente");
			}
			

		}
		formulario.limpiar();

	}

	@Override
	public void setComponente(Component component) {
        this.gestor=component;
	}

}
