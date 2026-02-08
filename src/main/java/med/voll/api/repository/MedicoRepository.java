package med.voll.api.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	public Page<Medico> findAllByAtivoTrue(Pageable paginacao);
	
	public Optional<Medico> findById(Long id);
	
	@Query(""" 
			SELECT m FROM Medico m
			WHERE m.ativo = true
			AND m.especialidade = :especialidade
			AND m.id NOT IN(
						SELECT c.medico.id FROM Consulta c
						WHERE c.horario = :horario
			)
			ORDER BY RAND()
			LIMIT 1
			""")
	public Medico escolherMedicoDisponivelNaData(Especialidade especialidade, LocalDateTime horario);
}
