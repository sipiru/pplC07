package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Gina on 4/12/2015.
 */
public class FormPeminjaman extends Activity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_peminjaman_ui);
        Button btnSubmit = (Button)findViewById(R.id.button1);
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),
                        "Permohonan telah dikirim", Toast.LENGTH_LONG).show();
            }
        });

    }

}
