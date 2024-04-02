/**
 * 
 */
package presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import modelo.Cliente;
import modelo.Vehiculo;
import modelo.Venta;
import modelo.Ventas;

/**
 * @author diego
 *
 */
public class ModeloTablaDeVentas implements TableModel,Consultable {

	private Ventas datos=new Ventas();
	private List listeners=new ArrayList();

	@Override
	public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Nombre";
		case 2:
			return "Apellido";
		case 3:
			return "DNI";
		case 4:
			return "Contacto";	
		case 5: 
			return "Tipo";
		case 6:
			return "Patente";
		case 7:
			return "Marca";
		case 8:
			return "Modelo";
		case 9:
			return "Color";
		case 10:
			return "Monto";
		case 11:
			return "Fecha";
		default:
			return null;
		}		
	
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return String.class;
		case 7:
			return String.class;
		case 8:
			return String.class;
		case 9:
			return String.class;
		case 10:
			return Float.class;			
		case 11:
			return LocalDate.class;		
		default:
			return 	Object.class;
		}	
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return datos.cantidad();
	}

	@Override
	public int getColumnCount() {
		return 12;//8;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Venta venta=datos.obtener(rowIndex);
		Vehiculo vehiculo=venta.getVehiculo();
		Cliente cliente=venta.getCliente();

		switch(columnIndex) {
		case 0:
			return cliente.getId();
		case 1:
			return cliente.getNombre();
		case 2:
			return cliente.getApellido();
		case 3:
			return cliente.getDni();
		case 4:
			return cliente.getContacto();	
		case 5: 
			return vehiculo.getTipo();
		case 6:
			return vehiculo.getPatente();
		case 7:
			return vehiculo.getMarca();
		case 8:
			return vehiculo.getModelo();
		case 9:
			return vehiculo.getColor();
		case 10:
			return venta.getMonto();			
		case 11:
			return venta.getFecha();			
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//no lo usa
	}

	public Number ingresosDiarios() {
		return datos.ingresosDiarios();
	}
	
	
	public Number ingresosMensuales() {
		return datos.ingresosMensuales();
	}
	
	
	public Number ingresosAnuales() {
		return datos.ingresosAnuales();
	}

	
	public Number ingresosAcumulados() {
		return datos.ingresosAcumulados();
	}
	
	public void agregarVenta(Venta venta) {
		datos.insertar(venta);
		
		//notificar suscriptores
		TableModelEvent evento=new TableModelEvent(this,this.getRowCount()-1,this.getRowCount()-1,TableModelEvent.ALL_COLUMNS,TableModelEvent.INSERT);
		notificarSuscriptores(evento);
	}
	
	private void notificarSuscriptores(TableModelEvent evento) {
		
		for(int i=0;i<listeners.size();i++) {
			((TableModelListener)listeners.get(i)).tableChanged(evento);
		}
	}
	
	public String obtenerEncabezado(String separador) {
		String encabezado = null;
		int indice=0;
		while(indice<this.getColumnCount()-1) {
			encabezado=encabezado+this.getColumnName(indice)+separador;
		     indice++;
		}
		encabezado=encabezado+this.getColumnName(indice);
		return encabezado;
	}
	
}
