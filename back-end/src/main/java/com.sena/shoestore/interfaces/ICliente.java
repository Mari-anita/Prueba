package com.sena.shoestore.interfaces;

import org.springframework.stereotype.Repository;

import com.sena.shoestore.models.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface ICliente extends CrudRepository<cliente,String>{
	
	//?1 es la primera variable
	@Query("SELECT c FROM cliente c WHERE c.nombre_cliente LIKE %?1% OR"
			+ " c.ciudad_cliente LIKE %?1% OR  c.estado_cliente ")
	
	List<cliente> filtroCliente(String filtro);


	
}

