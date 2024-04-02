/**
 * 
 */
package modelo;

/**
 * @author diego
 *
 */
public class Cliente {

	private int id;
	private String nombre;
	private String apellido;
	private Number dni;
	private String contacto;
	private boolean usuario=false;
	
	/**
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param contacto
	 */
	public Cliente(String nombre, String apellido, Number dni, String contacto) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contacto = contacto;
	}
	
	/**
	 * 
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param contacto
	 * @param activo
	 */
	public Cliente(String nombre, String apellido, Number dni, String contacto, boolean usuario
			) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contacto = contacto;
		this.usuario=usuario;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the dni
	 */
	public Number getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(Number dni) {
		this.dni = dni;
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
	public boolean esUsuario() {
		return usuario;
	}

	/**
	 * 
	 */
	public void convertirEnUsuario() {
		this.usuario = true;
	}
	
	public String[] obtenerValores() {
		String[] valores={this.nombre,this.apellido,this.dni.toString(),this.contacto};
		return valores;
	}
	
	
}
