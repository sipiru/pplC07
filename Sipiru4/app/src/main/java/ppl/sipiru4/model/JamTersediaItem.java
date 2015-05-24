package ppl.sipiru4.model;

import java.text.ParseException;

public class JamTersediaItem {
    private final String jamMulai;
    private final String jamSelesai;

    // the text for the ListView item title
    public JamTersediaItem(String jamMulai, String jamSelesai) {
        this.jamMulai = jamMulai;
        this.jamSelesai=jamSelesai;
    }

    public String getJamMulai() throws ParseException {
        return jamMulai;
    }
    public String getJamSelesai() {
        return jamSelesai;
    }
}
