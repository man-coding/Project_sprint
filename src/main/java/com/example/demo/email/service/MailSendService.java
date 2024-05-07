package com.example.demo.email.service;

import com.example.demo.email.dto.EmailCheckDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Slf4j
@Service
public class MailSendService {

  @Value("${spring.mail.username}")
  private String setFrom;

  @Autowired
  private JavaMailSender mailSender;

  private int authNumber;

  public void makeRandomNumber() {
    Random r = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      sb.append(r.nextInt(10));
    }

    authNumber = Integer.parseInt(sb.toString());
  }

  public String joinEmail(String email) {
    if (Objects.isNull(email)) {
      log.error("[MailSendService] email should not be null");
      return null;
    }

    makeRandomNumber();
    String title = "회원 가입 인증 이메일 입니다.";
    StringBuilder sb = new StringBuilder();
    sb.append("Sprint를 방문해 주셔서 감사합니다.<br><br>인증번호는 ")
      .append(authNumber)
      .append("입니다.<br>인증번호를 제대로 입력해주세요");
    mailSend(setFrom, email, title, sb.toString());

    return Integer.toString(authNumber);
  }

  private void mailSend(String setFrom, String toMail, String title, String content) {
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
      helper.setFrom(setFrom);
      helper.setTo(toMail);
      helper.setSubject(title);
      helper.setText(content, true);
      mailSender.send(message);
    } catch (MessagingException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
  }

  public String checkAuthNum(EmailCheckDto emailCheckDto) {

  }
}
