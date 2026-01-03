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
import med.voll.api.dto.AtualizarMedicoDTO;
import med.voll.api.dto.ListagemMedicoDTO;
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
	public void cadastrarMedico(@RequestBody @Valid MedicoDTO json) {
		System.out.println(json);

		Medico med = new Medico(json);
		repositorio.save(med);

	}

	@GetMapping
	public Page<ListagemMedicoDTO> listarMedicos(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		return service.listarDadosMedico(paginacao);
	}

	@PutMapping
	@Transactional
	public void atualizarMedico(@RequestBody @Valid AtualizarMedicoDTO json) {
		var medico = repositorio.getReferenceById(json.id());
		medico.atualizarDados(json);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluirMedico(@PathVariable Long id) {
		// repositorio.deleteById(id);

		var medico = repositorio.getReferenceById(id);
		medico.exclusaoLogica();
	}

}
