package ppl.sipiru4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KirimPesan extends Fragment{
    Button buttonSend;
    EditText textPhoneNo;
    EditText textSMS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.kirim_pesan_ui, container, false);

        buttonSend = (Button) rootView.findViewById(R.id.buttonSend);
        textPhoneNo = (EditText) rootView.findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) rootView.findViewById(R.id.editTextSMS);

        buttonSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String phoneNo = textPhoneNo.getText().toString();
                String sms = textSMS.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getActivity(), "SMS terkirim",
                            Toast.LENGTH_LONG).show();
                    textPhoneNo.setText("");
                    textSMS.setText("");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "SMS gagal terkirim. coba lagi nanti",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }
}