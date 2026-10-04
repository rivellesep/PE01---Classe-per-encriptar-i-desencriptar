import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ClasseAES {

    public static String encripta(String missatge, String clau) throws Exception {
        String missatgeXifrat = "";

        byte[] bytesClau = clau.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec clauAES = new SecretKeySpec(bytesClau, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clauAES);

        byte[] bytesMissatge = missatge.getBytes(StandardCharsets.UTF_8);

        byte[] bytesXifrats = cipher.doFinal(bytesMissatge);

        missatgeXifrat = Base64.getEncoder().encodeToString(bytesXifrats);

        return missatgeXifrat;
    }

    public static String desencripta(String missatgeXifrat, String clau) throws Exception {
        String missatge = "";

        byte[] bytesClau = clau.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec clauAES = new SecretKeySpec(bytesClau, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clauAES);

        byte[] bytesXifrats = Base64.getDecoder().decode(missatgeXifrat);

        byte[] bytesMissatge = cipher.doFinal(bytesXifrats);

        missatge = new String(bytesMissatge, StandardCharsets.UTF_8);

        return missatge;
    }
}
