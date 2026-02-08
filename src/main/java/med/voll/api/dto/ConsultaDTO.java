package med.voll.api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Especialidade;

public record ConsultaDTO(

		@NotNull 
		Long idPaciente,

		Long idMedico,

		@NotNull 
		@Future 
		LocalDateTime horario,
		
		Especialidade especialidade) {

}
