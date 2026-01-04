package med.voll.api.model;

import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AtualizarPacienteDTO;
import med.voll.api.dto.PacienteDTO;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String telefone;

	@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
	private String cpf;

	@Embedded
	private Endereco endereco;

	private Boolean ativo;

	@OneToMany(mappedBy = "paciente")
	private List<Consulta> consultas;

	public Paciente() {
	}

	public Paciente(PacienteDTO p) {
		this.ativo = true;
		this.nome = p.nome();
		this.email = p.email();
		this.telefone = p.telefone();
		this.cpf = p.cpf();
		this.endereco = new Endereco(p.endereco());
	}
	
	

	public Boolean getAtivo() {
		return ativo;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
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

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
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

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void atualizarPaciente(AtualizarPacienteDTO p) {

		if (p.nome() != null) {
			this.nome = p.nome();
		}

		if (p.telefone() != null) {
			this.telefone = p.telefone();
		}
		
		if (p.email() != null) {
			this.email = p.email();
		}

		this.endereco.atualizarEndereco(p.endereco());
	}

	public void exclusaoLogica() {
		this.ativo = false;

	}

}
