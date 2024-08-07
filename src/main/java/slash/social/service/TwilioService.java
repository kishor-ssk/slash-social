//package slash.social.service;
//
//import com.twilio.Twilio;
//import com.twilio.rest.verify.v2.service.Verification;
//import com.twilio.rest.verify.v2.service.VerificationCheck;
//
//import java.io.UnsupportedEncodingException;
//
//public class TwilioService {
//
//    private static final String ACCOUNT_SID = "AC93a9681bd4325def550aa5db3eb46f1b";
//    private static final String AUTH_TOKEN = "50ff0ceca3b00a27f259e3682fd44df4";
//
//    private static final String PATH_SERVICE_SID = "VA19785da40e62d5cdaa234139ac020c11";
//
//    public Boolean otpGenerate(String phoneNumber, String key) throws UnsupportedEncodingException {
//            phoneNumber = "+91" + phoneNumber;
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            Verification verification = Verification.creator(
//                            PATH_SERVICE_SID,
//                            phoneNumber,
//                            "whatsapp")
//                    .create();
//            return true;
//
//    }
//
//    public String verifyOtp(String phoneNumber, String otp) {
//        phoneNumber = "+91" + phoneNumber;
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        VerificationCheck verificationCheck = VerificationCheck.creator(
//                        "VA19785da40e62d5cdaa234139ac020c11")
//                .setTo(phoneNumber)
//                .setCode(otp)
//                .create();
//
//        System.out.println(verificationCheck.getStatus());
//        return verificationCheck.getStatus();
//    }
//}
