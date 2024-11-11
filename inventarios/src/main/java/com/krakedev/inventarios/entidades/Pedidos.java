package com.krakedev.inventarios.entidades;

import java.util.ArrayList;
import java.util.Date;

public class Pedidos {
	private int codigo;
	private Proveedores proveedor;
	private Date fecha;
	private EstadoPedidos estado;
	
	private ArrayList<DetallePedido> detalles;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Proveedores getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public EstadoPedidos getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedidos estado) {
		this.estado = estado;
	}
	
	public ArrayList<DetallePedido> getDetalles() {
		return detalles;
	}
	public void setDetalles(ArrayList<DetallePedido> detalles) {
		this.detalles = detalles;
	}
	public Pedidos() {
		super();
	}
	public Pedidos(int codigo, Proveedores proveedor, Date fecha, EstadoPedidos estado,
			ArrayList<DetallePedido> detalles) {
		super();
		this.codigo = codigo;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.estado = estado;
		this.detalles = detalles;
	}
	@Override
	public String toString() {
		return "Pedidos [codigo=" + codigo + ", proveedor=" + proveedor + ", fecha=" + fecha + ", estado=" + estado
				+ ", detalles=" + detalles + "]";
	}

	
}
