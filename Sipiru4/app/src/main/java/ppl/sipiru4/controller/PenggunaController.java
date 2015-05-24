package ppl.sipiru4.controller;

import ppl.sipiru4.Entity.User;

public class PenggunaController {
    User user;

    public PenggunaController(User user) {
        this.user = user;
    }

    public User getCurrentPengguna() {
        if (user != null) {
            return user;
        }
        return null;
    }


}