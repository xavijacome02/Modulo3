package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

import com.krakedev.inventarios.bdd.Categoria;

public class Producto {
	private int codigo;
	private String nombre;
	private UnidadMedida udm;
	private BigDecimal precioVenta;
	private boolean tieneIVA;
	private BigDecimal coste;
	private Categoria categoria;
	private int stock;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public UnidadMedida getUdm() {
		return udm;
	}
	public void setUdm(UnidadMedida udm) {
		this.udm = udm;
	}
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	public boolean isTieneIVA() {
		return tieneIVA;
	}
	public void setTieneIVA(boolean tieneIVA) {
		this.tieneIVA = tieneIVA;
	}
	public BigDecimal getCoste() {
		return coste;
	}
	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Producto() {
		super();
	}
	public Producto(int codigo, String nombre, UnidadMedida udm, BigDecimal precioVenta, boolean tieneIVA,
			BigDecimal coste, Categoria categoria, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.udm = udm;
		this.precioVenta = precioVenta;
		this.tieneIVA = tieneIVA;
		this.coste = coste;
		this.categoria = categoria;
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", udm=" + udm + ", precioVenta=" + precioVenta
				+ ", tieneIVA=" + tieneIVA + ", coste=" + coste + ", categoria=" + categoria + ", stock=" + stock + "]";
	}
	
	
}
