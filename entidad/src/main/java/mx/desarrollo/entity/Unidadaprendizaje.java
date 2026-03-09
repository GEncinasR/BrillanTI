package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "unidadaprendizaje")
public class Unidadaprendizaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "NombreUA", nullable = false, length = 50)
    private String nombreUA;

    @ColumnDefault("0")
    @Column(name = "HrsClase")
    private Byte hrsClase;

    @ColumnDefault("0")
    @Column(name = "HrsTaller")
    private Byte hrsTaller;

    @ColumnDefault("0")
    @Column(name = "HrsLab")
    private Byte hrsLab;

    @OneToMany(mappedBy = "idUA")
    private Set<Asignado> asignados = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUA() {
        return nombreUA;
    }

    public void setNombreUA(String nombreUA) {
        this.nombreUA = nombreUA;
    }

    public Byte getHrsClase() {
        return hrsClase;
    }

    public void setHrsClase(Byte hrsClase) {
        this.hrsClase = hrsClase;
    }

    public Byte getHrsTaller() {
        return hrsTaller;
    }

    public void setHrsTaller(Byte hrsTaller) {
        this.hrsTaller = hrsTaller;
    }

    public Byte getHrsLab() {
        return hrsLab;
    }

    public void setHrsLab(Byte hrsLab) {
        this.hrsLab = hrsLab;
    }

    public Set<Asignado> getAsignados() {
        return asignados;
    }

    public void setAsignados(Set<Asignado> asignados) {
        this.asignados = asignados;
    }

}