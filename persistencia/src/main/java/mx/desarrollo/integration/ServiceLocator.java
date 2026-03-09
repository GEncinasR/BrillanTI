package mx.desarrollo.integration;

import jakarta.persistence.EntityManager;
//import mx.desarrollo.dao.AlumnoDAO;
import mx.desarrollo.dao.UnidadAprendizajeDAO;
//import mx.desarrollo.dao.UsuarioDAO;
import mx.desarrollo.persistence.HibernateUtil;

/**
 *
 * @author total
 */
public class ServiceLocator {

  private static EntityManager getEntityManager() {
    return HibernateUtil.getEntityManager();
  }

  public static UnidadAprendizajeDAO getInstanceUnidadAprendizajeDAO() {
    return new UnidadAprendizajeDAO(getEntityManager());
  }

}
