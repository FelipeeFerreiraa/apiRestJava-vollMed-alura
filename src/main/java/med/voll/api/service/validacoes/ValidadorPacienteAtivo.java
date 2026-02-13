package med.voll.api.service.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.ConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private PacienteRepository repository;

	public void validar(ConsultaDTO dto) {

//		if(dto.idPaciente() == null) {
//			return;
//		}

		var pacienteAtivo = repository.findAtivoById(dto.idPaciente());

		if (!pacienteAtivo) {
			throw new ValidacaoException("PACIENTE DEVE ESTAR ATIVO NO CONSULTÓRIO...");
		}
	}
}
