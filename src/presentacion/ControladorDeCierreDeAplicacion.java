/**
 * 
 */
package presentacion;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import utilidades.GestorDeArchivos;

/**
 * @author diego
 *
 */
public class ControladorDeCierreDeAplicacion extends WindowAdapter {

	private Component contenedor;
	private GestorDeArchivos gestorDeArchivos;
	private boolean hayCambiosSinGuardar=false;
	
	/**
	 * @param contenedor
	 * @param gestorDeArchivos
	 */
	public ControladorDeCierreDeAplicacion(Component contenedor, GestorDeArchivos gestorDeArchivos) {
		super();
		this.contenedor = contenedor;
		this.gestorDeArchivos = gestorDeArchivos;
	}

	private int mostrarMensaje(Component ancestro) {
		String[] nombreDeBotones=new String[] {"Si","No","Cancelar"};
		String opcionPorDefault=nombreDeBotones[0];
		
		return JOptionPane.showOptionDialog(
				ancestro, 
				"Hay cambios sin guardar"+
				"¿Desea guardar cambios antes de salir?",
				"Atención",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				nombreDeBotones,
				opcionPorDefault);
		
		
	}
	
	private boolean hayCambiosSinGuardar() {
		//return true;
		///System.out.println("hay Cambios Sin Guardar?"+hayCambiosSinGuardar);
		return this.hayCambiosSinGuardar;
	}
	
	public void registrarCambiosSinGuardar() {
		this.hayCambiosSinGuardar=true;
	}
	
	private void manejarCierre() {
		if(hayCambiosSinGuardar()) {
			int respuesta=mostrarMensaje(this.contenedor);
			
			switch(respuesta) {
			
			case JOptionPane.YES_OPTION:
				this.gestorDeArchivos.guardar();
				this.hayCambiosSinGuardar=false;
			    System.exit(0);
				//dispose();
				break;
			case JOptionPane.NO_OPTION:
			    System.exit(0);
				//dispose();
				break;
			case JOptionPane.CANCEL_OPTION:
			
				break;	
			
			}
		}else {
		    System.exit(0);
			//dispose();
		}
	}
	
	public void windowClosing(WindowEvent e) {
		///System.out.println(e.toString());
		manejarCierre();
	}
	
}
