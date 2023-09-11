package pri.roggu.ticketing.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private final static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean matchs(String inputPwd, String pwd) {
        return passwordEncoder.matches(inputPwd, pwd);
    }

}