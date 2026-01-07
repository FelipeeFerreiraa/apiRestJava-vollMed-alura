package med.voll.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {

		var erros = ex.getFieldErrors();

		return ResponseEntity.badRequest().body(erros.stream().map(ErroValidacaoDTO::new).toList());
	}

	private record ErroValidacaoDTO(String campo, String msg) {

		public ErroValidacaoDTO(FieldError e) {
			this(e.getField(), e.getDefaultMessage());
		}
	}

}
