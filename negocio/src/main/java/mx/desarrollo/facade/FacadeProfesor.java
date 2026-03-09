package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateProfesor;
import mx.desarrollo.entity.Profesor;

public class FacadeProfesor {

    private final DelegateProfesor delegateProfesor;

    public FacadeProfesor(){
        this.delegateProfesor = new DelegateProfesor();
    }

    public void guardarProfesor(Profesor profe){
        delegateProfesor.saveProfesor(profe);
    }

    public Profesor buscarPorRFC(String rfc){
        return delegateProfesor.buscarPorRFC(rfc);
    }

    public java.util.List<Profesor> findAll() {
        return delegateProfesor.findAll();
    }
}
