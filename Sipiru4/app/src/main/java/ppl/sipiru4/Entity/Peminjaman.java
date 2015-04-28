package ppl.sipiru4.Entity;

public class Peminjaman {
    private int id;
    private String usernameP;
    private String namaP;
    private boolean statusPeminjam;
    private String mulai;
    private String selesai;
    private String perihal;
    private String kodeRuangan;
    private String peralatan;
    private int status;

    public Peminjaman(int id,String kodeRuangan, String namaP, String usernameP, boolean statusPeminjam, String perihal, String mulai, String selesai, String peralatan, int status) {
        this.id = id;
        this.kodeRuangan = kodeRuangan;
        this.namaP = namaP;
        this.usernameP = usernameP;
        this.statusPeminjam = statusPeminjam;
        this.perihal = perihal;
        this.mulai = mulai;
        this.selesai = selesai;
        this.peralatan = peralatan;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

}
