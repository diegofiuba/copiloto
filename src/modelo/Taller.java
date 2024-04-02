/**
 * 
 */
package modelo;

/**
 * @author diego
 *
 */
public class Taller {

	private int id;
	private String direccion;
	private String localidad;
	private int codigoPostal;
	private String contacto;
	private boolean activo=false;
	
	/**
	 * @param id
	 * @param direccion
	 * @param localidad
	 * @param codigoPostal
	 * @param contacto
	 */
	public Taller(String direccion, String localidad, int codigoPostal, String contacto) {
		super();
		this.direccion = direccion;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.contacto = contacto;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * @return the codigoPostal
	 */
	public int getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the contacto
	 */
	public String getContacto() {
		return contacto;
	}

	/**
	 * @param contacto the contacto to set
	 */
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	/**
	 * @return si esta activo
	 */
	public boolean estaActivo() {
		return activo;
	}

	/**
	 * 
	 */
	public void activar() {
		this.activo = true;
	}	
	
}
