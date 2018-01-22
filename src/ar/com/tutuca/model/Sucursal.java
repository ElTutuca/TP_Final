package ar.com.tutuca.model;

public class Sucursal {
	
	private int idSucursal;
	private String telefono;
	private String ubicacion;
	private String ip;
	
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	//Constructores
	public Sucursal() {
	}

	public Sucursal(int id, String telefono, String ubicacion, String ip) {
		setIdSucursal(id);
		setTelefono(telefono);
		setUbicacion(ubicacion);
		setIp(ip);
	}
}
