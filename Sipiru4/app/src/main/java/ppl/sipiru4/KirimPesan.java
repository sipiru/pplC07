package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ppl.sipiru4.Business.JSONParser;
import ppl.sipiru4.Business.PenggunaController;

public class KirimPesan extends FragmentActivity{
    private Button btnKirimPesan;
    private EditText etPesan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirim_pesan_ui);

        etPesan = (EditText) findViewById(R.id.etPesan);
        btnKirimPesan = (Button) findViewById(R.id.btnKirimPesan);

        btnKirimPesan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesan = etPesan.getText().toString();
                JSONParser.getJSONfromURL(Constants.PESAN_ADDR +
                        PenggunaController.getCurrentPengguna());
            }
        });
    }
}