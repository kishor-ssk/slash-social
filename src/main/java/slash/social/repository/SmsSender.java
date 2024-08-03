package slash.social.repository;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
