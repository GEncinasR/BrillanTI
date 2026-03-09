package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "unidadaprendizaje", schema = "sauap")
public class UnidadAprendizaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "NombreUA", nullable = false, length = 50)
    private String nombre;

    @Min(0)
    @Max(4)
    @Column(name = "HrsClase")
    private Integer hrsClase;

    @Min(0)
    @Max(4)
    @Column(name = "HrsTaller")
    private Integer hrsTaller;

    @Min(0)
    @Max(4)
    @Column(name = "HrsLab")
    private Integer hrsLab;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getHrsClase() {
        return hrsClase;
    }

    public void setHrsClase(Integer hrsClase) {
        this.hrsClase = hrsClase;
    }

    public Integer getHrsTaller() {
        return hrsTaller;
    }

    public void setHrsTaller(Integer hrsTaller) {
        this.hrsTaller = hrsTaller;
    }

    public Integer getHrsLab() {
        return hrsLab;
    }

    public void setHrsLab(Integer hrsLab) {
        this.hrsLab = hrsLab;
    }

}