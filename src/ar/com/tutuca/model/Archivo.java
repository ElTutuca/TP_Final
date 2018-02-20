package ar.com.tutuca.model;

public class Archivo {

	private int idArchivo;
	private String path;
	private String nombre;
	private String mimeType = null;
	private int tamaño;

	// Constructor
	public Archivo(int idArchivo, String path, String nombre, String mimeType, int tamaño) {
		setIdArchivo(idArchivo);
		setPath(path);
		setNombre(nombre);
		setMimeType(mimeType);
		setTamaño(tamaño);
	}
	
	public Archivo() {
	}

	public int getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(int idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public int getTamaño() {
		return tamaño;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

}
