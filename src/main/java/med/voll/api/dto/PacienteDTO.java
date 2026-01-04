package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Paciente;

public record PacienteDTO(

		@NotBlank String nome,

		@NotBlank @Email String email,

		@NotBlank String telefone,

		@NotBlank @Pattern(regexp = "\\d{11}") String cpf,

		@NotNull @Valid EnderecoDTO endereco) {

	public PacienteDTO(Paciente p) {
		this(p.getNome(), p.getEmail(), p.getTelefone(), p.getCpf(), new EnderecoDTO(p.getEndereco()));
	}

}
