/**
 * 
 */
package presentacion;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import modelo.ErrorDeEliminacion;

/**
 * @author diego
 *
 */
public class ControladorDeBorrado implements ControladorDeAccion {

	private Component gestor;
	private JTable tabla;
	private Eliminable eliminable;
	private String objeto;
	private ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion;

	/**
	 * @param controladorDeCierreDeAplicacion 
	 * @param tablaDeClientes
	 * @param modeloTablaDeClientes
	 */
	public ControladorDeBorrado(JTable tabla, Eliminable eliminable, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		super();
		this.tabla = tabla;
		this.eliminable = eliminable;
		this.controladorDeCierreDeAplicacion= controladorDeCierreDeAplicacion;
	}
	
	/**
	 * @param objeto
	 * @param tabla
	 * @param eliminable
	 * @param controladorDeCierreDeAplicacion 
	 */
	public ControladorDeBorrado(String objeto, JTable tabla, Eliminable eliminable, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		super();
		this.objeto = objeto;
		this.tabla = tabla;
		this.eliminable = eliminable;
		this.controladorDeCierreDeAplicacion= controladorDeCierreDeAplicacion;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
        int fila=this.tabla.getSelectedRow();
        //System.out.println("fila seleccionada"+fila);
        if(fila==-1) {
        	return;
        }
        try {
			this.eliminable.borrar(fila);
			this.controladorDeCierreDeAplicacion.registrarCambiosSinGuardar();
			
		} catch (ErrorDeEliminacion e1) {
			JOptionPane.showMessageDialog(this.gestor, this.objeto+" no puede ser borrado porque ya ha realizado una operación");
		}
	}

	@Override
	public void setComponente(Component component) {
        this.gestor=component;
	}

}
