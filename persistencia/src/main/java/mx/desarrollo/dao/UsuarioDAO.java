package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.persistence.AbstractDAO;
import mx.desarrollo.entity.Usuario;

import java.util.List;

public class UsuarioDAO extends AbstractDAO<Usuario> {
    private final EntityManager entityManager;

    public UsuarioDAO(EntityManager em) {
        super(Usuario.class);
        this.entityManager = em;
    }

    /**
     * Metodo para obtener todos los usuarios utilizando el criterio de la entidad base
     * @return lista de usuarios
     */
    public List<Usuario> obtenerTodos(){
        return super.findAll();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
