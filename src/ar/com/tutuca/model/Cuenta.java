package ar.com.tutuca.model;

import java.sql.Timestamp;

public class Cuenta {

	private String email = null;
	private String usuario;
	private String password;
	private Timestamp createTime;
	private int puntos;
	private int idCuenta;

	// Builders
	public Cuenta() {
	}

	public Cuenta(String email, String usuario, String password, Timestamp createTime, int puntos, int idCuenta) {
		super();
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.createTime = createTime;
		this.puntos = puntos;
		this.idCuenta = idCuenta;
	}

	// Getters
	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getEmail() {
		return email;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public String getPassword() {
		return password;
	}

	public int getPuntos() {
		return puntos;
	}

	public String getUsuario() {
		return usuario;
	}

	// Setters
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	// To-String
	@Override
	public String toString() {
		return String.format("(%s) Usuario: %s, Pass: %s, Ptos: %s, Email: %s, CreateTime: %s", getIdCuenta(),
				getUsuario(), getPassword(), getPuntos(), getEmail(), getCreateTime());
	}

}
