package ppl.sipiru4;

/**
 * Created by User on 30/03/2015.
 */
public class Constants {

    public static final String SVR_ADDR = "http://ppl-c07.cs.ui.ac.id/connect/";
    public static final String PESAN_ADDR = "http://ppl-c07.cs.ui.ac.id/pesan";
    public static final String RUANG_ADDR = "http://ppl-c07.cs.ui.ac.id/ruang";
    public static final String NOTIF_ADDR = "http://ppl-c07.cs.ui.ac.id/notif";
    public static final String PEMINJAMAN_ADDR = "http://ppl-c07.cs.ui.ac.id/connect/peminjaman";
    public static final String PINJAM_ADDR = "http://ppl-c07.cs.ui.ac.id/connect/pinjam";
    public static final String ACTIVEUSER_ADDR = "http://ppl-c07.cs.ui.ac.id/connect/activeuser";

    /** Batas waktu timeout untuk semua koneksi */
    public static final int TIMEOUT = 3000;

    /** Kunci kelas agar tidak ada objek yang dapat diinstansiasi */
    private Constants() {}
}
