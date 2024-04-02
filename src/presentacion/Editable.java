/**
 * 
 */
package presentacion;

/**
 * @author diego
 *
 */
public interface Editable {

	int getColumnCount();
	Object getValueAt(int rowIndex, int columnIndex);
	void setValueAt(Object aValue, int rowIndex, int columnIndex);
	
}
