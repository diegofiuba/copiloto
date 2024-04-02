/**
 * 
 */
package presentacion;

import modelo.ErrorDeEliminacion;

/**
 * @author diego
 *
 */
public interface Eliminable {
	
	public void borrar(int fila) throws ErrorDeEliminacion;

}
