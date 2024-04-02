/**
 * 
 */
package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import modelo.Cliente;
import modelo.Clientes;
import modelo.ErrorDeEliminacion;

/**
 * @author diego
 *
 */
public class ModeloTablaDeClientes implements TableModel,ClienteAgregable,Editable,Eliminable {

	private Clientes datos=new Clientes();
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
		return 5;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente=datos.obtener(rowIndex);
		
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
		default:
			return null;
		}
	
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente=datos.obtener(rowIndex);
		
		switch(columnIndex) {
		case 0: 
			cliente.setId((Integer)aValue);
			break;

		case 1:
			cliente.setNombre((String)aValue);
			break;

		case 2:
			cliente.setApellido((String)aValue);
			break;

		case 3:
			cliente.setDni((Integer)aValue);
			break;

		case 4:
			cliente.setContacto((String)aValue);
			break;

		default:
			break;
		}
		//notificar suscriptores
		TableModelEvent evento=new TableModelEvent(this,rowIndex,rowIndex,columnIndex);
		notificarSuscriptores(evento);
	}

	public void agregarCliente(Cliente cliente) {
		datos.insertar(cliente);
		
		//notificar suscriptores
		TableModelEvent evento=new TableModelEvent(this,this.getRowCount()-1,this.getRowCount()-1,TableModelEvent.ALL_COLUMNS,TableModelEvent.INSERT);
		notificarSuscriptores(evento);
	}

	@Override
	public void borrar(int fila) throws ErrorDeEliminacion {
		datos.eliminar(fila);

		//notificar suscriptores
		TableModelEvent evento=new TableModelEvent(this,fila,fila,TableModelEvent.ALL_COLUMNS,TableModelEvent.DELETE);
		notificarSuscriptores(evento);
	}

	public Cliente obtenerCliente(int fila) {
		return datos.obtener(fila);
	}
	
	public Cliente obtenerClientePorId(int id) {
		return datos.obtenerClientePorId(id);
	}
	
	public boolean esUsuario(int fila) {
		return datos.esUsuario(fila);
	}
	
	public void convertirEnUsuario(int fila) {
		datos.convertirEnUsuario(fila);
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
