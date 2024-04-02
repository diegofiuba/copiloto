/**
 * 
 */
package presentacion;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Cliente;

/**
 * @author diego
 *
 */
public class ControladorDeInsercionDeCliente implements ControladorDeAccion {

	private Component gestorDeClientes;
	private Formulario formularioDeNuevoCliente;
	private ClienteAgregable modeloTablaDeClientes;
	private ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion;
	
	/**
	 * @param formularioDeNuevoCliente
	 * @param modeloTablaDeClientes
	 * @param controladorDeCierreDeAplicacion 
	 */
	public ControladorDeInsercionDeCliente(Formulario formularioDeNuevoCliente,
			ClienteAgregable modeloTablaDeClientes, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		super();
		this.formularioDeNuevoCliente = formularioDeNuevoCliente;
		this.modeloTablaDeClientes = modeloTablaDeClientes;
		this.controladorDeCierreDeAplicacion=controladorDeCierreDeAplicacion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int opcionElegida=JOptionPane.showOptionDialog(this.gestorDeClientes, this.formularioDeNuevoCliente, "Agregar"/*"Nuevo cliente"*/, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		
		List<String> valores=formularioDeNuevoCliente.obtenerValores();
		
		if(opcionElegida==JOptionPane.OK_OPTION) {
			Cliente cliente;
			try {
				cliente = new Cliente(
						valores.get(0), 
						valores.get(1), 
						NumberFormat.getIntegerInstance().parse(valores.get(2)), 
						valores.get(3));
				this.modeloTablaDeClientes.agregarCliente(cliente);
                this.controladorDeCierreDeAplicacion.registrarCambiosSinGuardar();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		formularioDeNuevoCliente.limpiar();
		
	}

	@Override
	public void setComponente(Component component) {
        this.gestorDeClientes= component;
	}

}
