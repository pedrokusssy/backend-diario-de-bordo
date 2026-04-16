package pt.diariobordo.diario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.diariobordo.diario.entity.Diario;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiarioRepository extends JpaRepository<Diario, UUID> {
    List<Diario> findDiarioByFormandoId(UUID formandoId);
}
