package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.persistence.AbstractDAO;
import mx.desarrollo.entity.UnidadAprendizaje;
import java.util.List;

public class UnidadAprendizajeDAO extends AbstractDAO<UnidadAprendizaje> {
    private final EntityManager entityManager;

    public UnidadAprendizajeDAO(EntityManager em) {
        super(UnidadAprendizaje.class);
        this.entityManager = em;
    }

    public List<UnidadAprendizaje> obtenerTodos() {
        return entityManager
                .createQuery("SELECT a FROM UnidadAprendizaje a", UnidadAprendizaje.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
