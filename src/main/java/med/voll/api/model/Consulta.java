package med.voll.api.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import med.voll.api.dto.ConsultaDTO;

@Entity
@Table(name = "consultas")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	private Paciente paciente;

	@ManyToOne()
	private Medico medico;

	private OffsetDateTime horario;

	public Consulta() {
	}

	public Consulta(Paciente paciente, Medico medico, OffsetDateTime horario) {
		this.paciente = paciente;
		this.medico = medico;
		this.horario = horario;
	}

	public Consulta(ConsultaDTO c) {
		this.paciente = c.paciente();
		this.medico = c.medico();
		this.horario = c.horario();
	}

	public Long getId() {
		return id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public OffsetDateTime getHorario() {
		return horario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public void setHorario(OffsetDateTime horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "Consulta [paciente=" + paciente + ", medico=" + medico + ", horario=" + horario + "]";
	}

}
