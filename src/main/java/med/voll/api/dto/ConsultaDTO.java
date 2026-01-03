package med.voll.api.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;

public record ConsultaDTO(
		
		@NotNull
		Paciente paciente, 
		
		@NotNull
		Medico medico, 
		
		@NotNull
		OffsetDateTime horario) {

}
