package med.voll.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.dto.CancelamentoConsultaDTO;
import med.voll.api.dto.ConsultaDTO;
import med.voll.api.dto.DetalhandoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.model.Consulta;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.validacoes.ValidadorAgendamentoDeConsulta;

@Service
public class ValidarConsultaService {

	@Autowired
	private ConsultaRepository consultaRepositorio;

	@Autowired
	private MedicoRepository medicoRepositorio;

	@Autowired
	private PacienteRepository pacienteRepositorio;

	@Autowired
	private List<ValidadorAgendamentoDeConsulta> validadores;

	public DetalhandoConsultaDTO validarConsulta(ConsultaDTO dto) {

		if (dto.idMedico() != null && !medicoRepositorio.existsById(dto.idMedico())) {
			throw new ValidacaoException("ID MEDICO NÃO CADASTRADO NO BANCO...");
		}

		if (!pacienteRepositorio.existsById(dto.idPaciente())) {
			throw new ValidacaoException("ID PACIENTE NÃO CADASTRADO NO BANCO...");
		}

		validadores.forEach(v -> v.validar(dto)); 

		Medico medico = escolheMedicoDisponivel(dto); // medicoRepositorio.findById(dto.idMedico()).get();
		
		if(medico == null) {
			throw new ValidacaoException("NÃO EXISTE MÉDICO DISPONÍVEL NESSA DATA...");
		}

		Paciente paciente = pacienteRepositorio.findById(dto.idPaciente()).get();

		Consulta consulta = new Consulta(paciente, medico, dto.horario());

		consultaRepositorio.save(consulta);
		
		return new DetalhandoConsultaDTO(consulta);
	}

	public void validarHorarioReservaMenosDe30Min(ConsultaDTO dto) {
		if (dto.horario().isBefore(LocalDateTime.now().plusMinutes(30))) {
			throw new ValidacaoException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
		}
	}

	private Medico escolheMedicoDisponivel(ConsultaDTO dto) {

		if (dto.idMedico() != null) {
			return medicoRepositorio.getReferenceById(dto.idMedico());
		}

		if (dto.especialidade() == null) {
			throw new ValidacaoException("ESPECIALIDADE OBRIGATÓRIA QUANDO NÃO HÁ PREFERÊNCIA POR UM MÉDICO...");
		}

		System.out.println("MEDICO DISPONIVEL: ");
		System.out.println(medicoRepositorio.escolherMedicoDisponivelNaData(dto.especialidade(), dto.horario()));
		return medicoRepositorio.escolherMedicoDisponivelNaData(dto.especialidade(), dto.horario());

	}

	public void cancelarConsulta(CancelamentoConsultaDTO dto) {

		if (!consultaRepositorio.existsById(dto.id())) {
			throw new ValidacaoException("ID DA CONSULTA NÃO EXISTE...!");
		}

		var consulta = consultaRepositorio.getReferenceById(dto.id());
		consulta.cancelar(dto.motivo());
	}
}
