package med.voll.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	@Query("""
			SELECT p.ativo
			FROM Paciente p
			WHERE p.id = :id
			""")
	public boolean findAtivoById(Long id);

	public Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
	
	public Optional<Paciente> findById(Long id);
}
