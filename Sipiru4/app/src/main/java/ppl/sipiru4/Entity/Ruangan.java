package ppl.sipiru4.Entity;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Ruangan implements Parcelable{
    private String kode;
    private String nama;
    private String kapasitas;
    private String deskripsi;
    private Drawable kotak;

    public Ruangan(String kode, String nama, String kapasitas, String deskripsi) {
        this.kode = kode;
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.deskripsi = deskripsi;
    }
    public Ruangan(String kode, String nama, String kapasitas, String deskripsi, Drawable kotak) {
        this.kode = kode;
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.deskripsi = deskripsi;
        this.kotak = kotak;
    }

    public Ruangan(Parcel source) {
        kode = source.readString();
        nama = source.readString();
        kapasitas = "" + source.readInt();
        deskripsi = source.readString();
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
    public Drawable getKotak(){
        return kotak;
    }
    public void setKotak(Drawable kotak){
        this.kotak = kotak;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kode);
        dest.writeString(nama);
        dest.writeString(kapasitas);
        dest.writeString(deskripsi);
    }

    public static final Parcelable.Creator<Ruangan> CREATOR
            = new Parcelable.Creator<Ruangan>() {

        @Override
        public Ruangan createFromParcel(Parcel source) {
            return new Ruangan(source);
        }

        @Override
        public Ruangan[] newArray(int size) {
            return new Ruangan[size];
        }
    };
}