package med.voll.api.dto;

import med.voll.api.model.Paciente;

public record DetalhandoPacienteDTO(String nome,

		String email,

		String telefone,

		String cpf,

		EnderecoDTO endereco, Boolean ativo) {

	public DetalhandoPacienteDTO(Paciente p) {
		this(p.getNome(), p.getEmail(), p.getTelefone(), p.getCpf(), new EnderecoDTO(p.getEndereco()), p.getAtivo());
	}

}
