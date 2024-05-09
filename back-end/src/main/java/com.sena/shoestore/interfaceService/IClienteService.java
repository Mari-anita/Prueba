package com.sena.shoestore.interfaceService;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.shoestore.models.cliente;

@Service
public interface IClienteService {
	
	public String save(cliente cliente);
	public List<cliente> findAll();
	public Optional<cliente> findOne(String id_cliente);
	public int delete(String id_cliente);
	List<cliente> filtroCliente(String filtro);
	
	
}


