package com.sebastian.pojojpa;

import java.util.logging.Logger;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * configuracion para la conexion de pruebas con postgres.
 *
 * @author Sebastián Ávila A.
 */
public final class PostgresContainerSetUp {
  private static final Logger LOGGER = Logger.getLogger(PostgresContainerSetUp.class.getName());

  /**
   * permite iniciar y cargar los datos en el contenedor.
   *
   * @return referencia al contenedor con los datos cargado.
   */
  @SuppressWarnings({"rawtypes", "resource"})
  public static PostgreSQLContainer cargar() {
    return new PostgreSQLContainer<>().withInitScript("bd.sql");
  }

  /**
   * permite exportar las variables de acceso a la base de datos.
   *
   * @param postgres referencia para obtener los datos y exportarlos
   */
  @SuppressWarnings("rawtypes")
  public static void comenzar(final PostgreSQLContainer postgres) {
    postgres.start();
    System.setProperty("jdbc_driver", "org.postgresql.Driver");
    System.setProperty("jdbc_url", postgres.getJdbcUrl());
    System.setProperty("jdbc_user", postgres.getUsername());
    System.setProperty("jdbc_password", postgres.getPassword());
    LOGGER.info(postgres.getJdbcUrl());
  }
}
