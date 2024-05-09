package com.sena.ShoeStore.controller;

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

import com.sena.ShoeStore.interfaceService.IClienteService;
import com.sena.ShoeStore.models.cliente;

@RequestMapping("/api/shoes/cliente")
@RestController
@CrossOrigin
public class ClienteController { 
	
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("cliente") cliente cliente) {

        // Verificar que no exista el documento de identidad
        var listaCliente = clienteService.findAll()
                .stream().filter(clienteExistente -> clienteExistente.getId_cliente()
                        .equals(cliente.getId_cliente()));
        if (listaCliente.count() != 0) {
            return new ResponseEntity<>("El cliente ya existe", HttpStatus.BAD_REQUEST);
        }
        
        // Añadir campos obligatorios
        if (cliente.getTipo_id().isEmpty()) {
            return new ResponseEntity<>("El tipo de identificación es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getDoc_cliente().isEmpty()) {
            return new ResponseEntity<>("El número de documento es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getNombre_cliente().isEmpty()) {
            return new ResponseEntity<>("El nombre del cliente es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getApellido_cliente().isEmpty()) {
            return new ResponseEntity<>("El apellido del cliente es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getDireccion_cliente().isEmpty()) {
            return new ResponseEntity<>("La dirección es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (cliente.getCiudad_cliente().isEmpty()) {
            return new ResponseEntity<>("La ciudad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (cliente.getCorreo_cliente().isEmpty()) {
            return new ResponseEntity<>("El correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (cliente.getEstado_cliente().isEmpty()) {
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
	public ResponseEntity<Object> update(@PathVariable String idCliente, @ModelAttribute("cliente") cliente clienteUpdate){
	    var cliente = clienteService.findOne(idCliente);
	    if (cliente.isPresent()) {
	        cliente.get().setTipo_id(clienteUpdate.getTipo_id());
	        cliente.get().setDoc_cliente(clienteUpdate.getDoc_cliente());
	        cliente.get().setNombre_cliente(clienteUpdate.getNombre_cliente());
	        cliente.get().setApellido_cliente(clienteUpdate.getApellido_cliente());
	        cliente.get().setDireccion_cliente(clienteUpdate.getDireccion_cliente());
	        cliente.get().setCiudad_cliente(clienteUpdate.getCiudad_cliente());
	        cliente.get().setCorreo_cliente(clienteUpdate.getCorreo_cliente());
	        cliente.get().setEstado_cliente(clienteUpdate.getEstado_cliente());

	        clienteService.save(cliente.get());
	        return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error: cliente no encontrado", HttpStatus.BAD_REQUEST);
	    }
	}
}
