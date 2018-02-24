package ar.com.tutuca.model;

public class Cliente {

	// TODO Preguntar a Mariano como crearia un Cliente nuevo

	// TODO Quitar excepciones a todos los modelos!

	private int idCliente;
	private String nombre;
	private String apellido;
	private int edad;
	private String genero;
	private String direccion;
	private String telefono;
	private String dni;
	private Cuenta cuenta = null;

	public Cliente() {
	}

	public Cliente(int idCliente, String nombre, String apellido, int edad, String genero, String direccion,
			String telefono, String dni, Cuenta cuenta) {
		setIdCliente(idCliente);
		setNombre(nombre);
		setApellido(apellido);
		setEdad(edad);
		setGenero(genero);
		setDireccion(direccion);
		setTelefono(telefono);
		setDni(dni);
		setCuenta(cuenta);
	}

	// Getters
	public Cuenta getCuenta() {
		return cuenta;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getDni() {
		return dni;
	}

	public int getEdad() {
		return edad;
	}

	public String getGenero() {
		return genero;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	// Setters
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDireccion(String direccion){
		this.direccion = direccion;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return String.format(
				"(%s) Name: %s, Apellido: %s, Edad: %s, Genero: %s, Direccion: %s, Tel: %s, DNI: %s, idCuenta: %s",
				getIdCliente(), getNombre(), getApellido(), getEdad(), getGenero(), getDireccion(), getTelefono(),
				getDni(), mostrarCuenta());
	}

	public String mostrarCuenta() {
		return getCuenta() == null ? "null" : (Integer.toString(getCuenta().getIdCuenta()));
	}

}
