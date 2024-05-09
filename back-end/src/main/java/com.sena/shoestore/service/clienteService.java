package com.sena.shoestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.shoestore.interfaceService.IClienteService;
import com.sena.shoestore.interfaces.ICliente;
import com.sena.shoestore.models.cliente;



@Service
public class clienteService implements IClienteService{
	@Autowired
	private ICliente data;

	@Override
	public String save(cliente cliente) {
		data.save(cliente);
		return cliente.getId_cliente();
	}

	@Override
	public List<cliente> findAll() {
		List<cliente> listaCliente=
				(List<cliente>) data.findAll();
		//(List<paciente>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <paciente>
		//- se convierte a list <paciente>
		return listaCliente;
	}
	
	@Override
	public List<cliente> filtroCliente(String filtro) {
		List<cliente> listaCliente=data.filtroCliente(filtro);
		return listaCliente;
	}
	
	@Override
	public Optional<cliente> findOne(String id_cliente) {
		Optional<cliente> cliente=data.findById(id_cliente);
		return cliente;

	}


	@Override
	public int delete(String id_cliente) {
		var cliente=data.findById(id_cliente).get();
		cliente.setEstado_cliente("Inactivo"); 
        data.save(cliente); 
		return 0;
	}
}


