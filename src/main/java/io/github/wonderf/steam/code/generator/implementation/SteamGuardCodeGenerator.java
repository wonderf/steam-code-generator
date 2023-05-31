package io.github.wonderf.steam.code.generator.implementation;

import io.github.wonderf.steam.code.generator.SteamCodeGenerator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Formatter;

public class SteamGuardCodeGenerator implements SteamCodeGenerator {
    private final char[] chars={'2','3','4','5','6','7','8','9','B','C','D','F','G','H','J','K','M','N','P','Q','R','T','V','W','X','Y'};


    @Override
    public String oneTimeCode(String sharedSecret) {
        int timestamp = (int) (System.currentTimeMillis()/1000)/30;
        byte[] array = timeToUint64(timestamp);//uint 64
        byte[] keyBytes = Base64.getDecoder().decode(sharedSecret);
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

        // Get an hmac_sha1 Mac instance and initialize with the signing key
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(array);
            int begin = rawHmac[19] & 0xf;
            byte[] newArray = Arrays.copyOfRange(rawHmac, begin, begin + 4);
            long fullCode = fromArrayToLong(newArray) & 0x7fffffff;
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                int div = (int) (fullCode / chars.length);
                int mod = (int) (fullCode % chars.length);
                code.append(chars[mod]);
                fullCode = div;
            }
            return code.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Wrong parametrs for ecnrypt OTP code",e);
        }
    }
    @Override
    public ConfirmationKey confirmationKey(String identity) {
        byte[] decode = Base64.getDecoder().decode(identity);
        int timestamp = (int) (System.currentTimeMillis()/1000);
        byte[] timeBytes = timeToUint64(timestamp);
        byte[] tagBytes = "conf".getBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );

        SecretKeySpec signingKey = new SecretKeySpec(decode, "HmacSHA1");
        try {
            outputStream.write( timeBytes );
            outputStream.write( tagBytes );
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] bytes = mac.doFinal(outputStream.toByteArray());
            return new ConfirmationKey(timestamp,Base64.getEncoder().encodeToString(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem with SHA1",e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("wrong identity secret",e);
        } catch (IOException e) {
            throw new RuntimeException("IOE",e);
        }
    }

    @Override
    public String deviceId(String steamId) {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(steamId.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return "android:"+sha1.replaceAll("^([0-9a-f]{8})([0-9a-f]{4})([0-9a-f]{4})([0-9a-f]{4})([0-9a-f]{12}).*$", "$1-$2-$3-$4-$5");
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static long fromArrayToLong(byte[] array){
        long res = 0;
        res|=((long)(array[0]&0xff)<<24);
        res|=((long)(array[1]&0xff)<<16);
        res|=((long)(array[2]&0xff)<<8);
        res|=(array[3]&0xff);
        return res;
    }
    private static byte[] timeToUint64(int timestamp){
        byte[] res = new byte[8];
        for(int i=4;i<8;i++){
            res[i] = (byte)(timestamp>>24-8*(i-4) & 0xff);
        }
        return res;
    }
}
