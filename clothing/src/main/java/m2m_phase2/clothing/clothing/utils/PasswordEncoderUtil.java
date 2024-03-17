package m2m_phase2.clothing.clothing.utils;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class PasswordEncoderUtil {

	  // Mã hóa mật khẩu bằng thuật toán PBKDF2
    public static String encodePassword(String rawPassword) {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    // Xác minh mật khẩu đã mã hóa với mật khẩu gốc
    public static boolean verifyPassword(String rawPassword, String encodedPassword) {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
	
}