package ppl.sipiru4.Entities;

import java.util.ArrayList;

/**
 * Created by User on 12/04/2015.
 */
public class DaftarManajer {
    private ArrayList<Manajer> manajer;

    public DaftarManajer(ArrayList<Manajer> manajer) {
        this.manajer = manajer;
    }

    public void tambahManajer (Manajer manajer) {
        this.manajer.add(manajer);
    }
}
