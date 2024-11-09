package com.krakedev.inventarios.entidades;

public class Categoria {
	private int codigoCat;
	private String nombre;
	private Categoria codigoPadre;
	public int getCodigoCat() {
		return codigoCat;
	}
	public void setCodigoCat(int codigoCat) {
		this.codigoCat = codigoCat;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Categoria getCodigoPadre() {
		return codigoPadre;
	}
	public void setCodigoPadre(Categoria codigoPadre) {
		this.codigoPadre = codigoPadre;
	}
	public Categoria() {
		super();
	}
	public Categoria(int codigoCat, String nombre, Categoria codigoPadre) {
		super();
		this.codigoCat = codigoCat;
		this.nombre = nombre;
		this.codigoPadre = codigoPadre;
	}
	@Override
	public String toString() {
		return "Categoria [codigoCat=" + codigoCat + ", nombre=" + nombre + ", codigoPadre=" + codigoPadre + "]";
	}
	
}
