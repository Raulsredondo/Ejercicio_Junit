package com.example.junitBootcamp.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.junitBootcamp.bbdd.BaseDatosImpl;
import com.example.junitBootcamp.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI {
	
	private List<Articulo> cesta = new ArrayList<>();
	
	@Autowired
	private BaseDatosImpl baseDatos;

	@Override
	public void limpiarCesta() {
		cesta.clear();
	}

	@Override
	public void addArticulo(Articulo articulo) {
		cesta.add(articulo);
	}

	@Override
	public Integer getNumArticulos() {
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return cesta;
	}
	
	
	@Override
	public Double aplicarDescuento(Integer id, Double procentaje) {
		Double resultado = null;
		Articulo articulo = baseDatos.findArticuloById(id);
		if(Optional.ofNullable(articulo).isPresent()) {
			resultado = calculadorDescuento(articulo.getPrecio(), procentaje);
		}
		else {
			System.out.println("No se ha encontrado ningún articulo con el ID: " + id);
		}
		return resultado;
	}

	@Override
	public Double totalPrice() {
		Double total = 0D;
		for(Articulo articulo : cesta) {
			total = total + articulo.getPrecio();
		}
		return total;
	}
	
	@Override
	public Integer insertar(Articulo articulo) {
		Integer identificador = baseDatos.insertarArticulo(articulo);
		cesta.add(articulo);
		return identificador;
	}

	@Override
	public Double calculadorDescuento(Double precio, Double porcentajeDescuento) {
		return precio - precio*porcentajeDescuento/100;
	}

}
