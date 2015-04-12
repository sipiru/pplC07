package ppl.sipiru4.Entities;

import java.util.GregorianCalendar;

/**
 * Created by User on 11/04/2015.
 */
public class Peminjaman {
    private User peminjam;
    private GregorianCalendar mulai;
    private GregorianCalendar selesai;
    private Ruangan ruangan;
    private boolean status;

    public Peminjaman(User peminjam, GregorianCalendar mulai, GregorianCalendar selesai, Ruangan ruangan) {
        this.peminjam = peminjam;
        this.mulai = mulai;
        this.selesai = selesai;
        this.ruangan = ruangan;
        this.status = false;
    }

    public Peminjaman(User peminjam, GregorianCalendar mulai, GregorianCalendar selesai, Ruangan ruangan, boolean status) {
        this.peminjam = peminjam;
        this.mulai = mulai;
        this.selesai = selesai;
        this.ruangan = ruangan;
        this.status = status;
    }

    public User getPeminjam() {
        return peminjam;
    }

    public GregorianCalendar getMulai() {
        return mulai;
    }

    public GregorianCalendar getSelesai() {
        return selesai;
    }

    public Ruangan getRuangan() {
        return ruangan;
    }

    public boolean getStatus() {return status;}

    public void setujui() {status = true;}
}
