package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.AutenticacaoDTO;
import med.voll.api.dto.TokenJwtDTO;
import med.voll.api.model.Usuario;
import med.voll.api.service.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDTO dados) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentications = manager.authenticate(authenticationToken);

		var tokenJWT = tokenService.gerarToken((Usuario) authentications.getPrincipal());
		return ResponseEntity.ok(new TokenJwtDTO(tokenJWT));
	}

}
