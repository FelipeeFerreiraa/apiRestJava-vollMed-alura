package med.voll.api.model;

import jakarta.persistence.Embeddable;
import med.voll.api.dto.EnderecoDTO;

@Embeddable
public class Endereco {

	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String numero;
	private String complemento;

	public Endereco() {

	}

	public Endereco(EnderecoDTO e) {
		this.logradouro = e.logradouro();
		this.bairro = e.bairro();
		this.cep = e.cep();
		this.cidade = e.cidade();
		this.uf = e.uf();
		this.numero = e.numero();
		this.complemento = e.complemento();
	}

	public Endereco(String logradouro, String bairro, String cep, String cidade, String uf, String numero,
			String complemento) {

		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void atualizarEndereco(EnderecoDTO e) {
		if (e.logradouro() != null) {
			this.logradouro = e.logradouro();
		}

		if (e.bairro() != null) {
			this.bairro = e.bairro();
		}

		if (e.cep() != null) {
			this.cep = e.cep();
		}

		if (e.cidade() != null) {
			this.cidade = e.cidade();
		}

		if (e.uf() != null) {
			this.uf = e.uf();
		}

		if (e.numero() != null) {
			this.numero = e.numero();
		}

		if (e.complemento() != null) {
			this.complemento = e.complemento();
		}

	}

}
