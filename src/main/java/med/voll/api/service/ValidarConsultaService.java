package med.voll.api.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import med.voll.api.dto.ConsultaDTO;
import med.voll.api.exception.ValidacaoException;

@Service
public class ValidarConsultaService {

	public void validarConsulta(ConsultaDTO dto) {
		validarDiaConsulta(dto);
		validarHorarioReservaMenosDe30Min(dto);
	}

	public void validarDiaConsulta(ConsultaDTO dto) {
		OffsetDateTime data = dto.horario();

		DayOfWeek dia = data.getDayOfWeek();
		LocalTime hora = data.toLocalTime();

		boolean domingo = dia == DayOfWeek.SUNDAY;
		boolean foraHorario = hora.isBefore(LocalTime.of(7, 0)) || hora.isAfter(LocalTime.of(19, 0));

		if (domingo || foraHorario) {
			throw new ValidacaoException("Consulta fora do horário de funcionamento");
		}
	}

	public void validarHorarioReservaMenosDe30Min(ConsultaDTO dto) {
		if (dto.horario().isBefore(OffsetDateTime.now().plusMinutes(30))) {
			throw new ValidacaoException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
		}
	}
}
