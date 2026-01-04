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
import med.voll.api.dto.AtualizarMedicoDTO;
import med.voll.api.dto.ListagemMedicoDTO;
import med.voll.api.dto.DetalhandoMedicoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.service.ApiService;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repositorio;

	@Autowired
	private ApiService service;

	@PostMapping()
	@Transactional
	public ResponseEntity cadastrarMedico(@RequestBody @Valid MedicoDTO json, UriComponentsBuilder uriBuilder) {

		Medico med = new Medico(json);
		repositorio.save(med);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(med.getId()).toUri();

		return ResponseEntity.created(uri).body(new DetalhandoMedicoDTO(med));

	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhandoMedicoDTO> detalharMedico(@PathVariable Long id) {

		Optional<Medico> medico = repositorio.findById(id);

		if (medico.isPresent()) {
			DetalhandoMedicoDTO retorno = new DetalhandoMedicoDTO(medico.get());
			return ResponseEntity.ok(retorno);

		} else {
			return ResponseEntity.noContent().build();
		}

	}

	@GetMapping
	public ResponseEntity<Page<ListagemMedicoDTO>> listarMedicos(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

		var retorno = service.listarDadosMedico(paginacao);

		return ResponseEntity.ok(retorno);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizarMedico(@RequestBody @Valid AtualizarMedicoDTO json) {
		var medico = repositorio.getReferenceById(json.id());
		medico.atualizarDados(json);

		return ResponseEntity.ok(new DetalhandoMedicoDTO(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirMedico(@PathVariable Long id) {
		// repositorio.deleteById(id);

		var medico = repositorio.getReferenceById(id);
		medico.exclusaoLogica();

		return ResponseEntity.noContent().build();
	}

}
