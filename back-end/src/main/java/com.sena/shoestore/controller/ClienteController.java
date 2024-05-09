package com.sena.shoestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.shoestore.interfaceService.IClienteService;
import com.sena.shoestore.model.Cliente;

@RequestMapping("/api/shoes/cliente")
@RestController
@CrossOrigin
public class ClienteController { 
	
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("cliente") Cliente cliente) {

        // Verificar que no exista el documento de identidad
        var listaCliente = clienteService.findAll()
                .stream().filter(clienteExistente -> clienteExistente.getIdCliente()
                        .equals(cliente.getIdCliente()));
        if (listaCliente.count() != 0) {
            return new ResponseEntity<>("El cliente ya existe", HttpStatus.BAD_REQUEST);
        }
        
        // Añadir campos obligatorios
        if (cliente.getTipoId().isEmpty()) {
            return new ResponseEntity<>("El tipo de identificación es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getDocCliente().isEmpty()) {
            return new ResponseEntity<>("El número de documento es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getNombreCliente().isEmpty()) {
            return new ResponseEntity<>("El nombre del cliente es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getApellidoCliente().isEmpty()) {
            return new ResponseEntity<>("El apellido del cliente es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getDireccionCliente().isEmpty()) {
            return new ResponseEntity<>("La dirección es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (cliente.getCiudadCliente().isEmpty()) {
            return new ResponseEntity<>("La ciudad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (cliente.getCorreoCliente().isEmpty()) {
            return new ResponseEntity<>("El correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (cliente.getEstadoCliente().isEmpty()) {
            return new ResponseEntity<>("El estado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        // Todo bien, guardar el cliente
        clienteService.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);

    }

	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
	    var listaCliente = clienteService.findAll();
	    return new ResponseEntity<>(listaCliente, HttpStatus.OK);
	}


	@GetMapping("/busqueda/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	    var listaCliente = clienteService.filtroCliente(filtro); 
	    return new ResponseEntity<>(listaCliente, HttpStatus.OK);
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<Object> findOne(@PathVariable String idCliente){
	    var cliente = clienteService.findOne(idCliente);
	    return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Object> delete(@PathVariable String idCliente){
	    clienteService.delete(idCliente);
	    return new ResponseEntity<>("Registro Deshabilitado", HttpStatus.OK);
	}
	
	@PutMapping("/{idCliente}")
	public ResponseEntity<Object> update(@PathVariable String idCliente, @ModelAttribute("cliente") Cliente clienteUpdate){
	    var cliente = clienteService.findOne(idCliente);
	    if (cliente.isPresent()) {
	        cliente.get().setTipoId(clienteUpdate.getTipoId());
	        cliente.get().setDocCliente(clienteUpdate.getDocCliente());
	        cliente.get().setNombreCliente(clienteUpdate.getNombreCliente());
	        cliente.get().setApellidoCliente(clienteUpdate.getApellidoCliente());
	        cliente.get().setDireccionCliente(clienteUpdate.getDireccionCliente());
	        cliente.get().setCiudadCliente(clienteUpdate.getCiudadCliente());
	        cliente.get().setCorreoCliente(clienteUpdate.getCorreoCliente());
	        cliente.get().setEstadoCliente(clienteUpdate.getEstadoCliente());

	        clienteService.save(cliente.get());
	        return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error: cliente no encontrado", HttpStatus.BAD_REQUEST);
	    }
	}
}
