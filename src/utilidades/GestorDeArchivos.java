package utilidades;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;

import modelo.Cliente;
import modelo.ErrorDeInsercion;
import modelo.Vehiculo;
import modelo.Venta;
import presentacion.ModeloTablaDeClientes;
import presentacion.ModeloTablaDeVehiculos;
import presentacion.ModeloTablaDeVentas;

/**
 * 
 */

/**
 * @author diego
 *
 */
public class GestorDeArchivos {

	private String ubicacion;
	private ModeloTablaDeClientes modeloTablaDeClientes;
	private ModeloTablaDeVehiculos modeloTablaDeVehiculos;
	private ModeloTablaDeVentas modeloTablaDeVentas;
	private File archivoDeClientes;
	private File archivoDeVehiculos;
	private File archivoDeVentas;
	
	/**
	 * @param archivo
	 * @param modeloTablaDeClientes
	 * @param modeloTablaDeVhiculos
	 * @param modeloTablaDeVentas
	 */
	public GestorDeArchivos(File archivo, ModeloTablaDeClientes modeloTablaDeClientes,
			ModeloTablaDeVehiculos modeloTablaDeVhiculos, ModeloTablaDeVentas modeloTablaDeVentas) {
		super();
		this.ubicacion = archivo.getAbsolutePath();
		archivoDeClientes=new File(ubicacion+System.getProperty("file.separator")+"clientes.CSV");
		archivoDeVehiculos=new File(ubicacion+System.getProperty("file.separator")+"vehiculos.CSV");
		archivoDeVentas=new File(ubicacion+System.getProperty("file.separator")+"ventas.CSV");
		if(!archivo.exists()) {
			//crea un archivo con el nombre del proyecto
			archivo.mkdirs();
			try {
				//creo los archivos CSV
				//archivoDeClientes=new File(ubicacion+System.getProperty("file.separator")+"clientes.CSV");
				archivoDeClientes.createNewFile();
				//archivoDeVehiculos=new File(ubicacion+System.getProperty("file.separator")+"vehiculos.CSV");
				archivoDeVehiculos.createNewFile();
				//archivoDeVentas=new File(ubicacion+System.getProperty("file.separator")+"ventas.CSV");
				archivoDeVentas.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
		this.archivoDeClientes.setExecutable(true);
		this.archivoDeClientes.setReadable(true);
		this.archivoDeVehiculos.setExecutable(true);
		this.archivoDeVehiculos.setReadable(true);
		this.archivoDeVentas.setExecutable(true);
		this.archivoDeVentas.setReadable(true);
		
		this.modeloTablaDeClientes = modeloTablaDeClientes;
		this.modeloTablaDeVehiculos = modeloTablaDeVhiculos;
		this.modeloTablaDeVentas = modeloTablaDeVentas;
	}

	public void abrir() {
		File archivo=new File(ubicacion);
		if(archivo.exists()) {
			BufferedReader lector1=null;
			try {
				this.archivoDeClientes.setWritable(true);
				this.archivoDeVehiculos.setWritable(true);
				this.archivoDeVentas.setWritable(true);

				
				lector1=new BufferedReader(new FileReader(this.archivoDeClientes));
		        String linea1;
				linea1 = lector1.readLine();
				linea1 = lector1.readLine();

		    while(linea1!=null) {
			   //fracciona lo leido en varias partes (el fraccionamiento está dado por el simbolo ;)
			   String[] campos1=linea1.split(";",/*5*/6);
			   linea1=lector1.readLine();
			   this.modeloTablaDeClientes.agregarCliente(new Cliente(campos1[1],campos1[2],NumberFormat.getIntegerInstance().parse(campos1[3]),campos1[4],Boolean.parseBoolean(campos1[5])));
		    }

			BufferedReader lector2=null;
			lector2=new BufferedReader(new FileReader(this.archivoDeVehiculos));
		    String linea2=lector2.readLine();
			linea2 = lector2.readLine();

		    while(linea2!=null) {
			   //fracciona lo leido en varias partes (el fraccionamiento está dado por el simbolo ;)
			   String[] campos2=linea2.split(";",7);
			   linea2=lector2.readLine();
			   this.modeloTablaDeVehiculos.agregarVehiculo(new Vehiculo(Boolean.parseBoolean(campos2[0]),campos2[1],campos2[2],campos2[3],campos2[4],campos2[5],Boolean.parseBoolean(campos2[6])));
		    }
		    
			BufferedReader lector3=null;
			lector3=new BufferedReader(new FileReader(this.archivoDeVentas));
		    String linea3=lector3.readLine();
			linea3 = lector3.readLine();

		    while(linea3!=null) {
			   //fracciona lo leido en varias partes (el fraccionamiento está dado por el simbolo ;)
			   String[] campos3=linea3.split(";",12);
			   linea3=lector3.readLine();
			   Cliente cliente=this.modeloTablaDeClientes.obtenerClientePorId(Integer.parseInt(campos3[0]));
			   //System.out.println("PATENTE PARA BUSCAR AUTO VENDIDO:"+campos3[6]);
			   Vehiculo vehiculo=this.modeloTablaDeVehiculos.obtenerVehiculoPorPatente(campos3[6]);
			   
			   this.modeloTablaDeVentas.agregarVenta(new Venta(cliente,vehiculo,Integer.parseInt(campos3[10]),/*DateFormat.getDateInstance()*/LocalDate.parse(campos3[11])));
		    }
		lector1.close();
		lector2.close();
		lector3.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ErrorDeInsercion e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		}
	}


	
	public void guardar() {
		
		
		//PrintWriter escritorDeClientes;
		try {
		PrintWriter	escritorDeClientes = new PrintWriter(this.archivoDeClientes);

	    String linea1=new String();
	    escritorDeClientes.println(modeloTablaDeClientes.obtenerEncabezado(";")+";Usuario");
	    int indice1=0;
	    while(indice1<modeloTablaDeClientes.getRowCount()) {
	    	linea1=modeloTablaDeClientes.getValueAt(indice1, 0)+";"
	               +modeloTablaDeClientes.getValueAt(indice1, 1)+";"
	               +modeloTablaDeClientes.getValueAt(indice1, 2)+";"
	               +modeloTablaDeClientes.getValueAt(indice1, 3)+";"
	               +modeloTablaDeClientes.getValueAt(indice1, 4)+";"
	    	       +modeloTablaDeClientes.esUsuario(indice1);
		    escritorDeClientes.println(linea1);
		    indice1++;

	    }
	    escritorDeClientes.close();
	    
		PrintWriter escritorDeVehiculos=new PrintWriter(this.archivoDeVehiculos);
	    String linea2=new String();
	    escritorDeVehiculos.println(modeloTablaDeVehiculos.obtenerEncabezado(";")+";Usado");
	    int indice2=0;
	    while(indice2<modeloTablaDeVehiculos.getRowCount()) {
	    	linea2=modeloTablaDeVehiculos.getValueAt(indice2, 0)+";"
	               +modeloTablaDeVehiculos.getValueAt(indice2, 1)+";"
	               +modeloTablaDeVehiculos.getValueAt(indice2, 2)+";"
	               +modeloTablaDeVehiculos.getValueAt(indice2, 3)+";"
	               +modeloTablaDeVehiculos.getValueAt(indice2, 4)+";"
                   +modeloTablaDeVehiculos.getValueAt(indice2, 5)+";"
                   +modeloTablaDeVehiculos.estaUsado(indice2);
	    	escritorDeVehiculos.println(linea2);
		    indice2++;

	    }
	    escritorDeVehiculos.close();

		PrintWriter escritorDeVentas=new PrintWriter(this.archivoDeVentas);
	    String linea3=new String();
	    escritorDeVentas.println(modeloTablaDeVentas.obtenerEncabezado(";"));
	    int indice3=0;
	    while(indice3<modeloTablaDeVentas.getRowCount()) {
	    	linea3=modeloTablaDeVentas.getValueAt(indice3, 0)+";"
	               +modeloTablaDeVentas.getValueAt(indice3, 1)+";"
	               +modeloTablaDeVentas.getValueAt(indice3, 2)+";"
	               +modeloTablaDeVentas.getValueAt(indice3, 3)+";"
	               +modeloTablaDeVentas.getValueAt(indice3, 4)+";"
                   +modeloTablaDeVentas.getValueAt(indice3, 5)+";"
                   +modeloTablaDeVentas.getValueAt(indice3, 6)+";"
                   +modeloTablaDeVentas.getValueAt(indice3, 7)+";"
                   +modeloTablaDeVentas.getValueAt(indice3, 8)+";"
                   +modeloTablaDeVentas.getValueAt(indice3, 9)+";"
                   +modeloTablaDeVentas.getValueAt(indice3, 10)+";"
                   +modeloTablaDeVentas.getValueAt(indice3, 11);
            
	    	escritorDeVentas.println(linea3);
		    indice3++;

	    }
	    escritorDeVentas.close();
		this.archivoDeClientes.setWritable(false);
		this.archivoDeVehiculos.setWritable(false);
		this.archivoDeVentas.setWritable(false);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
