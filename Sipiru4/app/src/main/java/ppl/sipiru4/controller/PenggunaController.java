package ppl.sipiru4.controller;

import ppl.sipiru4.Entity.User;

public class PenggunaController {

    private static User currentPengguna = null;

    public PenggunaController() {}

    public static void loginPengguna(User user) {
        currentPengguna = user;
    }

    public static User getCurrentPengguna() {
        if (currentPengguna != null) {
            return currentPengguna;
        }
        return null;
    }
}