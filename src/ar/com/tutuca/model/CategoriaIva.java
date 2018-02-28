package ar.com.tutuca.model;

import java.math.BigDecimal;

public class CategoriaIva {

	private int idCategoriasIVA;
	private String nombre;
	private BigDecimal tasa;
	private boolean discrimina;
	
	@Override
	public String toString() {
		return String.format("(%s) Name: %s, Tasa:%s%, Discrimina: %s", getIdCategoriasIVA(), getNombre(), getTasa(),
				isDiscrimina());
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

}
