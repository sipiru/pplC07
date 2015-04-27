package ppl.sipiru4.model;


public class JamTersediaItem {

    public final String jamMulai;
    public final String jamSelesai;

    // the text for the ListView item title
    public JamTersediaItem(String jamMulai, String jamSelesai) {

        this.jamMulai = jamMulai;
        this.jamSelesai=jamSelesai;
    }

    public String getJamMulai() {
        return this.jamMulai;

    }
    public String getJamSelesai() {
        return this.jamSelesai;

    }

    public String setJamMulai() {
        return jamMulai;
    }
    public String setJamSelesai() {
        return jamSelesai;
    }
}
