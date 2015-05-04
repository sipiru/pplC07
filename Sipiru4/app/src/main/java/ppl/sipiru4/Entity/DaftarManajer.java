package ppl.sipiru4.Entity;

import java.util.ArrayList;

public class DaftarManajer {
    private ArrayList<Manajer> manajer;

    public DaftarManajer(ArrayList<Manajer> manajer) {
        this.manajer = manajer;
    }

    public void tambahManajer (Manajer manajer) {
        this.manajer.add(manajer);
    }
}
