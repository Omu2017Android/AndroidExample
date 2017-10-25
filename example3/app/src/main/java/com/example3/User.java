package com.example3;

/**
 * Created by Furkan on 25.10.2017.
 */

public class User {
    private String fullName;
    private boolean ogrenciMi;

    public User(String fullName, boolean ogrenciMi) {
        super();
        this.fullName = fullName;
        this.ogrenciMi = ogrenciMi;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isOgrenciMi() {
        return ogrenciMi;
    }

    public void setOgrenciMi(boolean ogrenciMi) {
        this.ogrenciMi = ogrenciMi;
    }
}
