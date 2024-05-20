package slash.social.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slash.social.entity.UserEntity;
import slash.social.repository.IUserRepository;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final List<String> GENDERS = Arrays.asList("male", "female");
    @Autowired
    private IUserRepository iUserRepository;

    @Transactional
    public String newUser(String emailId, String password) {
        if (isInputValid(emailId, password)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmailId(emailId);
            userEntity.setPassword(password);
            iUserRepository.save(userEntity);
            return "details are saved";
        }
        return "not saved";
    }

    public Boolean userValid(String emailId, String password) {
        UserEntity byEmailId = iUserRepository.findByEmailId(emailId);
        if(byEmailId.getPassword().equals(password)) {
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
}
