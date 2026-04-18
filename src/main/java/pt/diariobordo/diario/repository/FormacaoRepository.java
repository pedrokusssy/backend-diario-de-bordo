package pt.diariobordo.diario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pt.diariobordo.diario.entity.Formacao;

import java.util.Set;
import java.util.UUID;

@Repository
public interface FormacaoRepository extends JpaRepository<Formacao, UUID> {
    Set<Formacao> findAllByFormandosId(UUID formandoId);
    Formacao findByFormandosId(UUID formandoId);

}
