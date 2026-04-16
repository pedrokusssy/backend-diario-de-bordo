package pt.diariobordo.diario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.diariobordo.diario.entity.Formando;

import java.util.UUID;

@Repository
public interface FormandoRepository extends JpaRepository<Formando, UUID> {
}
