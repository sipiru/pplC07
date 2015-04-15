package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarPermohonanItemFI {

    public final String npmPeminjam;
    public final String ruangan;

    // the text for the ListView item title
    public DaftarPermohonanItemFI(String npmPeminjam, String ruangan) {

        this.npmPeminjam = npmPeminjam;
        this.ruangan = ruangan;
    }

    public String getNpmPeminjam() {
        return this.npmPeminjam;
    }
    public String getRuangan() {
        return this.ruangan;
    }

    public String setnpmPeminjam() {
        return npmPeminjam;
    }
    public String setRuangan() {
        return ruangan;
    }

}



