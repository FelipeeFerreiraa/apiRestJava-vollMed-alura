package med.voll.api.service.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.ConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.MedicoRepository;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private MedicoRepository repository;

	public void validar(ConsultaDTO dto) {

		if (dto.idMedico() == null) {
			return;
		}

		var medicoAtivo = repository.findAtivoById(dto.idMedico());

		if (!medicoAtivo) {
			throw new ValidacaoException("MÉDICO DEVE ESTAR ATIVO NO CONSULTÓRIO...");
		}
	}
}
