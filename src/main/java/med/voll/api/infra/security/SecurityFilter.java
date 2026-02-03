package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.repository.UsuarioRepository;
import med.voll.api.service.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// RECUPERA O TOKEN
		var tokenJWT = recuperarToken(request);
		System.out.println(tokenJWT);

		if (tokenJWT != null) {

			// PEGA O TOKEN NO CABEÇALHO
			var subject = tokenService.getSubject(tokenJWT);
			System.out.println(subject);

			// VALIDA LOGIN
			var usuario = usuarioRepository.findByLogin(subject);

			// AUTENTICA E 'FORÇA' A AUTENTICAÇÃO
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);
		System.out.println("FILTRO CHAMADO....");
	}

	private String recuperarToken(HttpServletRequest request) {

		var authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null) {

			return authorizationHeader.replace("Bearer ", "");
		}

		return null;

	}

}
