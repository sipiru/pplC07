package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPesan extends Activity {



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pesan_p);
        //TODO menampilkan pengirim dan isi pesan
        EditText dari = (EditText)findViewById(R.id.editDari);
        TextView isi = (TextView)findViewById(R.id.editIsi);
        EditText balas = (EditText)findViewById(R.id.balas);
        Button kirim = (Button)findViewById(R.id.buttonKirim);
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : mengirimkan pesan balasan ke penerima
                Toast.makeText(getApplicationContext(), "Pesan sudah terkirim",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}


