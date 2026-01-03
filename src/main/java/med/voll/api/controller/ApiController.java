package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.service.ApiService;

@RestController
public class ApiController {

	@Autowired
	private ApiService service;

	@GetMapping("/felipe")
	public String obterRequest() {
		return service.obterDadosRequest();
	}
	

}
