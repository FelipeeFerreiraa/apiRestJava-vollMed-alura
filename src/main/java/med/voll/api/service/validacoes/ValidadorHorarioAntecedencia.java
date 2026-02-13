package med.voll.api.service.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.dto.ConsultaDTO;
import med.voll.api.exception.ValidacaoException;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

	public void validar(ConsultaDTO dto) {

		LocalDateTime data = dto.horario();
		LocalDateTime agora = LocalDateTime.now();
		var diferencaEmMinutos = Duration.between(agora, data).toMinutes();
		//LocalTime hora = data.toLocalTime();

		if (diferencaEmMinutos < 30) {
			throw new ValidacaoException("CONSULTA DEVE SER AGENDADA COM ANTECEDÊNCIA MINÍMA DE 30 MINUTOS...");
		}
	}

}
