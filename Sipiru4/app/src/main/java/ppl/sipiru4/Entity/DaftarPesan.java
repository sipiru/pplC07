package ppl.sipiru4.Entity;

import java.util.ArrayList;

/**
 * Created by User on 28/04/2015.
 */
public class DaftarPesan {
    private ArrayList<Pesan> daftarPesan;

    public DaftarPesan(ArrayList<Pesan> daftarPesan) {
        this.daftarPesan = daftarPesan;
    }


    public ArrayList<Pesan> getDaftarPesan() {
        return daftarPesan;
    }

    public void tambahPesan(Pesan pesan) {
        daftarPesan.add(pesan);
    }
}
