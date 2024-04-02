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
public class Talleres {
	
	private List<Taller> talleres=new ArrayList<Taller>();
    private int indice=0;
	/**
	 * 
	 */
	public Talleres() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void agregar(Taller taller) {
		taller.setId(indice);
		this.talleres.add(taller);
		indice++;
	}

	public Taller obtenerCliente(int id) {
		for(Taller taller : talleres) {
			if(taller.getId()==id) {
				return taller;
			}
		}
		return null;
		//return this.clientes.get(id);
	}
	
	public void eliminarCliente(int id) throws ErrorDeEliminacion {
		Taller taller =this.talleres.get(indice);
		if(taller.estaActivo()) {
			throw new ErrorDeEliminacion();
		}
		this.talleres.remove(indice);
	}
}
