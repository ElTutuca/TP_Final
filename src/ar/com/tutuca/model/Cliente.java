package ar.com.tutuca.model;

public class Cliente {

	private int idCliente;
	private String nombre;
	private String nombreDeFantasia;
	private String direccion;
	private String telefono;
	private String nmroIngresosBrutos;
	private String cuit;
	private CategoriaIva catIva;

	public Cliente() {
	}

	public Cliente(int idCliente, String nombre, String nombreDeFantasia, String direccion, String telefono,
			String nmroIngresosBrutos, String cuit) {
		setIdCliente(idCliente);
		setNombre(nombre);
		setNombreDeFantasia(nombreDeFantasia);
		setDireccion(direccion);
		setTelefono(telefono);
		setNmroIngresosBrutos(nmroIngresosBrutos);
		setCuit(cuit);
	}

	public CategoriaIva getCatIva() {
		return catIva;
	}

	public String getCuit() {
		return cuit;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getNmroIngresosBrutos() {
		return nmroIngresosBrutos;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreDeFantasia() {
		return nombreDeFantasia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setCatIva(CategoriaIva catIva) {
		this.catIva = catIva;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setNmroIngresosBrutos(String nmroIngresosBrutos) {
		this.nmroIngresosBrutos = nmroIngresosBrutos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreDeFantasia(String nombreDeFantasia) {
		this.nombreDeFantasia = nombreDeFantasia;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return String.format(
				"(%s) Name: %s, NameFantasia: %s, Direccion: %s, Tel: %s, NmroIngreBrutos: %s, CUIT: %s, idCatIva: %s",
				getIdCliente(), getNombre(), getNombreDeFantasia(), getDireccion(), getTelefono(),
				getNmroIngresosBrutos(), getCuit(), getCatIva().getIdCategoriasIVA());
	}

}