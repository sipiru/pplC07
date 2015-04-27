package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Gina on 4/12/2015.
 */
public class FormPeminjaman extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_peminjaman_ui);

        EditText editRuang = (EditText)findViewById(R.id.ruang);
        EditText editNama = (EditText)findViewById(R.id.nama);
        EditText editNPM = (EditText)findViewById(R.id.npm);
        EditText editPrihal = (EditText)findViewById(R.id.prihal);
        //TODO : get tanggal peminjaman dari jadwal tersedia yang dipilih
        EditText editTgl = (EditText)findViewById(R.id.tgl);
        //TODO : get jam peminjaman dari jadwal tersedia yang dipilih
        EditText editJam = (EditText)findViewById(R.id.jam);

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
