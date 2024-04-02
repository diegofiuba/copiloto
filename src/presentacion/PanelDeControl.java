/**
 * 
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author diego
 *
 */
public class PanelDeControl extends JPanel {

	/**
	 * 
     * @param titulo titulo del panel
     * @param subtitulo subtitulo del panel
	 * @param panel panel para mostrar información adicional
	 * @param tabla tabla de datos
	 * @param botones botones para realizar acciones con los datos de las tablas
	 */
	public PanelDeControl(String titulo,String subtitulo,String...textos ) {
		super();
		setLayout(new BorderLayout());
		
		JPanel encabezado = construirEncabezado(titulo, subtitulo);		
		
		JPanel panel=new JPanel();

		/**/panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		//GridLayout layout = new GridLayout(textos.length,1);
		//panel.setLayout(layout);
		//layout.setVgap(10);
        
		JLabel etiqueta0=new JLabel();
		etiqueta0.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
		panel.add(etiqueta0);
		
		for(String texto : textos) {
			JLabel etiqueta=new JLabel(texto);
			etiqueta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			panel.add(etiqueta);
		}
		
		add(encabezado,BorderLayout.NORTH);
	    add(panel,BorderLayout.CENTER);		
	    

	}
	
    /**
     * 
     * @param titulo titulo del panel
     * @param subtitulo subtitulo del panel
     */
	public PanelDeControl(String titulo,String subtitulo) {
		super();
		setLayout(new BorderLayout());
		
		JPanel encabezado = construirEncabezado(titulo, subtitulo);		
				
		add(encabezado,BorderLayout.NORTH);
		
	}
	
	/**
	 * 
     * @param titulo titulo del panel
     * @param subtitulo subtitulo del panel
	 * @param tabla tabla de datos
	 * @param botones botones para realizar acciones con los datos de las tablas
	 */
	public PanelDeControl(String titulo,String subtitulo,JTable tabla,JButton... botones) {
		super();
		setLayout(new BorderLayout());
		
		JPanel encabezado = construirEncabezado(titulo, subtitulo);		
		
		JPanel panelDeControl = construirPanelDeControlSimple(tabla, botones);
		
		add(encabezado,BorderLayout.NORTH);
	    add(panelDeControl,BorderLayout.CENTER);
		
	}

	/**
	 * 
     * @param titulo titulo del panel
     * @param subtitulo subtitulo del panel
	 * @param nombreTabla1 nombre de la tabla
	 * @param tabla1 tabla de datos
	 * @param nombreTabla2 nombre de la tabla
	 * @param tabla2 tabla de datos
	 * @param botones botones para realizar acciones con los datos de las tablas
	 */
	public PanelDeControl(String titulo,String subtitulo,String nombreTabla1,JTable tabla1,String nombreTabla2,JTable tabla2,JButton... botones) {
		super();
		setLayout(new BorderLayout());
		
		JPanel encabezado=construirEncabezado(titulo, subtitulo);		
		
		JPanel panelDeControl=construirPanelDeControlComplejo(nombreTabla1,tabla1,nombreTabla2,tabla2, botones);
		
		add(encabezado,BorderLayout.NORTH);
	    add(panelDeControl,BorderLayout.CENTER);		
		
	}
	
	/**
	 * 
     * @param titulo titulo del panel
     * @param subtitulo subtitulo del panel
	 * @param panel panel para mostrar información adicional
	 * @param tabla tabla de datos
	 * @param botones botones para realizar acciones con los datos de las tablas
	 */
	public PanelDeControl(String titulo,String subtitulo,JPanel panel,JTable tabla,JButton... botones) {
		super();
		setLayout(new BorderLayout());
		
		JPanel encabezado = construirEncabezado(titulo, subtitulo);		
		JPanel panelDeControl = construirPanelDeControlSimple(tabla, botones);
		
		add(encabezado,BorderLayout.NORTH);
	    add(panel,BorderLayout.CENTER);		
	    add(panelDeControl,BorderLayout.SOUTH);

	}

	private JPanel construirPanelDeControlSimple(JTable tabla, JButton... botones) {
		JPanel panelCentral= new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		JScrollPane scroll= new JScrollPane();
		scroll.setViewportView(tabla);
		scroll.setColumnHeaderView(tabla.getTableHeader());
		
		panelCentral.add(scroll, BorderLayout.CENTER);

		JPanel menuLateral= new JPanel();
		menuLateral.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		GridLayout layout = new GridLayout(20,1);
		layout.setVgap(10);
		menuLateral.setLayout(layout);
		
        if(botones.length!=0) {
        	menuLateral.add(new JLabel("Opciones"));	
        }
		for(JButton boton : botones) {
			ControladorDeAccion[] controladores = boton.getListeners(ControladorDeAccion.class);
			for(ControladorDeAccion controlador : controladores) {
				controlador.setComponente(this);
			}
			menuLateral.add(boton);
		}
		panelCentral.add(menuLateral, BorderLayout.EAST);
        
		return panelCentral;
	}
	
	private JPanel construirPanelDeControlComplejo(String nombreTabla1,JTable tabla1,String nombreTabla2,JTable tabla2, JButton... botones) {
		JPanel panelCentral= new JPanel();
		panelCentral.setLayout(new BorderLayout());

		JPanel tablas=new JPanel();
		tablas.setLayout(new BoxLayout(tablas,BoxLayout.Y_AXIS));
		JScrollPane scroll1= new JScrollPane();
		scroll1.setViewportView(tabla1);
		scroll1.setColumnHeaderView(tabla1.getTableHeader());

		JScrollPane scroll2= new JScrollPane();
		scroll2.setViewportView(tabla2);
		scroll2.setColumnHeaderView(tabla2.getTableHeader());
		tablas.add(new JLabel(nombreTabla1));
		tablas.add(scroll1);
		tablas.add(new JLabel(nombreTabla2));
		tablas.add(scroll2);
		
		panelCentral.add(tablas, BorderLayout.CENTER);

		JPanel menuLateral= new JPanel();
		menuLateral.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		GridLayout layout = new GridLayout(20,1);
		layout.setVgap(10);
		menuLateral.setLayout(layout);
		
		
        if(botones.length!=0) {
        	menuLateral.add(new JLabel("Opciones"));	
        }
		for(JButton boton : botones) {
			ControladorDeAccion[] controladores = boton.getListeners(ControladorDeAccion.class);
			for(ControladorDeAccion controlador : controladores) {
				controlador.setComponente(this);
			}
			menuLateral.add(boton);
		}
		panelCentral.add(menuLateral, BorderLayout.EAST);
        
		return panelCentral;
	}

	private JPanel construirEncabezado(String titulo, String subtitulo) {
		JPanel panel=new JPanel();
		/**/panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));

		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		JLabel etiquetaTitulo=new  JLabel(titulo);
		/**/etiquetaTitulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		Font fuente=etiquetaTitulo.getFont();
		etiquetaTitulo.setFont(new Font(fuente.getFontName(),fuente.getStyle(),30));
		
		JLabel etiquetaSubtitulo=new  JLabel(subtitulo);
		/**/etiquetaSubtitulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panel.add(etiquetaTitulo);
		panel.add(etiquetaSubtitulo);

		return panel;
	}
    
}
