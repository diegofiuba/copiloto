/**
 * 
 */
package presentacion;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import modelo.Cliente;
import modelo.Venta;

/**
 * @author diego
 *
 */
public class ControladorDeVentaDeVehiculo implements ControladorDeAccion {

	private Component gestorDeVentas;
	private Formulario formularioDeNuevaVenta;
	private JTable tablaDeVehiculos;
	private JTable tablaDeClientes;
	private ModeloTablaDeVehiculos modeloTablaDeVehiculos;
	private ModeloTablaDeClientes modeloTablaDeClientes;
	private ModeloTablaDeVentas modeloTablaDeVentas;
	private ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion;
	
	/**
	 * @param formularioDeNuevaVenta
	 * @param tablaDeVehiculos
	 * @param tablaDeClientes
	 * @param modeloTablaDeVehiculos
	 * @param modeloTablaDeClientes
	 * @param modeloTablaDeVentas
	 * @param controladorDeCierreDeAplicacion 
	 */
	public ControladorDeVentaDeVehiculo(Formulario formularioDeNuevaVenta, JTable tablaDeVehiculos,
			JTable tablaDeClientes, ModeloTablaDeVehiculos modeloTablaDeVehiculos,
			ModeloTablaDeClientes modeloTablaDeClientes, ModeloTablaDeVentas modeloTablaDeVentas, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		super();
		this.formularioDeNuevaVenta = formularioDeNuevaVenta;
		this.tablaDeVehiculos = tablaDeVehiculos;
		this.tablaDeClientes = tablaDeClientes;
		this.modeloTablaDeVehiculos = modeloTablaDeVehiculos;
		this.modeloTablaDeClientes = modeloTablaDeClientes;
		this.modeloTablaDeVentas = modeloTablaDeVentas;
		this.controladorDeCierreDeAplicacion=controladorDeCierreDeAplicacion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int filaVehiulos=this.tablaDeVehiculos.getSelectedRow();
		if(filaVehiulos==-1) {
        	return;
        }

		int filaClientes=this.tablaDeClientes.getSelectedRow();
		if(filaClientes==-1) {
        	return;
        }
		
		boolean estaDisponible=(boolean) this.tablaDeVehiculos.getValueAt(filaVehiulos, 0);
		if(!estaDisponible) {
			JOptionPane.showMessageDialog(this.gestorDeVentas, "El vehiculo seleccionado ya no está disponible para vender");
            return;
		}   
		
		int opcionElegida=JOptionPane.showOptionDialog(this.gestorDeVentas, this.formularioDeNuevaVenta, "Vender", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		List<String> valores=formularioDeNuevaVenta.obtenerValores();
		
		if(opcionElegida==JOptionPane.OK_OPTION) {
			try {
				Cliente cliente=this.modeloTablaDeClientes.obtenerCliente(filaClientes);

				Date date=SimpleDateFormat.getDateInstance().parse(valores.get(1));
				
				LocalDate localdate=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				Venta venta=new Venta(cliente,
						this.modeloTablaDeVehiculos.obtenerVehiculo(filaVehiulos),
						DecimalFormat.getNumberInstance().parse(valores.get(0)), 
						localdate
						);
				this.modeloTablaDeVentas.agregarVenta(venta);

                this.modeloTablaDeVehiculos.venderVehiculo(filaVehiulos);
                this.modeloTablaDeClientes.convertirEnUsuario(filaClientes);

                this.controladorDeCierreDeAplicacion.registrarCambiosSinGuardar();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		}
		formularioDeNuevaVenta.limpiar();
	}

	@Override
	public void setComponente(Component component) {
        this.gestorDeVentas=component;

	}

}
