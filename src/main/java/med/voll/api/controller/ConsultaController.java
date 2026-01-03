package med.voll.api.controller;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.ConsultaDTO;
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

	@GetMapping
	public List<ListagemConsultaDTO> listadoConsultas(
			@RequestHeader(value = "X-Timezone", defaultValue = "UTC") String timezone) {
		ZoneId zone = ZoneId.of(timezone);

		List<ListagemConsultaDTO> dados = repositorio.findAll().stream().map(x -> new ListagemConsultaDTO(x, zone))
				.toList();
		return dados;

	}

	@PostMapping()
	@Transactional
	public void adicionarConsulta(@RequestBody @Valid ConsultaDTO dados) {
		System.out.println(dados);
		Consulta consulta = new Consulta(dados);

		service.validarConsulta(dados);

		repositorio.save(consulta);
	}

}
