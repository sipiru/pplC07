package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarPeminjamanItemMR {

    public final String username;
    public final String ruangan;

    // the text for the ListView item title
    public DaftarPeminjamanItemMR(String username, String ruangan) {

        this.username = username;
        this.ruangan = ruangan;
    }

    public String getNpmPeminjam() {
        return this.username;
    }
    public String getRuangan() {
        return this.ruangan;
    }

    public String setnpmPeminjam() {
        return username;
    }
    public String setRuangan() {
        return ruangan;
    }

}



