package med.voll.api.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;

import med.voll.api.model.Consulta;

public record ListagemConsultaDTO(Long id, String nomeMedico, String nomePaciente, String horario) {

//	public ListagemConsultaDTO(Consulta consulta) {
//		this(consulta.getId(), consulta.getMedico().getNome(), consulta.getPaciente().getNome(), consulta.getHorario());
//	}

//	public ListagemConsultaDTO(Consulta consulta, ZoneId zone) {
//		
//		this(
//			consulta.getId(), 
//			consulta.getMedico().getNome(), 
//			consulta.getPaciente().getNome(), 
//			consulta.getHorario()
//					.format("dd/MM/yyyy HH:mm"))
//					);
//	}

}
