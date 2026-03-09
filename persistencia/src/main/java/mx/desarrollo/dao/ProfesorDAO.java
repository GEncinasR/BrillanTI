package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.persistence.AbstractDAO;

public class ProfesorDAO extends AbstractDAO<Profesor> {

    private final EntityManager entityManager;

    public ProfesorDAO(EntityManager em){
        super(Profesor.class);
        this.entityManager = em;
    }

    public void guardarProfesor(Profesor profe){
        save(profe);
    }

    public Profesor buscarPorRFC(String rfc){
        return findByOneParameterUnique("rfc",rfc);
    }

    @Override
    public EntityManager getEntityManager() { return entityManager;}
}
