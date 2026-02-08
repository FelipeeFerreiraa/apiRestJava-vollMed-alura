package med.voll.api.dto;

import java.time.LocalDateTime;

public record DetalhandoConsultaDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime horario) {
}

//public record DetalhandoConsultaDTO(Long idPaciente, String nomePaciente, String emailPaciente, String telefonePaciente,
//		String cpfPaciente, EnderecoDTO enderecoPaciente, Boolean ativoPaciente,
//
//		Long idMedico, String nomeMedico, String emailMedico, String crmMedico, String telefoneMedico,
//		Especialidade especialidadeMedico, EnderecoDTO enderecoMedico, Boolean ativoMedico, LocalDateTime horario) {
//
//	public DetalhandoConsultaDTO(Consulta c) {
//		this(c.getPaciente().getId(), c.getPaciente().getNome(), c.getPaciente().getEmail(),
//				c.getPaciente().getTelefone(), c.getPaciente().getCpf(), new EnderecoDTO(c.getPaciente().getEndereco()),
//				c.getPaciente().getAtivo(),
//
//				c.getMedico().getId(), c.getMedico().getNome(), c.getMedico().getEmail(), c.getMedico().getCrm(),
//				c.getMedico().getTelefone(), c.getMedico().getEspecialidade(),
//				new EnderecoDTO(c.getMedico().getEndereco()), c.getMedico().getAtivo(),
//
//				c.getHorario());
//	}
//
//}
