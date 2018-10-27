package com.prueba.store.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.prueba.store.entidad.Producto;
import com.prueba.store.servicio.ServicioProducto;

/**
 * Recurso publicado para informacion de productos.
 * @author Sebastian
 *
 */
@Path("producto")
@RequestScoped
public class ProductoResource {
    @Context
    private UriInfo context;
    @EJB
    private ServicioProducto servicioProducto;

    /**
     * Obtiene una lista de productos y envia una respuesta positiva en formato json.
     * @return String json
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.ok().entity(this.servicioProducto.obtenerProductos()).build();
    }

   /**
    * Obtiene un producto segun el parametro recibido en la url.
    * @param codigoProducto codigo del producto.
    * @return el producto en formato json
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getProductoJson(@PathParam("id") String codigoProducto) {
        return Response.ok().entity(this.servicioProducto.obtenerProductoPorId(codigoProducto)).build();
    }

    /**
     * Invoca el servicio encargado de actualizar un producto.
     * @param producto datos del producto a actualizar
     * @return el producto actualizado
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(Producto producto) {
    	try {
        return Response.ok().entity(this.servicioProducto.actualizarProducto(producto)).build();
    	}catch (ValidationException e) {
    		return Response.serverError().entity(e.getMessage()).build();
		}
    }

    /**
     * Invoca el servicio encargado de crear un producto.
     * @param producto valores del producto a ser creado.
     * @return response 200 ok si se creo el producto
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(Producto producto) {
        try {
            this.servicioProducto.crearProducto(producto);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
