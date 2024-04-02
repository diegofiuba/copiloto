/**
 * 
 */
package presentacion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author diego
 *
 */
public class RenderBooleano extends DefaultTableCellRenderer {

	private Color colorDeSeleccion;
	
	/**
	 * @param colorDeSeleccion
	 */
	public RenderBooleano(Color colorDeSeleccion) {
		super();
		this.colorDeSeleccion = colorDeSeleccion;
	}

	public Component getTableCellRendererComponent(JTable tabla,Object valor,boolean estaSeleccionado,boolean tieneFoco,int fila,int columna) {
		JLabel etiqueta=new JLabel();
		etiqueta.setOpaque(true);
		if(estaSeleccionado) {
			etiqueta.setBackground(this.colorDeSeleccion);
		}else {
			etiqueta.setBackground(Color.WHITE);

		}
		if(valor.toString().equals("true")) {
			etiqueta.setText("Disponible");
		}else if(valor.toString().equals("false")) {		
			etiqueta.setText("No Disponible");
		}else {						
			etiqueta.setText(valor.toString());
			
		}
		return etiqueta;
		
	}
	
}
