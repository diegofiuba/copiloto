/**
 * 
 */
package presentacion;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * @author diego
 *
 */
public class InformeDeVentas extends JPanel implements TableModelListener{

	private ModeloTablaDeVentas modeloTablaDeVentas;
	private JLabel etiquetaIngresosDiarios;
	private JLabel etiquetaIngresosMensuales;
	private JLabel etiquetaIngresosAnuales;
	private JLabel etiquetaIngresosAcumulados;

	/**
	 * @param modeloTablaDeVentas
	 */
	public InformeDeVentas(ModeloTablaDeVentas modeloTablaDeVentas) {
		super();
		this.modeloTablaDeVentas = modeloTablaDeVentas;
		this.modeloTablaDeVentas.addTableModelListener(this);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		GridLayout layout = new GridLayout(5,2,-1,-1);
		this.setLayout(layout);
		//layout.setVgap(10);

		String ingresosIniciales = NumberFormat.getCurrencyInstance().format(0);
		etiquetaIngresosDiarios=crearEtiqueta(ingresosIniciales);
		etiquetaIngresosMensuales=crearEtiqueta(ingresosIniciales);
		etiquetaIngresosAnuales=crearEtiqueta(ingresosIniciales);
		etiquetaIngresosAcumulados=crearEtiqueta(ingresosIniciales);
		
    	calcularIngresos();
		
		add(crearEtiqueta("Fecha"));
		add(crearEtiqueta("Ingresos"));
		
		add(crearEtiqueta("Hoy"));
		add(etiquetaIngresosDiarios);
		
		add(crearEtiqueta("Este mes"));
		add(etiquetaIngresosMensuales);
		
		add(crearEtiqueta("Este Año"));
		add(etiquetaIngresosAnuales);
		
		add(crearEtiqueta("Acumulados"));
		add(etiquetaIngresosAcumulados);
	}

	private JLabel crearEtiqueta(String texto) {
		JLabel etiqueta=new JLabel(texto,JLabel.CENTER);
		etiqueta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return etiqueta;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
	    if(e.getType()==TableModelEvent.INSERT) {
	    	calcularIngresos();
	    }	
	}

	private void calcularIngresos() {
		String ingresosDiarios=NumberFormat.getCurrencyInstance().format(modeloTablaDeVentas.ingresosDiarios());
		String ingresosMensuales=NumberFormat.getCurrencyInstance().format(modeloTablaDeVentas.ingresosMensuales());
		String ingresosAnuales=NumberFormat.getCurrencyInstance().format(modeloTablaDeVentas.ingresosAnuales());
		String ingresosAcumulados=NumberFormat.getCurrencyInstance().format(modeloTablaDeVentas.ingresosAcumulados());

		etiquetaIngresosDiarios.setText(ingresosDiarios);
		etiquetaIngresosMensuales.setText(ingresosMensuales);
		etiquetaIngresosAnuales.setText(ingresosAnuales);
		etiquetaIngresosAcumulados.setText(ingresosAcumulados);
	}
	
	
}
