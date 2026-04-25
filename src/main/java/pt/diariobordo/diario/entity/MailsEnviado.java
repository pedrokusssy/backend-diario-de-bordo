package pt.diariobordo.diario.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import pt.diariobordo.diario.entity.enums.MailType;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table

@EqualsAndHashCode
@ToString
public class MailsEnviado{

    @Id
    private UUID id;
    private String sender;
    private MailType mailType;
    private String mailSentTo;
    private String mailSubject;
    @Column(columnDefinition = "TEXT")
    private String mailBody;

    private Set<String> anexos;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa receiver;
    private LocalDateTime mailSentAt;

    public MailsEnviado(){}

    public MailsEnviado(String sender, String mailSentTo, Pessoa receiver, String subject, MailType mailType, String mailBody,  Set<String> anexos) {
        this.id = UUID.randomUUID();
        this.sender = sender;
        this.mailType = mailType;
        this.mailSentTo = mailSentTo;
        this.mailSubject = subject;
        this.mailBody = mailBody;
        this.anexos = anexos;
        this.receiver = receiver;
        this.mailSentAt = LocalDateTime.now();
    }

}
