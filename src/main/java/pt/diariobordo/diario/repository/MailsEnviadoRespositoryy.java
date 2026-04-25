package pt.diariobordo.diario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.diariobordo.diario.entity.MailsEnviado;

import java.util.UUID;

public interface MailsEnviadoRespositoryy extends JpaRepository<MailsEnviado, UUID> {
}
