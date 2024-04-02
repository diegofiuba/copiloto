/**
 * 
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego
 *
 */
public class Clientes {

	private List<Cliente> clientes=new ArrayList<Cliente>();
    private int indice=0;
	/**
	 * 
	 */
	public Clientes() {
		super();
	}
	
	public void insertar(Cliente cliente) {
		cliente.setId(indice);
		this.clientes.add(cliente);
		indice++;
	}

	public Cliente obtenerClientePorId(int id) {
		for(Cliente cliente : clientes) {
			if(cliente.getId()==id) {
				return cliente;
			}
		}
		return null;

	}
	
	public void eliminar(int indice) throws ErrorDeEliminacion {
		Cliente cliente =this.clientes.get(indice);
		if(cliente.esUsuario()) {
			throw new ErrorDeEliminacion();
		}
		this.clientes.remove(indice);
	}
	
	public int cantidad() {
		return this.clientes.size();
	}	
	
	public Cliente obtener(int indice) {
		return this.clientes.get(indice);
	}
	
	public void convertirEnUsuario(int indice) {
		this.clientes.get(indice).convertirEnUsuario();
	}
	
	public boolean esUsuario(int indice) {
		return this.clientes.get(indice).esUsuario();
	}
}
