package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAsignado;
import mx.desarrollo.entity.Asignado;

import java.util.List;

public class FacadeAsignado {
    private final DelegateAsignado delegateAsignado;

    public FacadeAsignado() {
        this.delegateAsignado = new DelegateAsignado();
    }

    public boolean guardarAsignado(Asignado asignado) {
        return delegateAsignado.saveAsignado(asignado);
    }

    public List<Asignado> findAll() {
        return delegateAsignado.findAll();
    }
}
