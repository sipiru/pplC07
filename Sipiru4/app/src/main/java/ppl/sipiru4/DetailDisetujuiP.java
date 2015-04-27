package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Gina on 4/24/2015.
 */
public class DetailDisetujuiP extends Activity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //TODO menampilkan data peminjaman seorang peminjam yang sudah disetujui semua manager dan sedang berlangsung
        setContentView(R.layout.detail_peminjaman_p);
        TextView ruang = (TextView)findViewById(R.id.ruang);
        TextView nama = (TextView)findViewById(R.id.nama);
        TextView npm = (TextView)findViewById(R.id.npm);
        TextView prihal = (TextView)findViewById(R.id.prihal);
        TextView tgl = (TextView)findViewById(R.id.tgl);
        TextView jam = (TextView)findViewById(R.id.jam);
        TextView permintaanLain = (TextView)findViewById(R.id.permintaanlain);
    }
}
