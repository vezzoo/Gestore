package it.lucaVezzoli.Application;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

    private static String getUpdate() {
        return "";
    }

    public static String getEncoded(String data) {
        String generated = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(getUpdate().getBytes(StandardCharsets.UTF_8));
            byte[] dataa = md.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder a = new StringBuilder();
            for (int i = 0; i < dataa.length; i++)
                a.append(Integer.toString((dataa[i] & 0xFF) + 0x100, 16).substring(1));
            generated = a.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generated;
    }

}
