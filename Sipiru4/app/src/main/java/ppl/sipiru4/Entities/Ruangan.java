package ppl.sipiru4.Entities;

/**
 * Created by User on 11/04/2015.
 */
public class Ruangan {
    private String kode;
    private String nama;
    private int kapasitas;
    private String deskripsi;

    public Ruangan(String kode, String nama, int kapasitas, String deskripsi) {
        this.kode = kode;
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
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
}