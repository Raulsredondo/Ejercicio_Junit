package com.example.junitBootcamp.bbdd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.junitBootcamp.model.Articulo;

@Service
public class BaseDatosImpl implements BaseDatosI {
	
	private Map<Integer, Articulo> baseDatos;

	@Override
	public Integer insertarArticulo(Articulo articulo) {
		baseDatos.put(baseDatos.size()+1, articulo);
		return baseDatos.size();
	}

	@Override
	public Articulo findArticuloById(Integer identificador) {
		return baseDatos.get(identificador);
	}

	@Override
	public void iniciar() {
		baseDatos = new HashMap<>();
		baseDatos.put(1, new Articulo("Camiseta", 18.99));
		baseDatos.put(2, new Articulo("Pantalón", 21.99));
		baseDatos.put(3, new Articulo("Jersey", 15.99));
		baseDatos.put(4, new Articulo("Chaqueta", 32.99));
		baseDatos.put(5, new Articulo("Falda", 22.99));
	}

}
