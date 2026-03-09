package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.entity.Asignado;
import mx.desarrollo.persistence.AbstractDAO;

import java.util.List;

public class AsignadoDAO extends AbstractDAO<Asignado> {
    private final EntityManager entityManager;

    public AsignadoDAO(EntityManager em) {
        super(Asignado.class);
        this.entityManager = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Asignado> obtenerTodos() {
        return entityManager
                .createQuery("SELECT a FROM Asignado a", Asignado.class)
                .getResultList();
    }

    /**
     * Verifica si ya existe una asignación con los mismos datos
     * para evitar duplicados exactos.
     */
    public boolean existe(Asignado a) {
        String jpql = "SELECT COUNT(asig) FROM Asignado asig " +
                      "WHERE asig.idProfesor = :prof " +
                      "AND asig.idUA = :ua " +
                      "AND asig.dia = :dia " +
                      "AND asig.hrInicio = :inicio " +
                      "AND asig.hrFin = :fin";
        
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("prof", a.getIdProfesor())
                .setParameter("ua", a.getIdUA())
                .setParameter("dia", a.getDia())
                .setParameter("inicio", a.getHrInicio())
                .setParameter("fin", a.getHrFin())
                .getSingleResult();
        
        return count > 0;
    }

    public List<Asignado> obtenerPorProfesor(Integer idProfesor) {

        return entityManager.createQuery(
                        "SELECT a FROM Asignado a WHERE a.idProfesor.id = :idProfesor",
                        Asignado.class
                )
                .setParameter("idProfesor", idProfesor)
                .getResultList();
    }
}
