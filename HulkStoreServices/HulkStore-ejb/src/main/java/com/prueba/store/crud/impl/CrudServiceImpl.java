/*
 * Gestorinc S.A. Sistema: Gestor G5 Creado: 26 oct. 2018 Los contenidos de este archivo son propiedad intelectual de Gestorinc
 * S.A. y se encuentran protegidos por la licencia: "GESTOR FIDUCIA/FONDOS G5". Usted puede encontrar una copia de esta licencia
 * en: http://www.gestorinc.com Copyright 2008-2018 Gestorinc S.A. Todos los derechos reservados.
 */
package com.prueba.store.crud.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * @author sebas.
 */
public abstract class CrudServiceImpl {
    /**
     * Objeto que maneja las operaciones de persistencia.
     */
    @PersistenceContext(name = "punit", unitName = "punit")
    private EntityManager em;

    /**
     * Crea una instancia de Query a partir de la sentencia ejbql y los argumentos asociados al ejbql.
     * @param ejbql Sentencia ejbql.
     * @param args Argumentos para el query.
     * @return Query asociado al ejbql y args.
     */
    protected Query createQueryDinamico(String ejbql, List<Object> args) {
        Query query = this.getEntityManager().createQuery(ejbql);
        for (int i = 1; i <= args.size(); i++) {
            Object obj = args.get(i - 1);
            if (obj instanceof Date) {
                query.setParameter(i, (Date) obj, TemporalType.DATE);
            } else {
                query.setParameter(i, obj);
            }
        }
        return query;
    }

    /**
     * Ejecuta la operaci\u00f3n INSERT en el repositorio de datos.
     * @param entity Objeto que contiene los valores con los cuales se va a crear el nuevo registro en el repositorio de datos.
     * @param dsa Objeto que contiene la informaci\u00f3n del usuario que realiza la operaci\u00f3n de inserci\u00f3n para el
     *        registro de auditor\u00eda.
     */
    protected void insert(Object entity) {
        this.getEntityManager().persist(entity);
    }

    /**
     * Ejecuta la operaci\u00f3n UPDATE en el repositorio de datos.
     * @param entity Objeto que contiene los valores con los cuales se va a actualizar el registro que se encuentra en el
     *        repositorio de datos.
     * @return Objeto con los nuevos valores que constan en el repositorio de datos.
     */
    protected <T> T update(T entity) {
        return this.getEntityManager().merge(entity);
    }

    /**
     * Ejecuta la operaci\u00f3n DELETE en el repositorio de datos.
     * @param entity Objeto que contiene la clave primaria del registro que va a ser eliminado del repositorio de datos.
     */
    protected void delete(Object entity) {
        this.getEntityManager().remove(this.getEntityManager().merge(entity));
    }

    /**
     * Retorna una referencia al objeto que maneja las operaciones de persistencia definidas por JPA.
     * @return Referencia al objeto que maneja las operaciones de persistencia. En caso de que el objeto no este inicializado
     *         lanza la excepci\u00f3n
     * @see java.lang.IllegalStateException
     */
    protected EntityManager getEntityManager() {
        if (this.getPunit() == null) {
            throw new IllegalStateException("EntityManager has not been set on Service before usage");
        }
        return this.getPunit();
    }

    /**
     * Obtiene la referencia al log de auditor\u00eda.
     * @return referencia al log de auditor\u00eda.
     */
    protected abstract Logger logger();

    /**
     * {@inheritDoc }
     */
    private EntityManager getPunit() {
        return this.em;
    }
}
