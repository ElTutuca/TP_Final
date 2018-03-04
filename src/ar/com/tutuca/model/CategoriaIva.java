package ar.com.tutuca.model;

import java.math.BigDecimal;

import ar.com.tutuca.extras.GenericModel;

public class CategoriaIva implements GenericModel {

	private int idCategoriasIVA;
	private String nombre;
	private BigDecimal tasa;
	private boolean discrimina;

	@Override
	public String toString() {
		return getNombre();
	}

	// Constructores
	public CategoriaIva() {
	}

	public CategoriaIva(String nombre, BigDecimal tasa, boolean discrimina) {
		setNombre(nombre);
		setTasa(tasa);
		setDiscrimina(discrimina);
	}

	public CategoriaIva(int idCategoriasIVA, String nombre, BigDecimal tasa, boolean discrimina) {
		setIdCategoriasIVA(idCategoriasIVA);
		setNombre(nombre);
		setTasa(tasa);
		setDiscrimina(discrimina);
	}

	// Getters
	public int getIdCategoriasIVA() {
		return idCategoriasIVA;
	}

	public String getNombre() {
		return nombre;
	}

	public BigDecimal getTasa() {
		return tasa;
	}

	public boolean isDiscrimina() {
		return discrimina;
	}

	// Setters
	public void setDiscrimina(boolean discrimina) {
		this.discrimina = discrimina;
	}

	public void setIdCategoriasIVA(int idCategoriasIVA) {
		this.idCategoriasIVA = idCategoriasIVA;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}

	@Override
	public Object[] getFieldsValues() {
		Object[] r = { getIdCategoriasIVA(), getNombre(), getTasa(), isDiscrimina() };
		return r;

	}

}
