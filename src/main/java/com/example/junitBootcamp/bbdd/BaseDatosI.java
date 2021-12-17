package com.example.junitBootcamp.bbdd;

import com.example.junitBootcamp.model.Articulo;

public interface BaseDatosI {

	public Integer insertarArticulo(Articulo articulo);
	
	public Articulo findArticuloById(Integer identificador);
	
	public void iniciar();
}
