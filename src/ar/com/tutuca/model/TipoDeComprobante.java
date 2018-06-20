package ar.com.tutuca.model;

import ar.com.tutuca.extras.GenericModel;

public class TipoDeComprobante implements GenericModel {

	private int idTipoDeComprob;
	private String nombre;
	private String letra;
	private String abreviatura;
	
	@Override
	public String toString() {
		return String.format("(%s) Nombre: %s, Letra: %s, Abreviatura: %s", getIdTipoDeComprob(), getNombre(),
				getLetra(), getAbreviatura());
	}

	// Constructores
	public TipoDeComprobante() {
	}
	
	public TipoDeComprobante(String nombre, String letra, String abreviatura) {
		setNombre(nombre);
		setLetra(letra);
		setAbreviatura(abreviatura);
	}

	public TipoDeComprobante(int idTipoDeComprob, String nombre, String letra, String abreviatura) {
		setIdTipoDeComprob(idTipoDeComprob);
		setNombre(nombre);
		setLetra(letra);
		setAbreviatura(abreviatura);
	}

	// Getters
	public String getAbreviatura() {
		return abreviatura;
	}

	public int getIdTipoDeComprob() {
		return idTipoDeComprob;
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

	public void setIdTipoDeComprob(int idTipoDeComprob) {
		this.idTipoDeComprob = idTipoDeComprob;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Object[] getFieldsValues() {
		Object[] r = {getNombre(), getAbreviatura(),getLetra()};
		return r;
	}

	@Override
	public String[] getFieldNames() {
		String[] r = {"Nombre","Abreviatura","Letra"};
		return r;
	}

}
