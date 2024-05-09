package com.sena.shoestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.shoestore.controller.VentaController;
import com.sena.shoestore.interfaceService.IVentaService;
import com.sena.shoestore.interfaces.IVenta;
import com.sena.shoestore.models.venta;



@Service
public class ventaService  implements IVentaService{
	@Autowired
	private IVenta data;

	@Override
	public String save(venta venta) {
		data.save(venta);
		return venta.getId_venta();
	}

	@Override
	public List<venta> findAll() {
		List<venta> listaVenta=
				(List<venta>) data.findAll();
		//(List<paciente>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <paciente>
		//- se convierte a list <paciente>
		return listaVenta;
	}
	
	@Override
	public List<venta> filtroVenta(String filtro) {
		List<venta> listaVenta=data.filtroVenta(filtro);
		return listaVenta;
	}
	
	@Override
	public Optional<venta> findOne(String id_venta) {
		Optional<venta> venta=data.findById(id_venta);
		return venta;

	}


	@Override
	public int delete(String id_venta) {
		var venta=data.findById(id_venta).get();
		venta.setEstado_venta("Inactivo"); 
        data.save(venta); 
		return 0;
	}

	@Override
	public String save(VentaController venta) {
		//  Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'save'");
	}

}



