package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetallePedido {
	private int codigo;
	private Pedidos pedido;
	private Producto producto;
	private int cantidadSolicidad;
	private BigDecimal subtotal;
	private int cantidadRecibida;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Pedidos getPedido() {
		return pedido;
	}
	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidadSolicidad() {
		return cantidadSolicidad;
	}
	public void setCantidadSolicidad(int cantidadSolicidad) {
		this.cantidadSolicidad = cantidadSolicidad;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public int getCantidadRecibida() {
		return cantidadRecibida;
	}
	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}
	public DetallePedido() {
		super();
	}
	public DetallePedido(int codigo, Pedidos pedido, Producto producto, int cantidadSolicidad, BigDecimal subtotal,
			int cantidadRecibida) {
		super();
		this.codigo = codigo;
		this.pedido = pedido;
		this.producto = producto;
		this.cantidadSolicidad = cantidadSolicidad;
		this.subtotal = subtotal;
		this.cantidadRecibida = cantidadRecibida;
	}
	@Override
	public String toString() {
		return "DetallePedido [codigo=" + codigo + ", pedido=" + pedido + ", producto=" + producto
				+ ", cantidadSolicidad=" + cantidadSolicidad + ", subtotal=" + subtotal + ", cantidadRecibida="
				+ cantidadRecibida + "]";
	}
	

}
