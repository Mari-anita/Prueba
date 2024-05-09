package com.sena.ShoeStore.models;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity(name="cliente")
public class cliente {

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
	@Column(name="id_cliente", nullable=false, length = 36)
	private String id_cliente;

	@Column(name="tipo_id",nullable=false,length = 36)
	private String tipo_id;

	@Column(name="doc_cliente",nullable=false,length = 11)
	private String doc_cliente;
	
	@Column(name="nombre_cliente",nullable=false,length = 20)
	private String nombre_cliente;
	
	@Column(name="apellido_cliente",nullable=true,length = 20)
	private String apellido_cliente;
	
	@Column(name="telefono_cliente",nullable=false,length = 20)
	private String telefono_cliente;
	
	@Column(name="direccion_cliente",nullable=true,length = 20)
	private String direccion_cliente;
	
	@Column(name="ciudad_cliente",nullable=false,length = 15)
	private String ciudad_cliente;
	
	@Column(name="correo_cliente",nullable=false,length = 100)
	private String correo_cliente;

	@Column(name="estado_cliente",nullable=false,length = 40)
	private String estado_cliente;

	public cliente() {
		super();
	}

	public cliente(String id_cliente, String tipo_id, String doc_cliente, String nombre_cliente,
			String apellido_cliente, String telefono_cliente, String direccion_cliente, String ciudad_cliente, String correo_cliente, 
			String estado_cliente) {
		super();
		this.id_cliente = id_cliente;
		this.tipo_id = tipo_id;
		this.doc_cliente = doc_cliente;
		this.nombre_cliente = nombre_cliente;
		this.apellido_cliente = apellido_cliente;
		this.telefono_cliente = telefono_cliente;
		this.direccion_cliente = direccion_cliente;
		this.ciudad_cliente = ciudad_cliente;
		this.estado_cliente = correo_cliente;
		this.estado_cliente = estado_cliente;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public String getTipo_id() {
		return tipo_id;
	}

	public void setTipo_id(String tipo_id) {
		this.tipo_id = tipo_id;
	}

	public String getDoc_cliente() {
		return doc_cliente;
	}

	public void setDoc_cliente(String doc_cliente) {
		this.doc_cliente = doc_cliente;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellido_cliente() {
		return apellido_cliente;
	}

	public void setApellido_cliente(String apellido_cliente) {
		this.apellido_cliente = apellido_cliente;
	}

	public void getTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}

	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}

	public String getDireccion_cliente() {
		return direccion_cliente;
	}

	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}

	public String getCiudad_cliente() {
		return ciudad_cliente;
	}

	public void setCiudad_cliente(String ciudad_cliente) {
		this.ciudad_cliente = ciudad_cliente;
	}

	public String getCorreo_cliente() {
		return correo_cliente;
	}

	public void setCorreo_cliente(String correo_cliente) {
		this.correo_cliente = correo_cliente;
	}

	public String getEstado_cliente() {
		return estado_cliente;
	}

	public void setEstado_cliente(String estado_cliente) {
		this.estado_cliente = estado_cliente;
	}


}

