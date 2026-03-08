package mx.desarrollo.integration;

import jakarta.persistence.EntityManager;
import mx.desarrollo.dao.AlumnoDAO;
import mx.desarrollo.dao.ProfesorDAO;
import mx.desarrollo.dao.UsuarioDAO;
import mx.desarrollo.persistence.HibernateUtil;

/**
 *
 * @author total
 */
public class ServiceLocator {

  private static AlumnoDAO alumnoDAO;
  private static UsuarioDAO usuarioDAO;
  private static ProfesorDAO profesorDAO;

  private static EntityManager getEntityManager() {
    return HibernateUtil.getEntityManager();
  }

    /**
     * se crea la instancia para alumno DAO si esta no existe
     */
    public static ProfesorDAO getInstanceProfesorDAO() {
        if (profesorDAO == null) {
            profesorDAO = new ProfesorDAO(getEntityManager());
            return profesorDAO;
        } else {
            return profesorDAO;
        }
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

}
