package ppl.sipiru4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KirimPesan extends Activity{
    Button buttonSend;
    EditText textPhoneNo;
    EditText textSMS;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar()!=null) {
            getActionBar().setTitle("Kirim Pesan");
        }
        setContentView(R.layout.kirim_pesan_ui);
        context = this;

        buttonSend = (Button) findViewById(R.id.buttonSend);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) findViewById(R.id.editTextSMS);

        buttonSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String phoneNo = textPhoneNo.getText().toString();
                String sms = textSMS.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(context, "SMS terkirim",
                            Toast.LENGTH_LONG).show();
                    textPhoneNo.setText("");
                    textSMS.setText("");
                } catch (Exception e) {
                    Toast.makeText(context, "SMS gagal terkirim. coba lagi nanti",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}