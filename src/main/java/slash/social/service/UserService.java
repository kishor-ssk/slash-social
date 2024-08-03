package slash.social.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import slash.social.configuration.TwilioConfiguration;
import slash.social.entity.UserEntity;
import slash.social.repository.IUserRepository;
import slash.social.repository.SmsRequest;
import slash.social.repository.SmsSender;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final List<String> GENDERS = Arrays.asList("male", "female");
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    public String newUser(String email, String password) {
        if (isInputValid(email, password)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity.setPassword(password);
            iUserRepository.save(userEntity);
            return "details are saved";
        }
        return "not saved";
    }

    public Boolean userValid(String email, String password) {
        UserEntity byEmail = iUserRepository.findByEmail(email);
        if(byEmail.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInputValid(String emailId, String password) {
        return true;
    }

    private boolean isDetailsValid(String name, String dateOfBirth, String phoneNumber, String gender){
        if (!GENDERS.contains(gender)){
            return false;
        }
        if (name.trim().isEmpty() || !name.replaceAll("\\s", "").matches("[a-zA-Z]+")) {
            return false;
        }
        if (!phoneNumber.matches("\\d+") || phoneNumber.length() != 10) {
            return false;
        }
        return true;
    }

    public Boolean userDetails(Long userId, String name, String dateOfBirth, String phoneNumber, String gender) {
        UserEntity byUserId = iUserRepository.findByUserId(userId);
        if(userId != null){
            byUserId.setName(name);
            byUserId.setDateOfBirth(dateOfBirth);
            byUserId.setPhoneNumber(phoneNumber);
            byUserId.setGender(gender);
            iUserRepository.save(byUserId);
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean userEmail(String fromEmailId, String toEmailId){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmailId);
        simpleMailMessage.setTo(toEmailId);
        simpleMailMessage.setText("body");
        simpleMailMessage.setSubject("subject");
        javaMailSender.send(simpleMailMessage);
        return true;
    }

    @Service("twilio")
    public class TwilioSmsSender implements SmsSender {

        private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TwilioSmsSender.class);
        private final TwilioConfiguration twilioConfiguration;

        @Autowired
        public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
            this.twilioConfiguration = twilioConfiguration;
        }

        @Override
        public void sendSms(SmsRequest smsRequest) {
            if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
                PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
                PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
                String message = smsRequest.getMessage();
                MessageCreator creator = Message.creator(
                        to,
                        from,
                        message);
                creator.create();
                LOGGER.info("Send sms");
            } else{
                throw new IllegalArgumentException("phno no");
            }
        }

        private boolean isPhoneNumberValid(String phoneNumber) {
            return true;
        }
    }
}
