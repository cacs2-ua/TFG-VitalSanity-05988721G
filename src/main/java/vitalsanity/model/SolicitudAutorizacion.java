package vitalsanity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "solicitudes_autorizacion")
public class SolicitudAutorizacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombreProfesionalMedico;

    @NotNull
    private String nifNieProfesionalMedico;

    @NotNull
    private String nombrePaciente;

    @NotNull
    private String nifNiePaciente;

    @NotNull
    private String motivo;

    @NotNull
    private String descripcion;

    @NotNull
    private boolean firmada;

    @NotNull
    private boolean cofirmada;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        if (this.paciente == paciente || paciente == null) {
            return;
        }

        if (this.paciente != null) {
            this.paciente.getSolicitudesAutorizacion().remove(this);
        }
        this.paciente = paciente;

        if (!paciente.getSolicitudesAutorizacion().contains(this)) {
            paciente.addSolicitudAutorizacion(this);
        }
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "profesional_medico_id", nullable = false)
    private ProfesionalMedico profesionalMedico;

    public ProfesionalMedico getProfesionalMedico() {
        return this.profesionalMedico;
    }

    public void setProfesionalMedico(ProfesionalMedico profesionalMedico) {
        if (this.profesionalMedico == profesionalMedico || profesionalMedico == null) {
            return;
        }

        if (this.profesionalMedico != null) {
            this.profesionalMedico.getSolicitudesAutorizacion().remove(this);
        }

        this.profesionalMedico = profesionalMedico;

        if (!profesionalMedico.getSolicitudesAutorizacion().contains(this)) {
            profesionalMedico.addSolicitudAutorizacion(this);
        }
    }

    // constructores

    public SolicitudAutorizacion() {}

    public SolicitudAutorizacion(String nombreProfesionalMedico, String nifNieProfesionalMedico, String nombrePaciente,
                                  String nifNiePaciente, String motivo, String descripcion) {
        this.nombreProfesionalMedico = nombreProfesionalMedico;
        this.nifNieProfesionalMedico = nifNieProfesionalMedico;
        this.nombrePaciente = nombrePaciente;
        this.nifNiePaciente = nifNiePaciente;
        this.motivo = motivo;
        this.descripcion = descripcion;
    }

    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProfesionalMedico() {
        return nombreProfesionalMedico;
    }

    public void setNombreProfesionalMedico(String nombreProfesionalMedico) {
        this.nombreProfesionalMedico = nombreProfesionalMedico;
    }

    public String getNifNieProfesionalMedico() {
        return nifNieProfesionalMedico;
    }

    public void setNifNieProfesionalMedico(String nifNieProfesionalMedico) {
        this.nifNieProfesionalMedico = nifNieProfesionalMedico;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNifNiePaciente() {
        return nifNiePaciente;
    }

    public void setNifNiePaciente(String nifNiePaciente) {
        this.nifNiePaciente = nifNiePaciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isFirmada() {
        return firmada;
    }

    public void setFirmada(boolean firmada) {
        this.firmada = firmada;
    }

    public boolean isCofirmada() {
        return cofirmada;
    }

    public void setCofirmada(boolean cofirmada) {
        this.cofirmada = cofirmada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolicitudAutorizacion solicitudAutorizacion = (SolicitudAutorizacion) o;
        return Objects.equals(id, solicitudAutorizacion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
