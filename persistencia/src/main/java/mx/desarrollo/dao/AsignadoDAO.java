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

    public boolean existe(Asignado a) {
        // Un profesor no puede tener dos clases al mismo tiempo el mismo día.
        // Verificamos si existe alguna asignación para este profesor y día
        // donde el rango de horas se cruce.
        String jpql = "SELECT COUNT(asig) FROM Asignado asig " +
                      "WHERE asig.idProfesor = :prof " +
                      "AND asig.dia = :dia " +
                      "AND ((asig.hrInicio <= :inicio AND asig.hrFin > :inicio) " +
                      "OR (asig.hrInicio < :fin AND asig.hrFin >= :fin) " +
                      "OR (:inicio <= asig.hrInicio AND :fin >= asig.hrFin))";
        
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("prof", a.getIdProfesor())
                .setParameter("dia", a.getDia())
                .setParameter("inicio", a.getHrInicio())
                .setParameter("fin", a.getHrFin())
                .getSingleResult();
        
        return count > 0;
    }

    public List<Asignado> obtenerPorProfesor(Integer idProfesor) {
        entityManager.clear();
        String jpql = "SELECT a FROM Asignado a " +
                      "JOIN FETCH a.idUA " +
                      "JOIN FETCH a.idProfesor " +
                      "WHERE a.idProfesor.id = :idProfesor";
        
        return entityManager.createQuery(jpql, Asignado.class)
                .setParameter("idProfesor", idProfesor)
                .getResultList();
    }
}
