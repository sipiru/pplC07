package ppl.sipiru4.Entity;

import java.util.ArrayList;

/**
 * Created by User on 11/04/2015.
 */
public class DaftarPeminjaman {
    private ArrayList<Peminjaman> peminjaman;

    public DaftarPeminjaman(User pengguna, ArrayList<Peminjaman> peminjaman) {
        this.peminjaman = peminjaman;
    }

    public ArrayList<Peminjaman> getPeminjaman() {
        return peminjaman;
    }

    public void tambahPeminjaman(Peminjaman peminjaman) {
        this.peminjaman.add(peminjaman);
    }
}
