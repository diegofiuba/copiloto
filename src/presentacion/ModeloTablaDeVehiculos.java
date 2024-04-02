/**
 * 
 */
package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import modelo.ErrorDeEliminacion;
import modelo.ErrorDeInsercion;
import modelo.Vehiculo;
import modelo.Vehiculos;

/**
 * @author diego
 *
 */
public class ModeloTablaDeVehiculos implements TableModel,VehiculoAgregable,VehiculoEditable,Eliminable {

	private Vehiculos datos=new Vehiculos();
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
			return "Disponible";
		case 1: 
			return "Tipo";
		case 2:
			return "Patente";
		case 3:
			return "Marca";
		case 4:
			return "Modelo";
		case 5:
			return "Color";
		default:
			return null;
		}	
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0: 
			return Boolean.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;			
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
		return 6;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vehiculo vehiculo=datos.obtener(rowIndex);
		
		switch(columnIndex) {
		case 0: 
			return vehiculo.estaDisponible();	
		case 1:
			return vehiculo.getTipo();			
		case 2:
			return vehiculo.getPatente();
		case 3:
			return vehiculo.getMarca();
		case 4:
			return vehiculo.getModelo();
		case 5:
			return vehiculo.getColor();
		default:
			return null;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Vehiculo vehiculo=datos.obtener(rowIndex);
		
		switch(columnIndex) {
		case 1: 
			vehiculo.setTipo((String)aValue);
			break;

		case 2:
			vehiculo.setPatente((String)aValue);
			break;			
			
		case 3:
			vehiculo.setMarca((String)aValue);
			break;

		case 4:
			vehiculo.setModelo((String)aValue);
			break;
			
		case 5:
			vehiculo.setColor((String)aValue);
			break;
		default:
			break;
		}
		//notificar suscriptores
		TableModelEvent evento=new TableModelEvent(this,rowIndex,rowIndex,columnIndex);
		notificarSuscriptores(evento);
	}

	public boolean esUnico(Object aValue, int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 1: 
			return datos.esUnicoTipo((String)aValue, rowIndex);
			

		case 2:
			return datos.esUnicaMarca((String)aValue, rowIndex);
			

		case 3:
			return datos.esUnicoModelo((String)aValue, rowIndex);
			
			
		case 4:
			return datos.esUnicaPatente((String)aValue, rowIndex);
			
			
		case 5:
			return datos.esUnicoColor((String)aValue, rowIndex);
			
		default:
			break;
		}
		return false;
	}

	@Override
	public void borrar(int fila) throws ErrorDeEliminacion {
		datos.eliminar(fila);

		//notificar suscriptores
		TableModelEvent evento=new TableModelEvent(this,fila,fila,TableModelEvent.ALL_COLUMNS,TableModelEvent.DELETE);
		notificarSuscriptores(evento);
	}
	
	public Vehiculo obtenerVehiculo(int fila) {
		return datos.obtener(fila);
	}

	public Vehiculo obtenerVehiculoPorPatente(String patente) {
		return datos.obtenerVehiculoPorPatente(patente);
	}
	
	public void venderVehiculo(int fila) {
		datos.vender(fila);
		//notificar suscriptores
		TableModelEvent evento=new TableModelEvent(this,fila,fila,5);
		notificarSuscriptores(evento);		
	}
	
	public void agregarVehiculo(Vehiculo vehiculo) throws ErrorDeInsercion {
		datos.insertar(vehiculo);
		
		//notificar suscriptores
		TableModelEvent evento=new TableModelEvent(this,this.getRowCount()-1,this.getRowCount()-1,TableModelEvent.ALL_COLUMNS,TableModelEvent.INSERT);
		notificarSuscriptores(evento);
	}

	public boolean estaUsado(int fila) {
		return datos.estaUsado(fila);
	}
	
	private void notificarSuscriptores(TableModelEvent evento) {
		
		for(int i=0;i<listeners.size();i++) {
			((TableModelListener)listeners.get(i)).tableChanged(evento);
		}
	}
	
	public String obtenerEncabezado(String separador) {
		String encabezado = "";
		int indice=0;
		while(indice<this.getColumnCount()-1) {
			encabezado=encabezado+this.getColumnName(indice)+separador;
		     indice++;
		}
		encabezado=encabezado+this.getColumnName(indice);
		return encabezado;
	}

}
