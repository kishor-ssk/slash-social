package slash.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import slash.social.repository.SmsRequest;
//import slash.social.service.Service;
import slash.social.service.UserService;

@CrossOrigin
@RestController
public class UserController {

    private UserController userController;

    @Autowired
    public UserService userService;

//    private final Service service;
//
//    public UserController(Service service) {
//        this.service = service;
//    }

    @PostMapping("/new/user")
    public ResponseEntity<String> newUser(@RequestParam("email") String email,
                                          @RequestParam("password") String password) {
        return new ResponseEntity<String>(userService.newUser(email.trim(), password.trim()), HttpStatus.OK);
    }

    @GetMapping("/user/details/valid")
    public ResponseEntity<Boolean> userValid(@RequestParam("emailid") String emailId,
                                             @RequestParam("password") String password) {
        return new ResponseEntity<Boolean>(userService.userValid(emailId, password), HttpStatus.OK);
    }

    @GetMapping("/user/details")
    public ResponseEntity<Boolean> userDetails(@RequestParam("userid") Long userId,
                                               @RequestParam("name") String name,
                                               @RequestParam("dateofbirth") String dateOfBirth,
                                               @RequestParam("phonenumber") String phoneNumber,
                                               @RequestParam("gender") String gender) {
        return new ResponseEntity<Boolean>(userService.userDetails(userId, name.toLowerCase(), dateOfBirth, phoneNumber, gender.toLowerCase()), HttpStatus.OK);
    }

    @GetMapping("user/sent/email")
    public ResponseEntity<Boolean> userEmail(@RequestParam("fromEmailId") String fromEmailId,
                                             @RequestParam("toEmailId") String toEmailId) {
        return new ResponseEntity<Boolean>(userService.userEmail(fromEmailId, toEmailId), HttpStatus.OK);
    }

//    @PostMapping("user/sent/phno")
//    public void sendSms(@RequestBody SmsRequest smsRequest) {
//        service.sendSms(smsRequest);
//    }

    @GetMapping("user/pincode")
    public ResponseEntity<String> userPincode(@RequestParam("pincode") String pincode) {
        return new ResponseEntity<String>(userService.userPincode( pincode), HttpStatus.OK);
    }
}