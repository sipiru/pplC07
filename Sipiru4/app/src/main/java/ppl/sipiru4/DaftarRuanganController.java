package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Gina on 4/12/2015.
 */
public class DaftarRuanganController extends Activity{


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.daftar_ruangan);
        Button btnPinjam = (Button)findViewById(R.id.button1);
        btnPinjam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),FormPeminjamanController.class));
            }
        });

    }


}
