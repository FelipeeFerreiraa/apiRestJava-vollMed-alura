package med.voll.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Paciente paciente;

	@ManyToOne(fetch = FetchType.LAZY)
	private Medico medico;

	private LocalDateTime horario;

	private Boolean ativo;

	public Consulta() {
	}

	@Enumerated(EnumType.STRING)
	private CancelamentoConsulta motivoCancelamento;

	public Consulta(Medico medico, Paciente paciente, LocalDateTime horario) {
		this.ativo = true;
		this.paciente = paciente;
		this.medico = medico;
		this.horario = horario;
	}

//	public Consulta(ConsultaDTO dados) {
//		this.paciente = paciente;
//		this.medico = new Medico();
//		this.horario = horario;
//	}

//	public Consulta(ConsultaDTO c) {
//		this.paciente = c.paciente();
//		this.medico = c.medico();
//		this.horario = c.horario();
//	}

	public Long getId() {
		return id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public LocalDateTime getHorario() {
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

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	public void cancelar(CancelamentoConsulta motivo) {
		this.ativo = false;
		this.motivoCancelamento = motivo;
	}

	@Override
	public String toString() {
		return "Consulta [paciente=" + paciente + ", medico=" + medico + ", horario=" + horario + "]";
	}

}
