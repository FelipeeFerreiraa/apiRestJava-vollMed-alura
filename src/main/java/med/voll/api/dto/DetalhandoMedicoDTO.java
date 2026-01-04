package med.voll.api.dto;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public record DetalhandoMedicoDTO(
		Long id, 
		String nome, 
		String email, 
		String crm, 
		String telefone,
		Especialidade especialidade, 
		EnderecoDTO endereco,
		Boolean ativo) {
	

	public DetalhandoMedicoDTO(Medico medico) {
		this(
				medico.getId(), 
				medico.getNome(), 
				medico.getEmail(), 
				medico.getCrm(), 
				medico.getTelefone(),
				medico.getEspecialidade(), 
				new EnderecoDTO(medico.getEndereco()),
				medico.getAtivo()
			);
	}

}
