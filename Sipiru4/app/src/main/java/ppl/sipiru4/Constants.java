package ppl.sipiru4;

/**
 * Created by User on 30/03/2015.
 */
public class Constants {

    public static final String SVR_ADDR = "http://ppl-c07.cs.ui.ac.id/";
    public static final String PESAN_ADDR = "http://ppl-c07.cs.ui.ac.id/connect/kirim_pesan/";
    public static final String PESAN_GET_ADDR =  "http://ppl-c07.cs.ui.ac.id/connect/get_pesan/";
    public static final String PINJAM_ADDR = "http://ppl-c07.cs.ui.ac.id/connect/pinjam";

    /** Batas waktu timeout untuk semua koneksi */
    public static final int TIMEOUT = 3000;
    public static final String JSONTAG = "Sipiru:JSON";

    /** Kunci kelas agar tidak ada objek yang dapat diinstansiasi */
    private Constants() {}

}
