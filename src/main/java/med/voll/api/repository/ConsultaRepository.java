package med.voll.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	public Optional<Consulta> findById(Long id);
}
