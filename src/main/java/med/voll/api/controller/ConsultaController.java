package med.voll.api.controller;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.ConsultaDTO;
import med.voll.api.dto.DetalhandoConsultaDTO;
import med.voll.api.dto.ListagemConsultaDTO;
import med.voll.api.model.Consulta;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.service.ValidarConsultaService;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

	@Autowired
	private ConsultaRepository repositorio;

	@Autowired
	private ValidarConsultaService service;

	@GetMapping("/{id}")
	public ResponseEntity<DetalhandoConsultaDTO> listadoConsultas(
			@RequestHeader(value = "X-Timezone", defaultValue = "UTC") String timezone, @PathVariable Long id) {
		ZoneId zone = ZoneId.of(timezone);

		Consulta dados = repositorio.getReferenceById(id);

		// if (dados.isPresent()) {

		return ResponseEntity.ok(new DetalhandoConsultaDTO(dados));

		// } else {
		// return ResponseEntity.notFound().build();
		// }

	}

	@GetMapping
	public ResponseEntity<List<ListagemConsultaDTO>> listadoConsultas(
			@RequestHeader(value = "X-Timezone", defaultValue = "UTC") String timezone) {
		ZoneId zone = ZoneId.of(timezone);

		List<ListagemConsultaDTO> dados = repositorio.findAll().stream().map(x -> new ListagemConsultaDTO(x, zone))
				.toList();
		return ResponseEntity.ok().body(dados);

	}

	@PostMapping()
	@Transactional
	public ResponseEntity adicionarConsulta(@RequestBody @Valid ConsultaDTO dados, UriComponentsBuilder uriBuilder) {
		System.out.println(dados);
		Consulta consulta = new Consulta(dados);

		service.validarConsulta(dados);

		repositorio.save(consulta);

		var uri = uriBuilder.path("consultas/{id}").buildAndExpand(consulta.getId()).toUri();

		return ResponseEntity.created(uri).body(new DetalhandoConsultaDTO(consulta));
	}

}
