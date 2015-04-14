package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarPermohonanItemP {

    public final String ruangan;
    public final String status;

    // the text for the ListView item title
    public DaftarPermohonanItemP(String ruangan, String status) {

        this.ruangan = ruangan;
        this.status = status;
    }

    public String getRuangan() {
        return this.ruangan;

    }
    public String getStatus() {
        return this.status;

    }

    public String setRuangan() {
        return ruangan;
    }
    public String setStatus() {
        return status;
    }
}
