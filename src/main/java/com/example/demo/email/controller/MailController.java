package com.example.demo.email.controller;

import com.example.demo.email.dto.EmailCheckDto;
import com.example.demo.email.dto.EmailRequestDto;
import com.example.demo.email.service.MailSendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailController {

  private final MailSendService mailSendService;

  @PostMapping("/mailSend")
  public String mailSend(@RequestBody @Valid EmailRequestDto emailRequestDto) {
    return mailSendService.joinEmail(emailRequestDto.getEmail());
  }

  @PostMapping("/mailauthCheck")
  public String authCheck(@RequestBody @Valid EmailCheckDto emailCheckDto) {
    return mailSendService.checkAuthNum(emailCheckDto);
  }
}
