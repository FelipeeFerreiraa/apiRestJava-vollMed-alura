package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarPacienteDTO(
		
		@NotNull
		Long id,
		String nome, 
		String email,
		String telefone, 
		EnderecoDTO endereco) {

}
