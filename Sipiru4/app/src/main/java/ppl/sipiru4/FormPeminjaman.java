package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gina on 4/12/2015.
 */
public class FormPeminjaman extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_peminjaman_ui);

        TextView editRuang = (TextView)findViewById(R.id.ruang);
        editRuang.setText("R2303");
        TextView editNama = (TextView)findViewById(R.id.nama);
        editNama.setText("Gina Andriyani");
        TextView editNPM = (TextView)findViewById(R.id.npm);
        editNPM.setText("1106022654");
        //TODO : isi sendiri
        EditText editPrihal = (EditText)findViewById(R.id.prihal);
        //TODO : get tanggal peminjaman dari jadwal tersedia yang dipilih
        TextView editTgl = (TextView)findViewById(R.id.tgl);
        editTgl.setText("20 Maret 2015");
        //TODO : get jam peminjaman dari jadwal tersedia yang dipilih
        TextView editJam = (TextView)findViewById(R.id.jam);
        editJam.setText("13.00 - 15.00");
        TextView fasilitas = (TextView)findViewById(R.id.fasilitas);
        fasilitas.setText("1 Proyektor");
        Button btnSubmit = (Button)findViewById(R.id.btnTolak);
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO : semua isian disimpan ke database
/*                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new DaftarRuangan());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                Toast.makeText(getApplicationContext(),
                        "Permohonan telah dikirim", Toast.LENGTH_LONG).show();
                finish();
                finish();
                finish();
                finish();
            }
        });

    }

}
