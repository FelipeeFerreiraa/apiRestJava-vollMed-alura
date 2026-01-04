package med.voll.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	public Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
	
	public Optional<Paciente> findById(Long id);
}
