package com.prueba.store.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import javax.ejb.EJBException;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prueba.store.crud.impl.CrudServiceImpl;
import com.prueba.store.entidad.Producto;
import com.prueba.store.servicio.ServicioProducto;

/**
 * Contiene los metodos que realizan las pruebas unitarias para el modulo de productos. 
 * @author Sebastian
 */
@RunWith(Arquillian.class)
public class ServicioProductoTest {

	/** Referencia al servicio de productos.*/
	@Inject
	private ServicioProducto servicioProducto;
	
	/**
	 * Crea un despliegue Wildfly 10 segun la configuracion en el pom.xml.
	 * @return WebArchive
	 */
	@Deployment
	public static WebArchive createDeployment() {
	    return ShrinkWrap.create(WebArchive.class)
	            .addClasses(Producto.class, ServicioProducto.class, CrudServiceImpl.class)
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml") // activates CDI
	            .addAsResource("META-INF/persistence.xml");
	}

	/**
	 * Prueba el metodo que obtiene los productos.
	 */
	@Test
	public void testObtenerProductos() {
		try {
			assertNotNull(this.servicioProducto.obtenerProductos()); 
		} catch (Exception e) {
			fail("La busqueda no deberia propagar una excepcion.");
		}
	}
	
	/**
	 * Prueba el metodo para obtener productos por su codigo.
	 */
	 @Test
	 public void testObtenerProductoPorId() {
		 Producto producto = this.servicioProducto.obtenerProductoPorId("CAMHULK");
		 assertNotNull(producto);
		 assertEquals(producto.getCodigo(), "CAMHULK");
	 }

	 /**
	  * Prueba unitaria de la actalizacion de productos.
	  */
	@Test
	public void testActualizarProducto() {
		Producto producto = this.servicioProducto.obtenerProductoPorId("CAMHULK");
		Integer cantidad = producto.getExistencias()+5;
		producto.setExistencias(cantidad);
		producto = this.servicioProducto.actualizarProducto(producto);
		assertEquals(cantidad, producto.getExistencias());
	}

	/**
	 * Prueba unitaria para la creacion de productos.
	 */
	 @Test
	 public void testCrearProducto() {
		Producto producto = new Producto("SST02", "Saint Seiya Tomo 02", 5, "Manga", new BigDecimal("30"));
		this.servicioProducto.crearProducto(producto);
		 producto = this.servicioProducto.obtenerProductoPorId("SST02");
		 assertNotNull(producto);
		 assertEquals(producto.getCodigo(), "SST02");
	 }

}
