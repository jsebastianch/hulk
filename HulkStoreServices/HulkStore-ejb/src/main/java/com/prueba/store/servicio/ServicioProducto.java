package com.prueba.store.servicio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.validation.ValidationException;

import com.prueba.store.crud.impl.CrudServiceImpl;
import com.prueba.store.entidad.Producto;

/**
 * Servicio que encapsula la logica de negocio referente a los productos.
 * @author Sebastian
 */
@Stateless
public class ServicioProducto extends CrudServiceImpl implements Serializable {
    /**
     * Id de serializacion.
     */
    private static final long serialVersionUID = -9129555638107753634L;

    /**
     * Inicializa valores en la base de datos en memoria.
     */
    @PostConstruct
    public void postConstructor() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("CAMHULK", "Camiseta Hulk", 25, "Ropa", new BigDecimal("20")));
        productos.add(new Producto("SST01", "Saint Seiya Tomo 01", 5, "Manga", new BigDecimal("30")));
        for (Producto producto : productos) {
            super.insert(producto);
        }
    }

    /**
     * Obtiene la lista de productos registrados en base de datos.
     * @return lista de Productos
     */
    @SuppressWarnings("unchecked")
    public List<Producto> obtenerProductos() {
        return super.createQueryDinamico("select p from Producto p", new ArrayList<>()).getResultList();
    }

    /**
     * Actualiza los valores de un producto.
     * @param producto producto a ser actualizado
     * @return el producto actualizado
     * @throws ValidationException si el numero de existencias es menor a 0
     */
    public Producto actualizarProducto(Producto producto) throws ValidationException {
    	if(producto.getExistencias()<0) {
    		producto = null;
    		throw new ValidationException("No puede registrar una salida mayor a las existencias actuales");
    	}
        return super.update(producto);
    }

    /**
     * Registr un producto en la tabla de productos.
     * @param producto valores a ser persistidos.
     */
    public void crearProducto(Producto producto) {
        super.insert(producto);
    }

    /**
     * Obtiene un producto segun el codigo recibido.
     * @param codigoProducto codigo del producto a obtener
     * @return Producto
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Producto obtenerProductoPorId(String codigoProducto) {
        List args = new ArrayList<>();
        args.add(codigoProducto);
        return (Producto) super.createQueryDinamico("select p from Producto p where p.codigo = ?1", args).getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Logger logger() {
        return Logger.getLogger(this.getClass().getName());
    }
}
