package com.prueba.store.entidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que representa la tabla PRODUCTO en la base de datos.
 * 
 * @author Sebastian
 */
@Entity
public class Producto implements Serializable {
	/** Id de serializacion. */
	private static final long serialVersionUID = 2034312639362377563L;
	/** Codigo del producto. */
	@Id
	private String codigo;
	/** Nombre del producto. */
	private String nombre;
	/** Numero de existencias en stock del producto. */
	private Integer existencias;
	/** Tipo de producto. */
	private String tipo;
	/** Valor unitario del producto. */
	private BigDecimal valorUnitario;

	/**
	 * Crea una instancia de la clase Producto.
	 */
	public Producto() {
		super();
	}

	/**
	 * Crea una instancia de la clase Producto.
	 * 
	 * @param codigo
	 *            valor a ser asignado al atributo codigo
	 * @param nombre
	 *            valor a ser asignado al atributo nombre
	 * @param existencias
	 *            valor a ser asignado al atributo existencias
	 * @param tipo
	 *            valor a ser asignado al atributo tipo
	 * @param valorUnitario
	 *            valor a ser asignado al atributo valorUnitario
	 */
	public Producto(String codigo, String nombre, Integer existencias, String tipo, BigDecimal valorUnitario) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.existencias = existencias;
		this.tipo = tipo;
		this.valorUnitario = valorUnitario;
	}

	/**
	 * Obtiene el valor del atributo codigo
	 * 
	 * @return el atributo codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}
	/**
	 * Asigna un valor al atributo codigo
	 * 
	 * @param codigo
	 *            valor a ser asignado
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el valor del atributo nombre
	 * 
	 * @return el atributo nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Asigna un valor al atributo nombre
	 * 
	 * @param nombre
	 *            valor a ser asignado
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el valor del atributo existencias
	 * 
	 * @return el atributo existencias
	 */
	public Integer getExistencias() {
		return this.existencias;
	}
	/**
	 * Asigna un valor al atributo existencias
	 * 
	 * @param existencias
	 *            valor a ser asignado
	 */
	public void setExistencias(Integer existencias) {
		this.existencias = existencias;
	}

	/**
	 * Obtiene el valor del atributo tipo
	 * 
	 * @return el atributo tipo
	 */
	public String getTipo() {
		return this.tipo;
	}
	/**
	 * Asigna un valor al atributo tipo
	 * 
	 * @param tipo
	 *            valor a ser asignado
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtiene el valor del atributo valorUnitario
	 * 
	 * @return el atributo valorUnitario
	 */
	public BigDecimal getValorUnitario() {
		return this.valorUnitario;
	}
	/**
	 * Asigna un valor al atributo valorUnitario
	 * 
	 * @param valorUnitario
	 *            valor a ser asignado
	 */
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
}
