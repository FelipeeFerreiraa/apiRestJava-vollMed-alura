package med.voll.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarPacienteDTO;
import med.voll.api.dto.DetalhandoPacienteDTO;
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
	public ResponseEntity cadastrarPaciente(@RequestBody @Valid PacienteDTO json, UriComponentsBuilder uriBuilder) {
		System.out.println(json);
		Paciente p = new Paciente(json);
		repositorio.save(p);

		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(p.getId()).toUri();

		return ResponseEntity.created(uri).body(new PacienteDTO(p));
	}

	@GetMapping
	public ResponseEntity<Page<ListagemPacienteDTO>> listarPacientes(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		return service.listarDadosPacientes(paginacao);

	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhandoPacienteDTO> detalhandoPaciente(@PathVariable @Valid Long id) {

		// TAMBÉM FUNCIONA
		// DetalhandoPacienteDTO pac = new
		// DetalhandoPacienteDTO(repositorio.getReferenceById(id));
		// return ResponseEntity.ok(pac);

		Optional<Paciente> paciente = repositorio.findById(id);
		if (paciente.isPresent()) {
			DetalhandoPacienteDTO retorno = new DetalhandoPacienteDTO(paciente.get());
			return ResponseEntity.ok(retorno);

		} else {
			return ResponseEntity.noContent().build();
		}

	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizarPaciente(@RequestBody @Valid AtualizarPacienteDTO json) {
		Paciente paciente = repositorio.getReferenceById(json.id());
		paciente.atualizarPaciente(json);
		return ResponseEntity.ok(new DetalhandoPacienteDTO(paciente));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluindoPaciente(@PathVariable Long id) {
		Paciente paciente = repositorio.getReferenceById(id);
		paciente.exclusaoLogica();

		return ResponseEntity.noContent().build();
	}
}
