package presentacion;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import utilidades.GestorDeArchivos;

/**
 * 
 */

/**
 * @author diego
 *
 */
public class Aplicacion extends JFrame{
	
	private GestorDeArchivos gestorDeArchivos;

	/**
	 * 
	 */
	public Aplicacion() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(Locale.getDefault());
		ModeloTablaDeClientes modeloTablaDeClientes=new ModeloTablaDeClientes();		
		ModeloTablaDeVehiculos modeloTablaDeVehiculos=new ModeloTablaDeVehiculos();
		ModeloTablaDeVentas modeloTablaDeVentas=new ModeloTablaDeVentas();
		
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.iniciar(modeloTablaDeClientes, modeloTablaDeVehiculos, modeloTablaDeVentas);
		aplicacion.construirInterfazGrafica(modeloTablaDeClientes, modeloTablaDeVehiculos, modeloTablaDeVentas);
	}

	private void iniciar(ModeloTablaDeClientes modeloTablaDeClientes, ModeloTablaDeVehiculos modeloTablaDeVehiculos, ModeloTablaDeVentas modeloTablaDeVentas) {
		String ruta = new String(System.getProperty("user.home")+System.getProperty("file.separator")+".Copiloto");
		
		gestorDeArchivos=new GestorDeArchivos(new File(ruta), modeloTablaDeClientes, modeloTablaDeVehiculos, modeloTablaDeVentas);
		gestorDeArchivos.abrir();
	}

	private void construirInterfazGrafica(ModeloTablaDeClientes modeloTablaDeClientes, ModeloTablaDeVehiculos modeloTablaDeVehiculos, ModeloTablaDeVentas modeloTablaDeVentas) {
        
		ImageIcon imagen= new ImageIcon("src/copiloto.jpg");
		setIconImage(imagen.getImage());
		
		ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion=new ControladorDeCierreDeAplicacion(this, gestorDeArchivos);
		
		JPanel panelCentral=new JPanel();
		panelCentral.setLayout(new CardLayout());
        
		JPanel panelInformativo=new PanelDeControl(
				"Copiloto",
				//"Copiloto es una aplicación que permite realizar varias tareas para administrar la venta de vehículos",
				"Copiloto es una aplicación que permite realizar varias tareas relacionadas con la venta de vehículos",
				" <<  Administrar los clientes",
				" <<  Administrar los vehículos",
				" <<  Realizar las ventas",
				" <<  Consultar los ingresos"
				);
		
		panelCentral.add(panelInformativo,"Copiloto");	

		JButton verCopiloto = crearBoton("src/copiloto.jpg","Copiloto",Color.ORANGE);
		
		verCopiloto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout=(CardLayout) panelCentral.getLayout();
				layout.show(panelCentral, "Copiloto");
			}});
		
		JPanel listaClientes = construirListaDeClientes(modeloTablaDeClientes,controladorDeCierreDeAplicacion);
		panelCentral.add(listaClientes,"Clientes");	

		JButton verClientes = crearBoton("Clientes",Color.ORANGE);
		
		verClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout=(CardLayout) panelCentral.getLayout();
				layout.show(panelCentral, "Clientes");
			}});
		
		JPanel listaVehiculos = construirListaDeVehiculos(modeloTablaDeVehiculos,controladorDeCierreDeAplicacion);
		panelCentral.add(listaVehiculos,"Vehiculos");	
		
		JButton verVehiculos=crearBoton("Vehículos", Color.ORANGE);
		
		verVehiculos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout=(CardLayout) panelCentral.getLayout();
				layout.show(panelCentral, "Vehiculos");
			}});
		
		JPanel listaVentas = construirPanelDeVentas(modeloTablaDeVehiculos,modeloTablaDeClientes,modeloTablaDeVentas,controladorDeCierreDeAplicacion);
		
		panelCentral.add(listaVentas,"Ventas");	
		
		JButton verVentas=crearBoton("Ventas",Color.ORANGE);
		
		verVentas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout=(CardLayout) panelCentral.getLayout();
				layout.show(panelCentral, "Ventas");
			}});
		
		JTable tablaDeIngresos = construirTabla(modeloTablaDeVentas);
		
		tablaDeIngresos.getTableHeader().setReorderingAllowed(false);
		InformeDeVentas informeDeIngresos=new InformeDeVentas(modeloTablaDeVentas);		

		JPanel listaIngresos=new PanelDeControl(
				"Ingresos",
				"Acá podés consultar los ingresos por vehículos vendidos", 
				informeDeIngresos,
				tablaDeIngresos
				);
		
		panelCentral.add(listaIngresos,"Ingresos");	
		
		JButton verIngresos=crearBoton("Ingresos",Color.ORANGE);
		
		verIngresos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout=(CardLayout) panelCentral.getLayout();
				layout.show(panelCentral, "Ingresos");
			}});

		JButton guardar=crearBoton("Guardar",Color.ORANGE);
		
		guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gestorDeArchivos.guardar();
			}});
		
		
		JPanel menuLateral=new JPanel();
		menuLateral.setLayout(new BoxLayout(menuLateral,BoxLayout.Y_AXIS));
		/**/menuLateral.add(verCopiloto);
		menuLateral.add(verClientes);
		menuLateral.add(verVehiculos);
		menuLateral.add(verVentas);
		menuLateral.add(verIngresos);
		menuLateral.add(guardar);
		menuLateral.setBackground(Color.BLACK);
		
		JFrame ventana=new JFrame();
		ventana.setLayout(new BorderLayout());
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setVisible(true);

		ventana.setTitle("Copiloto");
				
		ventana.add(panelCentral,BorderLayout.CENTER);
		ventana.add(menuLateral, BorderLayout.WEST);		
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(controladorDeCierreDeAplicacion);
		ventana.pack();
	}

	private JPanel construirPanelDeVentas(ModeloTablaDeVehiculos modeloTablaDeVehiculos,ModeloTablaDeClientes modeloTablaDeClientes,ModeloTablaDeVentas modeloTablaDeVentas, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		JTable tablaDeVehiculos = construirTabla(modeloTablaDeVehiculos);	
		tablaDeVehiculos.setDefaultRenderer(Boolean.class, new RenderBooleano(Color.ORANGE));
		tablaDeVehiculos.getTableHeader().setReorderingAllowed(false);
		
		JTable tablaDeClientes = construirTabla(modeloTablaDeClientes);
		tablaDeClientes.getTableHeader().setReorderingAllowed(false);		
		
		String[] nombresDeCamposDeVenta={"Monto","Fecha"};
		Class<?>[] tipoDeCamposDeVenta={Float.class,LocalDate.class};
		Formulario formularioDeVentaDeVehiculo=new Formulario(nombresDeCamposDeVenta,tipoDeCamposDeVenta);
		
		
		ControladorDeAccion controladorDeVentaDeVehiculo=new ControladorDeVentaDeVehiculo( 
				                                                 formularioDeVentaDeVehiculo,
				                                                 tablaDeVehiculos,
				                                                 tablaDeClientes,
				                                                 modeloTablaDeVehiculos,
				                                                 modeloTablaDeClientes,
				                                                 modeloTablaDeVentas,
				                                                 controladorDeCierreDeAplicacion);
		
		JButton botonVender=crearBoton("Vender",Color.ORANGE,Color.BLACK);
		botonVender.addActionListener(controladorDeVentaDeVehiculo);
		
		JPanel panelDeVentas=new PanelDeControl(
				"Ventas", 
				"Acá podés seleccionar el vehiculo y cliente para realizar la venta", 
				"Vehiculos",
				tablaDeVehiculos,
				"Clientes",
				tablaDeClientes,
				botonVender
				);
		return panelDeVentas;
	}

	private JTable construirTabla(TableModel modeloTablaDeVentas) {
		JTable tabla=new JTable(modeloTablaDeVentas);
		tabla.setSelectionBackground(Color.ORANGE);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return tabla;
	}

	private JButton crearBoton(String rutaImagen,String texto,Color colorDeFrente) {
		JButton boton=new JButton(texto);
		ImageIcon defaultIcon = new ImageIcon(rutaImagen);
		Image imagen = defaultIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imagen));
		boton.setHorizontalTextPosition(SwingConstants.CENTER);
		boton.setVerticalTextPosition(SwingConstants.BOTTOM);
		//boton.setBackground(Color.BLACK);
		boton.setForeground(colorDeFrente);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
		boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return boton;
	}
	
	private JButton crearBoton(String texto,Color colorDeFrente) {
		JButton boton=new JButton(texto);
		//boton.setBackground(Color.BLACK);
		boton.setForeground(colorDeFrente);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
		boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return boton;
	}

	private JPanel construirListaDeClientes(ModeloTablaDeClientes modeloTablaDeClientes, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		JTable tablaDeClientes= construirTabla(modeloTablaDeClientes);
		tablaDeClientes.getTableHeader().setReorderingAllowed(false);
		
		String[] nombresDeCamposDeClientes={"Nombre","Apellido","DNI","Contacto"};
		Class<?>[] tipoDeCamposDeClientes={String.class,String.class,Long.class,String.class};
		Formulario formularioDeNuevoCliente=new Formulario(nombresDeCamposDeClientes,tipoDeCamposDeClientes);
		Formulario formularioDeEdicionDeCliente=new Formulario(nombresDeCamposDeClientes,tipoDeCamposDeClientes);
		
		ControladorDeAccion controladorDeInsercionDeCliente=new ControladorDeInsercionDeCliente( formularioDeNuevoCliente, modeloTablaDeClientes, controladorDeCierreDeAplicacion);
		ControladorDeAccion controladorDeEdicionDeCliente=new ControladorDeEdicion(formularioDeEdicionDeCliente,tablaDeClientes, modeloTablaDeClientes, controladorDeCierreDeAplicacion);
		ControladorDeAccion controladorDeBorradoDeCliente=new ControladorDeBorrado( "El cliente",tablaDeClientes,modeloTablaDeClientes,controladorDeCierreDeAplicacion);
		
		JButton botonAgregar=crearBoton("Agregar",Color.ORANGE,Color.BLACK);
		botonAgregar.addActionListener(controladorDeInsercionDeCliente);
		JButton botonEditar=crearBoton("Editar",Color.ORANGE,Color.BLACK);
		botonEditar.addActionListener(controladorDeEdicionDeCliente);
		JButton botonBorrar=crearBoton("Borrar",Color.ORANGE,Color.BLACK);
		botonBorrar.addActionListener(controladorDeBorradoDeCliente);
		
		JPanel listaClientes=new PanelDeControl(
				"Clientes", 
				"Acá podés administrar los clientes de tu empresa, agregandolos,modificandolos,o borrandolos", 
				tablaDeClientes, 
				botonAgregar,
				botonEditar,
				botonBorrar);
		return listaClientes;
	}

	private JPanel construirListaDeVehiculos(ModeloTablaDeVehiculos modeloTablaDeVehiculos, ControladorDeCierreDeAplicacion controladorDeCierreDeAplicacion) {
		
		JTable tablaDeVehiculos=construirTabla(modeloTablaDeVehiculos);
		tablaDeVehiculos.setDefaultRenderer(Boolean.class, new RenderBooleano(Color.ORANGE));
		tablaDeVehiculos.getTableHeader().setReorderingAllowed(false);

		
		String[] nombresDeCamposDeVehiculos={"Tipo","Patente","Marca","Modelo","Color"};
		Class<?>[] tipoDeCamposDeVehiculos={String.class,String.class,String.class,String.class,String.class};
		Formulario formularioDeNuevoVehiculo=new Formulario(nombresDeCamposDeVehiculos,tipoDeCamposDeVehiculos);

		Formulario formularioDeEdicionDeVehiculo=new Formulario(nombresDeCamposDeVehiculos,tipoDeCamposDeVehiculos);
		
		ControladorDeAccion controladorDeInsercionDeVehiculo=new ControladorDeInsercionDeVehiculo(formularioDeNuevoVehiculo, modeloTablaDeVehiculos, controladorDeCierreDeAplicacion);
		ControladorDeAccion controladorDeEdicionDeVehiculo=new ControladorDeEdicionDeVehiculo(formularioDeEdicionDeVehiculo,tablaDeVehiculos, modeloTablaDeVehiculos, controladorDeCierreDeAplicacion);		
		ControladorDeAccion controladorDeBorradoDeVehiculo=new ControladorDeBorrado("El vehiculo", tablaDeVehiculos,modeloTablaDeVehiculos,controladorDeCierreDeAplicacion);

		JButton botonAgregar=crearBoton("Agregar",Color.ORANGE,Color.BLACK);
		botonAgregar.addActionListener(controladorDeInsercionDeVehiculo);
		JButton botonEditar=crearBoton("Editar",Color.ORANGE,Color.BLACK);
		botonEditar.addActionListener(controladorDeEdicionDeVehiculo);		
		JButton botonBorrar=crearBoton("Borrar",Color.ORANGE,Color.BLACK);
		botonBorrar.addActionListener(controladorDeBorradoDeVehiculo);

		
		JPanel listaVehiculos=new PanelDeControl(
				"Vehículos", 
				"Acá podés administrar los vehículos de tu empresa, agregandolos,modificandolos,o borrandolos", 
				tablaDeVehiculos, 
				botonAgregar,
				botonEditar,
				botonBorrar);
		return listaVehiculos;
	}
	
	private JButton crearBoton(String texto,Color colorDeFondo,Color colorDeFrente) {
		JButton boton=new JButton(texto);
		boton.setBackground(colorDeFondo);
		boton.setForeground(colorDeFrente);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		//boton.setContentAreaFilled(false);
		boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return boton;
	}
	
}
