package slash.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import slash.social.service.UserService;

import java.io.UnsupportedEncodingException;

@CrossOrigin
@RestController
public class UserController {
    private UserController userController;

    @Autowired
    public UserService userService;

    @PostMapping("/user/save")
    public ResponseEntity<Boolean> saveUserDetails(@RequestParam("emailid") String emailId,
                                                   @RequestParam("password") String password) {
        return new ResponseEntity<Boolean>(userService.saveUserDetails(emailId.trim(), password.trim()), HttpStatus.OK);
    }

    @GetMapping("/user/details/valid")
    public ResponseEntity<Boolean> isDetailsValid(@RequestParam("emailid") String phoneNumber,
                                                   @RequestParam("password") String emailId ) {
        return new ResponseEntity<Boolean>(userService.isDetailsValid(phoneNumber, emailId), HttpStatus.OK);
    }
}
