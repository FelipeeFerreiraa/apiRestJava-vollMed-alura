package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarPacienteDTO;
import med.voll.api.dto.ListagemPacienteDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.ApiService;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repositorio;

	@Autowired
	private ApiService service;

	@PostMapping
	@Transactional
	public void cadastrarPaciente(@RequestBody @Valid PacienteDTO json) {
		System.out.println(json);
		repositorio.save(new Paciente(json));
	}

	@GetMapping
	public Page<ListagemPacienteDTO> listarPacientes(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		return service.listarDadosPacientes(paginacao);
	}

	@PutMapping
	@Transactional
	public void atualizarPaciente(@RequestBody @Valid AtualizarPacienteDTO json) {
		Paciente paciente = repositorio.getReferenceById(json.id());
		paciente.atualizarPaciente(json);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluindoPaciente(@PathVariable Long id) {
		Paciente paciente = repositorio.getReferenceById(id);
		paciente.exclusaoLogica();
	}
}
