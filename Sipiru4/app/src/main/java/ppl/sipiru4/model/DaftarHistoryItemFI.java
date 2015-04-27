package ppl.sipiru4.model;


public class DaftarHistoryItemFI {

    public final String npmPeminjam;
    public final String ruangan;
    public final String alat;

    // the text for the ListView item title
    public DaftarHistoryItemFI(String npmPeminjam, String ruangan, String alat) {

        this.npmPeminjam = npmPeminjam;
        this.ruangan = ruangan;
        this.alat = alat;
    }

    public String getNpmPeminjam() {
        return this.npmPeminjam;
    }
    public String getRuangan() {
        return this.ruangan;
    }
    public String getalat() {
        return this.alat;
    }

    public String setnpmPeminjam() {
        return npmPeminjam;
    }
    public String setRuangan() {
        return ruangan;
    }
    public String setalat() {
        return alat;
    }

}



