package com.krakedev.inventarios.entidades;

public class UnidadMedida {
	private String nombre;
	private String descripcion;
	private CategoriaUnidadMedida categoriaUnidadMedida;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public CategoriaUnidadMedida getCategoriaUnidadMedida() {
		return categoriaUnidadMedida;
	}
	public void setCategoriaUnidadMedida(CategoriaUnidadMedida categoriaUnidadMedida) {
		this.categoriaUnidadMedida = categoriaUnidadMedida;
	}
	public UnidadMedida() {
		super();
	}
	public UnidadMedida(String nombre, String descripcion, CategoriaUnidadMedida categoriaUnidadMedida) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoriaUnidadMedida = categoriaUnidadMedida;
	}
	@Override
	public String toString() {
		return "UnidadMedida [nombre=" + nombre + ", descripcion=" + descripcion + ", categoriaUnidadMedida="
				+ categoriaUnidadMedida + "]";
	}
	
	
}
