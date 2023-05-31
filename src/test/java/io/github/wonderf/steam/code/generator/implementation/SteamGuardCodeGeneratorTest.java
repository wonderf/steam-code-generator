package io.github.wonderf.steam.code.generator.implementation;

import io.github.wonderf.steam.code.generator.SteamCodeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SteamGuardCodeGeneratorTest {
    private SteamCodeGenerator steamCodeGenerator = new SteamGuardCodeGenerator();

    @Test
    void oneTimeCodeTest() {
        String secret = System.getenv("secret");
        Assertions.assertNotNull(secret,"Secret is not set!");
        String code = steamCodeGenerator.oneTimeCode(secret);
        System.out.println(code);
        Assertions.assertEquals(5,code.length());
    }

    @Test
    void confirmationKeyTest() {
        String identity = System.getenv("identity");
        Assertions.assertNotNull(identity,"Identity is not set!");
        ConfirmationKey key = steamCodeGenerator.confirmationKey(identity);
        System.out.println(key.getKey());
    }

    @Test
    void deviceIdTest(){
        String steamId = System.getenv("steamId");
        Assertions.assertNotNull(steamId,"steamId is not set!");
        String s = steamCodeGenerator.deviceId(steamId);
        System.out.println(s);
    }
}