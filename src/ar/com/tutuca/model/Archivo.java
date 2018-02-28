package ar.com.tutuca.model;

public class Archivo {

	private int idArchivo;
	private String path;
	private String nombre;
	private String mimeType = null;
	private int tamaño;

	@Override
	public String toString() {
		return String.format("(%s) PATH: %s, Name: %s, mimeType: %s, Size: %s", getIdArchivo(), getPath(), getNombre(),
				getMimeType(), getTamaño());
	}

	// Constructores
	public Archivo(int idArchivo, String path, String nombre, String mimeType, int tamaño) {
		setIdArchivo(idArchivo);
		setPath(path);
		setNombre(nombre);
		setMimeType(mimeType);
		setTamaño(tamaño);
	}

	public Archivo(String path, String nombre, String mimeType, int tamaño) {
		setPath(path);
		setNombre(nombre);
		setMimeType(mimeType);
		setTamaño(tamaño);
	}

	public Archivo() {
	}

	// Getters
	public int getIdArchivo() {
		return idArchivo;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPath() {
		return path;
	}

	public int getTamaño() {
		return tamaño;
	}

	// Setters
	public void setIdArchivo(int idArchivo) {
		this.idArchivo = idArchivo;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

}
