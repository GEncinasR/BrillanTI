import mx.desarrollo.integration.ServiceLocator;
import mx.desarrollo.entity.Usuario;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.entity.Asignado;

import java.util.List;

public class testDAO {

    public static void main(String[] args) {
        System.out.println("--- TESTING USUARIOS ---");
        List<Usuario> usuarios = ServiceLocator.getInstanceUsuarioDAO().findAll();
        for (Usuario u : usuarios) {
            System.out.println("User: " + u.getNombreUsuario() + " | ID: " + u.getId());
        }

        System.out.println("\n--- TESTING PROFESORES ---");
        List<Profesor> profesores = ServiceLocator.getInstanceProfesorDAO().findAll();
        for (Profesor p : profesores) {
            System.out.println("Prof: " + p.getNombreProfesor() + " " + p.getApellidoP() + " | RFC: " + p.getRfc());
        }

        System.out.println("\n--- TESTING UNIDADES DE APRENDIZAJE ---");
        List<UnidadAprendizaje> uas = ServiceLocator.getInstanceUnidadAprendizajeDAO().findAll();
        for (UnidadAprendizaje ua : uas) {
            System.out.println("UA: " + ua.getNombre() + " | Clave: " + ua.getId());
        }

        System.out.println("\n--- TESTING ASIGNACIONES ---");
        List<Asignado> asignaciones = ServiceLocator.getInstanceAsignadoDAO().findAll();
        for (Asignado a : asignaciones) {
            System.out.println("ID: " + a.getId() + 
                               " | ProfID: " + a.getIdProfesor().getId() + 
                               " | UAID: " + a.getIdUA().getId() + 
                               " | Día: " + a.getDia() + 
                               " | Inicio: " + a.getHrInicio() + 
                               " | Fin: " + a.getHrFin());
        }
    }
}
