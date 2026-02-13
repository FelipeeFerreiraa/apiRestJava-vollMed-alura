package med.voll.api.service.validacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import med.voll.api.dto.ConsultaDTO;
import med.voll.api.exception.ValidacaoException;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

	public void validar(ConsultaDTO dto) {

		LocalDateTime data = dto.horario();

		DayOfWeek dia = data.getDayOfWeek();
		LocalTime hora = data.toLocalTime();

		boolean domingo = dia == DayOfWeek.SUNDAY;
		boolean foraHorario = hora.isBefore(LocalTime.of(7, 0)) || hora.isAfter(LocalTime.of(18, 0));

		if (domingo || foraHorario) {
			throw new ValidacaoException("CONSULTA FORA DO HORÁRIO DE FUNCIONAMENTO...");
		}

	}

}
