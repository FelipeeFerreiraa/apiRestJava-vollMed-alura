package med.voll.api.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	public Optional<Consulta> findById(Long id);

	public boolean existsByPacienteIdAndHorarioBetween(Long id, LocalDateTime primeiroHorario,
			LocalDateTime ultimoHorario);

	public boolean existsByMedicoIdAndHorarioAndMotivoCancelamentoIsNull(Long id, LocalDateTime horario);
}
