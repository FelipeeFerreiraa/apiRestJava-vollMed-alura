package med.voll.api.dto;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import med.voll.api.model.Consulta;

public record ListagemConsultaDTO(Long id, String nomeMedico, String nomePaciente, String horario) {

//	public ListagemConsultaDTO(Consulta consulta) {
//		this(consulta.getId(), consulta.getMedico().getNome(), consulta.getPaciente().getNome(), consulta.getHorario());
//	}
	
	public ListagemConsultaDTO(Consulta consulta, ZoneId zone) {
		
		this(
			consulta.getId(), 
			consulta.getMedico().getNome(), 
			consulta.getPaciente().getNome(), 
			consulta.getHorario()
					.atZoneSameInstant(zone)
					.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
					);
	}

	

}
