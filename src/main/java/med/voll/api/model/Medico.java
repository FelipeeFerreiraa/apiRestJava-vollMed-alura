package med.voll.api.model;

import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarMedicoDTO;
import med.voll.api.dto.MedicoDTO;

@Entity
@Table(name = "medicos")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String crm;
	private String telefone;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;

	private Boolean ativo;

	public Medico() {
	}

	public Medico(String nome, String email, String crm, String telefone, Especialidade especialidade,
			Endereco endereco) {
		this.ativo = true;
		this.nome = nome;
		this.email = email;
		this.crm = crm;
		this.especialidade = especialidade;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public Medico(MedicoDTO m) {
		this.ativo = true;
		this.nome = m.nome();
		this.email = m.email();
		this.crm = m.crm();
		this.telefone = m.telefone();
		this.especialidade = m.especialidade();
		this.endereco = new Endereco(m.endereco());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCrm() {
		return crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Medico [nome=" + nome + ", email=" + email + ", crm=" + crm + ", especialidade=" + especialidade
				+ ", endereco=" + endereco + "]";
	}

	public void atualizarDados(@Valid AtualizarMedicoDTO json) {

		if (json.nome() != null) {
			this.nome = json.nome();
		}

		if (json.nome() != null) {
			this.telefone = json.telefone();
		}

		if (json.nome() != null) {
			this.endereco.atualizarEndereco(json.endereco());
		}

	}
	
	public void exclusaoLogica() {
		this.ativo = false;
	}

}
