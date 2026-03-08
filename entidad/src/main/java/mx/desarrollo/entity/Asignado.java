package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(name = "asignado")
public class Asignado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAsignado", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IdProfesor", nullable = false)
    private Profesor idProfesor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IdUA", nullable = false)
    private Unidadaprendizaje idUA;

    @Lob
    @Column(name = "Dia")
    private String dia;

    @Column(name = "HrInicio")
    private LocalTime hrInicio;

    @Column(name = "HrFin")
    private LocalTime hrFin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Unidadaprendizaje getIdUA() {
        return idUA;
    }

    public void setIdUA(Unidadaprendizaje idUA) {
        this.idUA = idUA;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalTime getHrInicio() {
        return hrInicio;
    }

    public void setHrInicio(LocalTime hrInicio) {
        this.hrInicio = hrInicio;
    }

    public LocalTime getHrFin() {
        return hrFin;
    }

    public void setHrFin(LocalTime hrFin) {
        this.hrFin = hrFin;
    }

}