package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.dto.ListagemMedicoDTO;
import med.voll.api.dto.ListagemPacienteDTO;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class ApiService {

	@Autowired
	private MedicoRepository medicoRepositorio;

	@Autowired
	private PacienteRepository pacienteRepository;

	public String obterDadosRequest() {
		return "Hello world...";
	}

	public Page<ListagemMedicoDTO> listarDadosMedico(Pageable paginacao) {

		return medicoRepositorio.findAllByAtivoTrue(paginacao).map(ListagemMedicoDTO::new);

//		return med.stream()
//				.map(x -> new ListagemMedicoDTO(x))
//				.collect(Collectors.toList());
	}

	public Page<ListagemPacienteDTO> listarDadosPacientes(Pageable paginacao) {
		return pacienteRepository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDTO::new);
	}
}
