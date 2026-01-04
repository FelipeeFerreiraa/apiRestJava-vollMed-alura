package med.voll.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	public Page<Medico> findAllByAtivoTrue(Pageable paginacao);
	
	public Optional<Medico> findById(Long id);
}
