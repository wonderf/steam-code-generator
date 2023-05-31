package io.github.wonderf.steam.code.generator.implementation;

public class ConfirmationKey {
    private final int time;
    private final String key;


    public ConfirmationKey(int time, String key) {
        this.time = time;
        this.key = key;
    }

    public int getTime() {
        return time;
    }

    public String getKey() {
        return key;
    }
}
