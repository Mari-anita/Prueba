package com.sena.ShoeStore.models;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity(name="producto")
public class producto {

	/*
	 * ID
	 * DOC
	 * PRI NOM
	 * SEG NOM
	 * PRI APE
	 * SEG APE
	 * CELULAR
	 * CORREO
	 * ESTADO HAB/DES
	 */
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="id_produc", nullable=false, length = 36)
	private String id_produc;

	@Column(name="nombre_produc",nullable=false,length = 255)
	private String nombre_produc;
	
	@Column(name="descripcion",nullable=false,length = 255)
	private String descripcion;
	
	@Column(name="cantidad",nullable=true,length = 20)
	private String cantidad;
	
	@Column(name="precio",nullable=false,length = 20)
	private String precio;
	
	@Column(name="iva",nullable=true,length = 5)
	private String iva;
	
	@Column(name="descuento",nullable=false,length = 15)
	private String descuento;
	
	@Column(name="estado_produc",nullable=false,length = 40)
	private String estado_produc;

	public producto() {
		super();
	}

	public producto(String id_produc, String nombre_produc, String descripcion, String cantidad,
			String precio, String iva, String descuento, String estado_produc) {
		super();
		this.id_produc = id_produc;
		this.nombre_produc = nombre_produc;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
		this.iva = iva;
		this.descuento = descuento;
		this.estado_produc = estado_produc;
	}

	public String getId_produc() {
		return id_produc;
	}

	public String getNombre_produc() {
		return nombre_produc;
	}

	public void setNombre_produc(String nombre_produc) {
		this.nombre_produc = nombre_produc;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getIva() {
		return iva;
	}

	public String setIva(String iva) {
		return iva;
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getEstado_produc() {
		return estado_produc;
	}

	public void setEstado_produc(String estado_produc) {
		this.estado_produc = estado_produc;
	}


}

