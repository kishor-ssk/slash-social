package slash.social.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import slash.social.repository.SmsRequest;

@org.springframework.stereotype.Service
public class Service {
    private final UserService.TwilioSmsSender twilioSmsSender;
    @Autowired
    public Service(UserService.TwilioSmsSender twilioSmsSender) {
        this.twilioSmsSender = twilioSmsSender;
    }

    public void sendSms(SmsRequest smsRequest){
        twilioSmsSender.sendSms(smsRequest);
    }
}
