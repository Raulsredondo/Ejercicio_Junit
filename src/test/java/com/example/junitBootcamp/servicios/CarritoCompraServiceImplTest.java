package com.example.junitBootcamp.servicios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import com.example.junitBootcamp.bbdd.BaseDatosI;
import com.example.junitBootcamp.bbdd.BaseDatosImpl;
import com.example.junitBootcamp.model.Articulo;


@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {
	
	
	
	@Mock
	private BaseDatosImpl baseDatos;
	
	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();



		



	@Test
	void testLimpiarCesta() {
		assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("chandal", 30.50));
		assertFalse(carritoService.getArticulos().isEmpty());
		carritoService.limpiarCesta();
		assertTrue(carritoService.getArticulos().isEmpty());
		
		
	}

	@Test
	void testAddArticulo() {
		
		assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("chandal", 30.50));
		assertFalse(carritoService.getArticulos().isEmpty());
		
	}

	@Test
	void testGetNumArticulos() {
		carritoService.addArticulo(new Articulo("chandal", 30.50));
		carritoService.addArticulo(new Articulo("gorra", 12.50));
		Integer res = carritoService.getNumArticulos();
		assertEquals(2, res);
	}

	@Test
	void testGetArticulos() {
		assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("chandal", 30.50));
		carritoService.addArticulo(new Articulo("gorra", 12.50));
		carritoService.addArticulo(new Articulo("calcetines", 5.50));
		carritoService.addArticulo(new Articulo("sudadera", 22.50));
		List<Articulo> res = carritoService.getArticulos();
		assertEquals("chandal", res.get(0).getNombre());
		assertEquals(4, res.size());
	}

	@Test
	void testTotalPrice() {
		
		

		
		
		assertEquals(0.00, carritoService.totalPrice());
		carritoService.addArticulo(new Articulo("chandal", 30.50));
		carritoService.addArticulo(new Articulo("gorra", 12.50));
		carritoService.addArticulo(new Articulo("calcetines", 5.50));
		assertEquals(48.50, carritoService.totalPrice());
	}
	
	@Test
	void aplicarDescuento() {
		
		Articulo articulo = new Articulo("Camiseta", 20.00);
		when(baseDatos.findArticuloById(any(Integer.class))).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1, 10D);
		assertEquals(18D, res);
		verify(baseDatos).findArticuloById(any(Integer.class));
	}
	
	@Test
	void testInsertar() {
		Articulo articulo = new Articulo("chandal", 40.00);
		when(baseDatos.insertarArticulo(any(Articulo.class))).thenReturn(0);
		Integer identificador = carritoService.insertar(articulo);
		List<Articulo> articulos = carritoService.getArticulos();
		
		assertEquals(0, identificador);
		assertEquals("chandal",	articulos.get(identificador).getNombre());
		assertEquals(40D, articulos.get(identificador).getPrecio());
		verify(baseDatos, atLeast(1)).insertarArticulo(any(Articulo.class));
		
	}

	@Test
	void testCalculadorDescuento() {
		assertEquals(90D, carritoService.calculadorDescuento(100D, 10D));
	}

}
