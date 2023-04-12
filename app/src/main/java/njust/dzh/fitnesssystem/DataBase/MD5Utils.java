package njust.dzh.fitnesssystem.DataBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String md5(String text) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("md5");

            byte[] result = messageDigest.digest(text.getBytes());


            StringBuffer sb = new StringBuffer();

            for (byte b : result){

                int number = b & 0xff;

                String hex = Integer.toHexString(number);
                if (hex.length() == 1){
                    sb.append("0" + hex);
                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

            return "";
        }
    }
}