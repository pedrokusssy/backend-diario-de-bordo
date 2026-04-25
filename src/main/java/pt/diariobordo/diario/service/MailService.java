package pt.diariobordo.diario.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pt.diariobordo.diario.entity.MailsEnviado;
import pt.diariobordo.diario.entity.Pessoa;
import pt.diariobordo.diario.entity.enums.MailType;
import pt.diariobordo.diario.repository.MailsEnviadoRespositoryy;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@Service
public class MailService {

    private JavaMailSender mailSender;
    private MailsEnviadoRespositoryy mailsEnviadoRespositoryy;
    public MailService(JavaMailSender mailSender, MailsEnviadoRespositoryy mailsEnviadoRespositoryy) {
        this.mailSender = mailSender;
        this.mailsEnviadoRespositoryy = mailsEnviadoRespositoryy;
    }

    @Value("${spring.mail.username}")
    private String remetenteSmtp;
    @Transactional
    public void sendHtml(String from, String to, String subject, String htmlBody, MailType mailType, Pessoa pessoa) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setReplyTo("noreply@gmail.com", "Não Responder à este email");
        helper.setFrom(remetenteSmtp);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // true means this is HTML

        Set<String> anexos = new HashSet<>();
        MailsEnviado mailsEnviado = new MailsEnviado(from, to, pessoa,subject, mailType, htmlBody, anexos);

        mailsEnviadoRespositoryy.save(mailsEnviado);
        mailSender.send(message);

    }

}
