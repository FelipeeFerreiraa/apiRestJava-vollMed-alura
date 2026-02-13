package med.voll.api.service.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.ConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;

	public void validar(ConsultaDTO dto) {
		var primeiroHorario = dto.horario().withHour(7);
		var ultimoHorario = dto.horario().withHour(18);
		var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndHorarioBetween(dto.idPaciente(),
				primeiroHorario, ultimoHorario);

		if (pacientePossuiOutraConsultaNoDia) {
			throw new ValidacaoException("PACIENTE JÁ POSSUI UMA CONSULTA AGENDADA NESSE DIA...");
		}

	}

}
