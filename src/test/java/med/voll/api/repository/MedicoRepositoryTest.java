package med.voll.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.dto.EnderecoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.model.Consulta;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Deve retornar 'null' quando o único médico cadastrado, não estiver disponível na data.")
	void escolherMedicoDisponivelNaDataCenario1() {

		// --------- GIVEN ou ARRAGE
		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

		var medico = cadastrarMedico("Medico Teste", "MedicoTeste@gmail.com", "123456", "(37) 99966-5522",
				Especialidade.CARDIOLOGIA, retornaEnderecoDTO());

		var paciente = cadastrarPaciente("Paciente Teste", "PacienteTeste@gmail.com", "(37) 99966-5522", "12345678910",
				retornaEnderecoDTO());

		cadastrarConsulta(medico, paciente, proximaSegundaAs10);

		// --------- WHEN ou ACT
		var medicoLivre = medicoRepository.escolherMedicoDisponivelNaData(Especialidade.CARDIOLOGIA,
				proximaSegundaAs10);

		// --------- THEN ou ASSERT
		assertThat(medicoLivre).isNull();

	}

	@Test
	@DisplayName("Deve retornar 'Medico' quando ele estiver disponível na data.")
	void escolherMedicoDisponivelNaDataCenario2() {

		// --------- GIVEN ou ARRAGE
		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

		var medico = cadastrarMedico("Medico Teste", "MedicoTeste@gmail.com", "123456", "(37) 99966-5522",
				Especialidade.CARDIOLOGIA, retornaEnderecoDTO());

		// --------- WHEN ou ACT
		var medicoLivre = medicoRepository.escolherMedicoDisponivelNaData(Especialidade.CARDIOLOGIA,
				proximaSegundaAs10);

		// --------- THEN ou ASSERT
		assertThat(medicoLivre).isEqualTo(medico);

	}

	private EnderecoDTO retornaEnderecoDTO() {
		EnderecoDTO end = new EnderecoDTO(
				new Endereco("Rua ABC", "Bairro DEF", "3551900", "Londres Test", "SP", "404", "Casa esquina"));

		return end;
	}

	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
		em.persist(new Consulta(medico, paciente, data));
	}

	private Paciente cadastrarPaciente(String nome, String email, String telefone, String cpf, EnderecoDTO endereco) {
		var paciente = new Paciente(dadosPaciente(nome, email, telefone, cpf, endereco));
		em.persist(paciente);
		return paciente;
	}

	private PacienteDTO dadosPaciente(String nome, String email, String telefone, String cpf, EnderecoDTO endereco) {
		return new PacienteDTO(nome, email, telefone, cpf, endereco);

	}

	private Medico cadastrarMedico(String nome, String email, String crm, String telefone, Especialidade especialidade,
			EnderecoDTO endereco) {
		var medico = new Medico(dadosMedico(nome, email, crm, telefone, especialidade, endereco));
		em.persist(medico);
		return medico;
	}

	private MedicoDTO dadosMedico(String nome, String email, String crm, String telefone, Especialidade especialidade,
			EnderecoDTO endereco) {

		return new MedicoDTO(nome, email, crm, telefone, especialidade, endereco);
	}

}
