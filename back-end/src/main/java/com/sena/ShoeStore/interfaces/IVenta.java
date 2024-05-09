package com.sena.ShoeStore.interfaces;

import org.springframework.stereotype.Repository;

import com.sena.ShoeStore.models.venta;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



@Repository
public interface IVenta extends CrudRepository<venta,String>{

	//?1 es la primera variable
	@Query("SELECT v FROM venta v WHERE v.id_cliente_venta LIKE %?1%")
	
	List<venta> filtroVenta(String filtro);


	
}

