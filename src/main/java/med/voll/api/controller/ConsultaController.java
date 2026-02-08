package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.CancelamentoConsultaDTO;
import med.voll.api.dto.ConsultaDTO;
import med.voll.api.dto.DetalhandoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.service.ValidarConsultaService;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

	@Autowired
	private ValidarConsultaService consultaService;

	@PostMapping()
	@Transactional
	public ResponseEntity adicionarConsulta(@RequestBody @Valid ConsultaDTO dados) {
		System.out.println(dados);
		consultaService.validarConsulta(dados);
		return ResponseEntity
				.ok(new DetalhandoConsultaDTO(null, dados.idMedico(), dados.idPaciente(), dados.horario()));
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity cancelarConsulta(@RequestBody @Valid CancelamentoConsultaDTO dados) {

		consultaService.cancelarConsulta(dados);

		return ResponseEntity.noContent().build();
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<DetalhandoConsultaDTO> listadoConsultas(
//			@RequestHeader(value = "X-Timezone", defaultValue = "UTC") String timezone, @PathVariable Long id) {
//		ZoneId zone = ZoneId.of(timezone);
//
//		Consulta dados = repositorio.getReferenceById(id);
//
//		// if (dados.isPresent()) {
//
//		return ResponseEntity.ok(new DetalhandoConsultaDTO(dados));
//
//		// } else {
//		// return ResponseEntity.notFound().build();
//		// }
//
//	}
//
//	@GetMapping
//	public ResponseEntity<List<ListagemConsultaDTO>> listadoConsultas(
//			@RequestHeader(value = "X-Timezone", defaultValue = "UTC") String timezone) {
//		ZoneId zone = ZoneId.of(timezone);
//
//		List<ListagemConsultaDTO> dados = repositorio.findAll().stream().map(x -> new ListagemConsultaDTO(x, zone))
//				.toList();
//		return ResponseEntity.ok().body(dados);
//
//	}

//	@PostMapping()
//	@Transactional
//	public ResponseEntity adicionarConsulta(@RequestBody @Valid ConsultaDTO dados, UriComponentsBuilder uriBuilder) {
//		System.out.println(dados);
//
//		// service.validarConsulta(dados);
//
//		Optional<Medico> m = medicoRepositorio.findById(dados.idMedico());
//		System.out.println(m);
//		Optional<Paciente> p = pacienteRepositorio.findById(dados.idPaciente());
//		System.out.println(p);
//		Consulta consulta = new Consulta(p.get(), m.get(), dados.horario());
//		repositorio.save(consulta);
//
//		var uri = uriBuilder.path("consultas/{id}").buildAndExpand(consulta.getId()).toUri();
//
//		return ResponseEntity.created(uri).body(new DetalhandoConsultaDTO(consulta));
//		// return ResponseEntity.ok(null);
//	}

}
