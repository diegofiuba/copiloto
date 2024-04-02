/**
 * 
 */
package modelo;

/**
 * @author diego
 *
 */
public class Vehiculo {

	private String tipo;
	private String patente;
	private String marca;
	private String modelo;
	private String color;
    private boolean usado=false;
	private boolean estaDisponible=true;
	
	/**
	 * @param tipo
	 * @param marca
	 * @param modelo
	 * @param patente
	 * @param color
	 */
	public Vehiculo(String tipo, String patente,String marca, String modelo, String color) {
		super();
		this.tipo = tipo;
		this.patente = patente;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}
	
	/**
	 * @param estaDisponible
	 * @param tipo
	 * @param patente
	 * @param marca
	 * @param modelo
	 * @param color
	 * @param activo
	 */
	public Vehiculo(boolean estaDisponible, String tipo, String patente, String marca, String modelo, String color,
			boolean usado) {
		super();
		this.estaDisponible = estaDisponible;
		this.tipo = tipo;
		this.patente = patente;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.usado = usado;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the patente
	 */
	public String getPatente() {
		//System.out.print("PATENTE:"+patente);
		return patente;
	}

	/**
	 * @param patente the patente to set
	 */
	public void setPatente(String patente) {
		this.patente = patente;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * @return si esta activo
	 */
	public boolean estaUsado() {
		return usado;
	}

	/**
	 * @return the estaDisponible
	 */
	public boolean estaDisponible() {
		return estaDisponible;
	}

    public void vender() {
    	this.usar();
    	this.estaDisponible=false;
    }

	/**
	 * 
	 */
	public void usar() {
		this.usado = true;
	}
	
	
}
