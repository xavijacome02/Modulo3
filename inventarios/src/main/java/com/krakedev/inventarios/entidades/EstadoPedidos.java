package com.krakedev.inventarios.entidades;

public class EstadoPedidos {
	private int codigo;
	private String descripcion;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public EstadoPedidos() {
		super();
	}
	public EstadoPedidos(int codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "EstadoPedidos [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
}
