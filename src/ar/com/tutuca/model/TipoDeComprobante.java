package ar.com.tutuca.model;

public class TipoDeComprobante {

	private int idTipoDeComprov;
	private String nombre;
	private String letra;
	private String abreviatura;

	// Constructores
	public TipoDeComprobante() {
	}

	public TipoDeComprobante(int idTipoDeComprov, String nombre, String letra, String abreviatura) {
		setIdTipoDeComprov(idTipoDeComprov);
		setNombre(nombre);
		setLetra(letra);
		setAbreviatura(abreviatura);
	}

	// Getters
	public String getAbreviatura() {
		return abreviatura;
	}

	public int getIdTipoDeComprov() {
		return idTipoDeComprov;
	}

	public String getLetra() {
		return letra;
	}

	public String getNombre() {
		return nombre;
	}

	// Setters
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public void setIdTipoDeComprov(int idTipoDeComprov) {
		this.idTipoDeComprov = idTipoDeComprov;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.format("(%s) Nombre: %s, Letra: %s, Abreviatura: %s", getIdTipoDeComprov(), getNombre(),
				getLetra(), getAbreviatura());
	}

}
