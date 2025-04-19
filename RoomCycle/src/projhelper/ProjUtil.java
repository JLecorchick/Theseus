package projhelper;

import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class ProjUtil {
    private static final Properties prop = new Properties();

    static {
        try (InputStream in = ProjUtil.class.getResourceAsStream(
        		"/projhelper/config/config.properties")) {
            if (in == null) throw new RuntimeException("config.properties not found on classpath!");
            prop.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key, "").trim();
    }

    public static String getSHA(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(getProperty("sec.sha"));
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hex = new StringBuilder(number.toString(16));
            while (hex.length() < 64) hex.insert(0, '0');
            return hex.toString();
        } catch (Exception ex) {
            throw new RuntimeException("SHA error", ex);
        }
    }

    public static String getResourcePath(String source) {
        try {
            return ProjUtil.class.getResource(source).getPath();
        } catch(Exception ex) {
            throw new RuntimeException("Resource not found: " + source, ex);
        }
    }

    private static Cipher getCipherInstance(int mode) throws Exception {
        byte[] iv = getProperty("sec.iv").getBytes();
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(
            getProperty("sec.key").toCharArray(),
            getProperty("sec.salt").getBytes(),
            65536, 256
        );
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(mode, secretKey, ivspec);
        return cipher;
    }

    public static String encrypt(String msg) {
        try { return Base64.getEncoder().encodeToString(
                    getCipherInstance(Cipher.ENCRYPT_MODE).doFinal(msg.getBytes(StandardCharsets.UTF_8))
               );
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }

    public static String decrypt(String encMsg) {
        try { return new String(
                    getCipherInstance(Cipher.DECRYPT_MODE).doFinal(Base64.getDecoder().decode(encMsg))
               );
        } catch (Exception e) {
            throw new RuntimeException("Decryption error", e);
        }
    }
}
