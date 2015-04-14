package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
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
        //TODO : semua isian disimpan ke database
        EditText editRuang = (EditText)findViewById(R.id.editIsiPesan);
        EditText editNama = (EditText)findViewById(R.id.editNama);
        EditText editNPM = (EditText)findViewById(R.id.editNPM);
        EditText editPrihal = (EditText)findViewById(R.id.editPrihal);
        //TODO : get tanggal peminjaman dari jadwal tersedia yang dipilih
        EditText editTgl = (EditText)findViewById(R.id.editTgl);
        //TODO : get jam peminjaman dari jadwal tersedia yang dipilih
        EditText editJam = (EditText)findViewById(R.id.editJam);

        Button btnSubmit = (Button)findViewById(R.id.button1);
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO : kembali ke halaman cariruangan
                Toast.makeText(getApplicationContext(),
                        "Permohonan telah dikirim", Toast.LENGTH_LONG).show();
            }
        });

    }

}
