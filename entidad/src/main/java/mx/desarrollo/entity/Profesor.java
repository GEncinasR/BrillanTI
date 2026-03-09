package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "NombreProfesor", nullable = false, length = 50)
    private String nombreProfesor;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "ApellidoP", nullable = false, length = 50)
    private String apellidoP;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "ApellidoM", nullable = false, length = 50)
    private String apellidoM;

    @Size(max = 13)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "RFC", nullable = false, length = 13)
    private String rfc;

    @OneToMany(mappedBy = "idProfesor")
    private Set<Asignado> asignados = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Set<Asignado> getAsignados() {
        return asignados;
    }

    public void setAsignados(Set<Asignado> asignados) {
        this.asignados = asignados;
    }

}