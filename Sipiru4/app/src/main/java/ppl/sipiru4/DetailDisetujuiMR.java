package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gina on 4/24/2015.
 */
public class DetailDisetujuiMR extends Activity {



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_peminjaman_mr);
        //TODO menampilkan data peminjaman yang sudah disetujui manager ruangan
        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText("R2304");
        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText("Aditya Murda");
        TextView npm = (TextView)findViewById(R.id.npm);
        npm.setText("1106024563");
        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText("Rapat BEM");
        TextView tgl = (TextView)findViewById(R.id.tgl);
        tgl.setText("20 Maret 2015");
        TextView jam = (TextView)findViewById(R.id.jam);
        jam.setText("20.00 - 21.00");
        TextView fasilitas = (TextView)findViewById(R.id.fasilitas);
        fasilitas.setText("1 Proyektor");
        Button download = (Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : mengunduh berkas peminjaman
                Toast.makeText(getApplicationContext(), "start downloading ....",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
