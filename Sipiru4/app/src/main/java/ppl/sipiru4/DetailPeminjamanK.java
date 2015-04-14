package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPeminjamanK extends Activity {



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_peminjaman_mk);
        //TODO : get data peminjaman yang sudah disetujui manager kemahasiswaan
        TextView ruang = (TextView)findViewById(R.id.ruang);
        TextView nama = (TextView)findViewById(R.id.nama);
        TextView npm = (TextView)findViewById(R.id.npm);
        TextView prihal = (TextView)findViewById(R.id.prihal);
        TextView tgl = (TextView)findViewById(R.id.tgl);
        TextView jam = (TextView)findViewById(R.id.jam);
        TextView permintaanLain = (TextView)findViewById(R.id.permintaanLain);
    }
}


