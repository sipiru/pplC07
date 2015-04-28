package ppl.sipiru4.controller;

import ppl.sipiru4.Constants;
import ppl.sipiru4.Entity.DaftarManajer;
import ppl.sipiru4.Entity.Manajer;

import java.io.IOException;


public class ManajerController {

    public static DaftarManajer daftarManajer = null;

    public static boolean tambahManajer(Manajer manajer) {
        String strManajer= "{";

        strManajer += '}';

//        try {
//            JSONHelper.post(Constants.PINJAM_ADDR, strManajer);
//        } catch (IOException e) {
//            return false;
//        }

        daftarManajer.tambahManajer(manajer);
        return true;
    }
}
