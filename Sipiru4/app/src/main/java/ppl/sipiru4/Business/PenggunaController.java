package ppl.sipiru4.Business;

import ppl.sipiru4.Entity.User;

/**
 * Created by User on 12/04/2015.
 */
public class PenggunaController {

    private static User currentPengguna = null;

    private PenggunaController() {}

    public static void loginPengguna(User user) throws ConnectionErrorException, ParseErrorException {
        currentPengguna = user;
    }

    public static User getCurrentPengguna() {
        if (currentPengguna != null) {
            return currentPengguna;
        }
        return null;
    }
}