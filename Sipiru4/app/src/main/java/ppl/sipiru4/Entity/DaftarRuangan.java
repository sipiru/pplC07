package ppl.sipiru4.Entity;

import java.util.ArrayList;

public class DaftarRuangan {
    private ArrayList<Ruangan> ruangan;

    private DaftarRuangan() {}

    public DaftarRuangan(ArrayList<Ruangan> ruangan) {
        this.ruangan = ruangan;
    }

    public ArrayList<Ruangan> getRuangan() {
        return ruangan;
    }

    public void tambahRuangan(Ruangan ruangan) {
        this.ruangan.add(ruangan);
    }
    
    public Ruangan getRuanganByKode(String kode) {
        for (Ruangan r: ruangan) {
            if(r.getKode().equals(kode))
                return r;
        }
        return null;
    }
}
