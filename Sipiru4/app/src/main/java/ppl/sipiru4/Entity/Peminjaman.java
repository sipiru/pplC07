package ppl.sipiru4.Entity;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Peminjaman implements Parcelable{
    private int id;
    private String kodeRuangan;
    private String usernameP;
    private String namaP;
    private boolean statusPeminjam;
    private String mulai;
    private String selesai;
    private String perihal;
    private String kegiatan;
    private String peralatan;
    private String status;
    private Drawable gambar;

    public Peminjaman(int id,String kodeRuangan, String usernameP, String namaP, boolean statusPeminjam, String mulai, String selesai, String perihal, String kegiatan, String peralatan, String status, Drawable gambar) {
        this.id = id;
        this.kodeRuangan = kodeRuangan;
        this.usernameP = usernameP;
        this.namaP = namaP;
        this.statusPeminjam = statusPeminjam;
        this.mulai = mulai;
        this.selesai = selesai;
        this.perihal = perihal;
        this.kegiatan = kegiatan;
        this.peralatan = peralatan;
        this.status = status;
        this.gambar = gambar;
    }

    public Peminjaman(int id,String kodeRuangan, String usernameP, String namaP, boolean statusPeminjam, String mulai, String selesai, String perihal, String kegiatan, String peralatan, String status) {
        this.id = id;
        this.kodeRuangan = kodeRuangan;
        this.usernameP = usernameP;
        this.namaP = namaP;
        this.statusPeminjam = statusPeminjam;
        this.mulai = mulai;
        this.selesai = selesai;
        this.perihal = perihal;
        this.kegiatan = kegiatan;
        this.peralatan = peralatan;
        this.status = status;
    }

    public Peminjaman(Parcel source) {
        id = source.readInt();
        kodeRuangan = source.readString();
        usernameP = source.readString();
        namaP = source.readString();
        statusPeminjam = source.readInt() == 1;
        mulai = source.readString();
        selesai = source.readString();
        perihal = source.readString();
        kegiatan = source.readString();
        peralatan = source.readString();
        status = "" + source.readInt();
    }

    public int getId() {
        return id;
    }

    public String getNamaP() {
        return namaP;
    }

    public String getUsernameP() {
        return usernameP;
    }

    public boolean getStatusPeminjam() {
        return statusPeminjam;
    }

    public String getMulai() {
        return mulai;
    }

    public String getSelesai() {
        return selesai;
    }

    public String getKodeRuangan() {
        return kodeRuangan;
    }

    public String getPeralatan() {
        return peralatan;
    }

    public String getPerihal() {
        return perihal;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public String getStatus() {
        return status;
    }

    public Drawable getGambar(){
        return gambar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(kodeRuangan);
        dest.writeString(usernameP);
        dest.writeString(namaP);
        if (statusPeminjam) dest.writeInt(1);
        else dest.writeInt(0);
        dest.writeString(mulai);
        dest.writeString(selesai);
        dest.writeString(perihal);
        dest.writeString(kegiatan);
        dest.writeString(peralatan);
        dest.writeString(status);
    }

    public static final Parcelable.Creator<Peminjaman> CREATOR
            = new Parcelable.Creator<Peminjaman>() {

        @Override
        public Peminjaman createFromParcel(Parcel source) {
            return new Peminjaman(source);
        }

        @Override
        public Peminjaman[] newArray(int size) {
            return new Peminjaman[size];
        }
    };
}
