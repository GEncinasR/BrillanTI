package mx.desarrollo.integration;

import jakarta.persistence.EntityManager;
import mx.desarrollo.dao.AlumnoDAO;
import mx.desarrollo.dao.UsuarioDAO;
import mx.desarrollo.dao.AsignadoDAO;
import mx.desarrollo.persistence.HibernateUtil;

/**
 *
 * @author total
 */
public class ServiceLocator {

  private static AlumnoDAO alumnoDAO;
  private static UsuarioDAO usuarioDAO;
  private static AsignadoDAO asignadoDAO;

  private static EntityManager getEntityManager() {
    return HibernateUtil.getEntityManager();
  }

  /**
   * se crea la instancia para alumno DAO si esta no existe
   */
  public static AlumnoDAO getInstanceAlumnoDAO() {
    if (alumnoDAO == null) {
      alumnoDAO = new AlumnoDAO(getEntityManager());
      return alumnoDAO;
    } else {
      return alumnoDAO;
    }
  }

  /**
   * se crea la instancia de usuarioDAO si esta no existe
   */
  public static UsuarioDAO getInstanceUsuarioDAO() {
    if (usuarioDAO == null) {
      usuarioDAO = new UsuarioDAO(getEntityManager());
      return usuarioDAO;
    } else {
      return usuarioDAO;
    }
  }

  /**
   * se crea la instancia de asignadoDAO si esta no existe
   * (copié el comment, si el codigo base lo tiene, yo tambien)
   */
  public static AsignadoDAO getInstanceAsignadoDAO() {
    if (asignadoDAO == null) {
      asignadoDAO = new AsignadoDAO(getEntityManager());
      return asignadoDAO;
    } else {
      return asignadoDAO;
    }
  }

}
