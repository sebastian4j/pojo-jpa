package com.sebastian.pojojpa;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * crea el {@link EntityManagerFactory} y permite a los clientes obtener el
 * {@link EntityManager} para sus tareas de persistencia.
 *
 * @author Sebastián Ávila A.
 */
public class ContextoPersistencia {

    private static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("em", cargarPropiedades());

    private static Map<String, String> cargarPropiedades() {
        return Map.of("javax.persistence.jdbc.driver", System.getProperty("jdbc_driver"),
                "javax.persistence.jdbc.url", System.getProperty("jdbc_url"),
                "javax.persistence.jdbc.user", System.getProperty("jdbc_user"),
                "javax.persistence.jdbc.password", System.getProperty("jdbc_password"),
                "hibernate.hbm2ddl.auto", "validate"
        );
    }

    /**
     * genera el manager para realizar las tareas de persistencia.
     *
     * @return manager de las entidades
     */
    public static EntityManager obtener() {
        return emf.createEntityManager();
    }
}
