package ppl.sipiru4;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class KirimPesanController extends Fragment {
	
	public KirimPesanController(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.kirim_pesan_ui, container, false);
        Button button = (Button) rootView.findViewById(R.id.buttonCari);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),"Pesan sudah terkirim",Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

}
