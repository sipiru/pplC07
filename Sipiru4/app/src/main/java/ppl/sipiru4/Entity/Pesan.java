package ppl.sipiru4.Entity;

/**
 * Created by User on 28/04/2015.
 */
public class Pesan {

    private int idPesan;
    private String isiPesan;

    public Pesan(int idPesan, String isiPesan) {
        this.idPesan = idPesan;
        this.isiPesan = isiPesan;
    }

    public int getIdPesan() {
        return idPesan;
    }

    public String getPesan() {return isiPesan;}

    @Override
    public String toString() {return isiPesan;}
}
