package pt.diariobordo.diario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.diariobordo.diario.entity.Actividade;

import java.util.UUID;

@Repository
public interface ActividadeRepositorry extends JpaRepository<Actividade, UUID> {
}
