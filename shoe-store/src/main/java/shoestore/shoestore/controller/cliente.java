package shoestore.shoestore.controller;

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

import shoestore.shoestore.Service.productoService;
import shoestore.shoestore.interfaceService.IClienteService;
import shoestore.shoestore.interfaceService.IProductoService;



@RequestMapping("/api/shoes/cliente")
@RestController
@CrossOrigin
public class cliente { 
	
	
	@Autowired
	private IClienteService clienteServiceService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("Cliente") cliente cliente) {

        // verificar que no exista el documento de identidad
        var listaCliente = clienteService.findAll()
                .stream().filter(cliente -> cliente.getId_cliente()
                        .equals(Cliente.getId_cliente()));
        if (listaCliente.count() != 0) {
            return new ResponseEntity<>("El cliente ya existe", HttpStatus.BAD_REQUEST);
        }
        //verificar que el campo documento de identidad sea diferente vacio
        //Añadir campos obligatorios
        //no cambiar nada
        if (cliente.getTipo_id().equals("")) {

            return new ResponseEntity<>("El tipo de identificacion es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getDoc_cliente().equals("")) {
            
            return new ResponseEntity<>("El numero de documento es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getNombre_cliente().equals("")) {
            
            return new ResponseEntity<>("El nombre del cliente es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getaApellido_cliente().equals("")) {
            
            return new ResponseEntity<>("EL apellido del cliente es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (cliente.getDireccion_cliente().equals("")) {
            
            return new ResponseEntity<>("La direccion es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (cliente.getCiudad_cliente().equals("")) {
            
            return new ResponseEntity<>("La ciudad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
		if (cliente.getEstado_cliente().equals("")) {
            
            return new ResponseEntity<>("El estado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        
        // todo bien
        clienteService.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);

    }

	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
	var listaCliente=clienteService.findAll();
	return new ResponseEntity<>(listaCliente,HttpStatus.OK);
	}


@GetMapping("/busqueda/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	var listaCliente=clienteService.filtroMedico(filtro); 
	return new ResponseEntity<>(listaCliente,HttpStatus.OK);
	}
	
	@GetMapping("/{id_produc}")
	public ResponseEntity<Object> findOne(@PathVariable String id_produc){
		var medico=clienteService.findOne(id_produc);
		return new ResponseEntity<>(medico,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_produc}")
	public ResponseEntity<Object> delete(@PathVariable String id_produc){
		clienteService.delete(id_cliente);
		return new ResponseEntity<>("Registro Dehabilitado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id_cliente, @ModelAttribute("cliente") cliente clienteUpdate){
		var cliente=clienteService.findOne(id).get();
		if (cliente != null) {
			cliente.setTipo_id(clienteUpdate.getTipo_id());
			cliente.setDoc_cliente(clienteUpdate.getDoc_cliente());
			cliente.setNombre_cliente(clienteUpdate.getNombre_cliente());
			cliente.setApellido_cliente(clienteUpdate.getApellido_cliente());
			cliente.setDireccion_cliente(clienteUpdate.getDireccion_cliente());
			cliente.setCiudad_cliente(clienteUpdate.getCiudad_cliente());
			cliente.setEstado_cliente(clienteUpdate.getEstado_cliente());

			clienteService.save(cliente);
			return new ResponseEntity<>(cliente,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Error producto no encontrado",HttpStatus.BAD_REQUEST);
		}
	}

}


