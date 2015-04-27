package ppl.sipiru4.Business;

import ppl.sipiru4.Constants;
import ppl.sipiru4.Entities.DaftarManajer;
import ppl.sipiru4.Entities.JSONHelper;
import ppl.sipiru4.Entities.Manajer;

import java.io.IOException;

/**
 * Created by User on 12/04/2015.
 */
public class ManajerController {

    public static DaftarManajer daftarManajer = null;

    public static boolean tambahManajer(Manajer manajer) {
        String strManajer= "{";

        strManajer += '}';

        try {
            JSONHelper.post(Constants.PINJAM_ADDR, strManajer);
        } catch (IOException e) {
            return false;
        }

        daftarManajer.tambahManajer(manajer);
        return true;
    }
}
