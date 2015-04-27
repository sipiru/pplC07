package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarRuanganItem {

    public final String kode;
    public final String kapasitas;
    public final String deskripsi;


    // the text for the ListView item title
    public DaftarRuanganItem(String kode, String kapasitas, String deskripsi) {

        this.kode = kode;
        this.kapasitas = kapasitas;
        this.deskripsi = deskripsi;
    }

    public String getKode() {
        return this.kode;

    }
    public String getKapasitas() {
        return this.kapasitas;

    }
    public String getDeskripsi() {
        return this.deskripsi;

    }

    public String setKode()
    {
        return kode;
    }
    public String setKapasitas()
    {
        return kapasitas;
    }
    public String setDeskripsi()
    {
        return deskripsi;
    }
}
