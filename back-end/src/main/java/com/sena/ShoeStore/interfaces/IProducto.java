package com.sena.ShoeStore.interfaces;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sena.ShoeStore.models.producto;

@Repository
public interface IProducto extends CrudRepository<producto,String>{
	
	//?1 es la primera variable
	@Query("SELECT p FROM producto p WHERE p.nombre LIKE %?1% OR"
			+ " p.estado_produc LIKE %?1%")
	
    List<producto> filtroProducto(String filtro);

	
}

