package io.github.wonderf.steam.code.generator;

import io.github.wonderf.steam.code.generator.implementation.ConfirmationKey;

public interface SteamCodeGenerator {
    /**
     * Generate a Steam one time OTP code
     * @param sharedSecret Your TOTP shared_secret
     * @return 5 symbols code
     */
    String oneTimeCode(String sharedSecret);

    /**
     *
     * @param identity Steam account identity_secret
     * @return confirmation key and moment which it generated
     */
    ConfirmationKey confirmationKey(String identity);

    /**
     *
     * @param steamId Steam account steam id
     * @return sha1 generated id
     */
    String deviceId(String steamId);
}
