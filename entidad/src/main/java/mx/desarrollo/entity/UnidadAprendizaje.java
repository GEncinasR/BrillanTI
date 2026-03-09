package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "unidadaprendizaje")
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
    private Byte hrsClase;

    @Min(0)
    @Max(4)
    @Column(name = "HrsTaller")
    private Byte hrsTaller;

    @Min(0)
    @Max(4)
    @Column(name = "HrsLab")
    private Byte hrsLab;

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

}