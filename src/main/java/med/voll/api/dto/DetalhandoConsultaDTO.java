package med.voll.api.dto;

import java.time.OffsetDateTime;

import med.voll.api.model.Consulta;
import med.voll.api.model.Especialidade;

public record DetalhandoConsultaDTO(Long idPaciente, String nomePaciente, String emailPaciente, String telefonePaciente,
		String cpfPaciente, EnderecoDTO enderecoPaciente, Boolean ativoPaciente,

		Long idMedico, String nomeMedico, String emailMedico, String crmMedico, String telefoneMedico,
		Especialidade especialidadeMedico, EnderecoDTO enderecoMedico, Boolean ativoMedico, OffsetDateTime horario) {

	public DetalhandoConsultaDTO(Consulta c) {
		this(
				c.getPaciente().getId(), 
				c.getPaciente().getNome(), 
				c.getPaciente().getEmail(),
				c.getPaciente().getTelefone(), 
				c.getPaciente().getCpf(), 
				new EnderecoDTO(c.getPaciente().getEndereco()),
				c.getPaciente().getAtivo(),

				c.getMedico().getId(), 
				c.getMedico().getNome(), 
				c.getMedico().getEmail(), 
				c.getMedico().getCrm(),
				c.getMedico().getTelefone(), 
				c.getMedico().getEspecialidade(), 
				new EnderecoDTO(c.getMedico().getEndereco()),
				c.getMedico().getAtivo(),

				c.getHorario());
	}

}
