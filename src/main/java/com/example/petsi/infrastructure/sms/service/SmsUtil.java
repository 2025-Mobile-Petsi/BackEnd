package com.example.petsi.infrastructure.sms.service;

import com.example.petsi.infrastructure.sms.config.CoolsmsProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class SmsUtil {

    private final CoolsmsProperties properties;
    private DefaultMessageService messageService;

    @PostConstruct
    public void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(
                properties.getApiKey(),
                properties.getApiSecret(),
                "https://api.coolsms.co.kr"
        );
    }

    public void sendVerificationCode(String to, String code) {
        Message message = new Message();
        message.setFrom(properties.getFrom());
        message.setTo(to);
        message.setText("[Petsi] 인증번호는 " + code + "입니다.");

        messageService.sendOne(new SingleMessageSendingRequest(message));
    }
}
