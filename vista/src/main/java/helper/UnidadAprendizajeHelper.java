package helper;

import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;

public class UnidadAprendizajeHelper {

    /**
     * Valida que la unidad de aprendizaje tenga nombre no vacío,
     * que las horas (clase, taller y laboratorio) estén en el rango 0‑4,
     * y que al menos una hora sea mayor que 0.
     *
     * @param ua unidad a verificar
     * @return {@code true} si pasa todas las comprobaciones, {@code false} en caso contrario
     */
    public boolean validar(UnidadAprendizaje ua) {
        if (ua == null) {
            return false;
        }
        if (ua.getNombre() == null || ua.getNombre().trim().isEmpty()) {
            return false;
        }
        if (!rangoValido(ua.getHrsClase())
                || !rangoValido(ua.getHrsTaller())
                || !rangoValido(ua.getHrsLab())) {
            return false;
        }
        // Al menos una hora debe ser mayor que 0
        if ((ua.getHrsClase() == null || ua.getHrsClase() == 0) &&
                (ua.getHrsTaller() == null || ua.getHrsTaller() == 0) &&
                (ua.getHrsLab() == null || ua.getHrsLab() == 0)) {
            return false;
        }
        return true;
    }

    private boolean rangoValido(Byte valor) {
        return valor != null && valor >= 0 && valor <= 4;
    }

}
