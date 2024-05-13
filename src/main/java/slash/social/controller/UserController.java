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
    public ResponseEntity<Boolean> saveUserDetails(@RequestParam("name") String name,
                                                   @RequestParam("phonenumber") String phoneNumber,
                                                   @RequestParam("password") String password) {
        return new ResponseEntity<Boolean>(userService.saveUserDetails(name.toLowerCase(), phoneNumber, password.trim()), HttpStatus.OK);
    }

    @GetMapping("/user/details/valid")
    public ResponseEntity<Boolean> isDetailsValid(@RequestParam("phonenumber") String phoneNumber,
                                                   @RequestParam("password") String password ) {
        return new ResponseEntity<Boolean>(userService.isDetailsValid(phoneNumber, password), HttpStatus.OK);
    }
}
