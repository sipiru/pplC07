package ppl.sipiru4;

import android.content.Intent;
import android.preference.EditTextPreference;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class KirimPesan extends Fragment {
	
	public KirimPesan(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.kirim_pesan_ui, container, false);
        Spinner penerima = (Spinner)rootView.findViewById(R.id.penerima);
        EditText isiPesan =(EditText)rootView.findViewById(R.id.editIsiPesan);
        Button button = (Button) rootView.findViewById(R.id.buttonKirim);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO : kirim pesan ke penerima
                Toast.makeText(getActivity(),"Pesan sudah terkirim",Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

}
