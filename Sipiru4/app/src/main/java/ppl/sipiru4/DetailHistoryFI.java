package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gina on 4/24/2015.
 */
public class DetailHistoryFI extends Activity {
    final Context context = this;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_history_fi);
        //TODO : menampilkan detail peminjaman yang sudah disetujui oleh fasumITF
        TextView ruang = (TextView) findViewById(R.id.ruang);
        ruang.setText("3113");
        TextView nama = (TextView) findViewById(R.id.nama);
        nama.setText("Aditya Murda");
        TextView npm = (TextView) findViewById(R.id.npm);
        npm.setText("12602345");
        TextView prihal = (TextView) findViewById(R.id.prihal);
        prihal.setText("Rapat BEM");
        TextView tgl = (TextView) findViewById(R.id.tgl);
        tgl.setText("22 April 2015");
        TextView jam = (TextView) findViewById(R.id.jam);
        jam.setText("08.00-10.00");
        TextView fasilitas = (TextView) findViewById(R.id.fasilitas);
        fasilitas.setText("1 Mikrofon");
    }
}
