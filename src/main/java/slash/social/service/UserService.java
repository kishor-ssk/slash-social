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
    public Boolean saveUserDetails(String name, String phoneNumber, String password) {
        if(isInputValid(name,phoneNumber,password)){
            UserEntity userEntity=new UserEntity();
            userEntity.setUserName(name);
            userEntity.setPhoneNumber(phoneNumber);
            userEntity.setPassword(password);
            return true;
        }
        return false;
    }

    public Boolean isDetailsValid(String phoneNumber, String password) {
        UserEntity byPhoneNumber = iUserRepository.findByPhoneNumber(phoneNumber);
        UserEntity byPassword = iUserRepository.findByPassword(password);
        if((byPhoneNumber != null) && (byPassword != null)){
            return true;
        }else{
            return false;
        }
    }

    private boolean isInputValid(String name, String phoneNumber, String password) {
        if (name.trim().isEmpty() || !name.replaceAll("\\s", "").matches("[a-zA-Z]+")) {
            return false;
        }
        if (!phoneNumber.matches("\\d+") || phoneNumber.length() != 10) {
            return false;
        }
        return true;
    }
}
