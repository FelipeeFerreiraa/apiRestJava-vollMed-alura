package med.voll.api.service.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.ConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

@Component
public class ValidadorMedicoComOutraConsultaMesmoHorario implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;

	public void validar(ConsultaDTO dto) {

		var medicoPossuiOutraConsulta = repository.existsByMedicoIdAndHorarioAndMotivoCancelamentoIsNull(dto.idMedico(), dto.horario());
		if (medicoPossuiOutraConsulta) {
			throw new ValidacaoException("MEDICO JÁ POSSUI OUTRA CONSULTA AGENDADA NESSE HORÁRIO!");
		}
	}

}
