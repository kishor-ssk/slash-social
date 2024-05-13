package slash.social.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slash.social.entity.UserEntity;
import slash.social.repository.IUserRepository;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Transactional
    public Boolean saveUserDetails(String emailId, String password) {
        if (isInputValid(emailId, password)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmailId(emailId);
            userEntity.setPassword(password);
            return true;
        }
        return false;
    }

    public Boolean isDetailsValid(String emailId, String password) {
        UserEntity byEmailId = iUserRepository.findByEmailId(emailId);
        UserEntity byPassword = iUserRepository.findByPassword(password);
        if ((byEmailId != null) && (byPassword != null)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInputValid(String emailId, String password) {
        if (emailId == "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}") {
            return true;
        }
        if (password == "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$") {
            return true;
        } else {
            return false;
        }
    }
}
