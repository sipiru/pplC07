package ppl.sipiru4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ppl.sipiru4.Entity.JSONParser;

/**
 * Created by Gina on 4/24/2015.
 */
public class UpdateTabel extends Fragment {

    EditText kode;
    EditText kapasitas;
    EditText deskripsi;
    String fillKode;
    String fillKapasitas;
    String fillDeskripsi;
    Button update;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View rootView = inflater.inflate(R.layout.update_tabel_admin, container, false);

        kode = (EditText)rootView.findViewById(R.id.role);
        kapasitas = (EditText)rootView.findViewById(R.id.kapasitas);
        deskripsi = (EditText)rootView.findViewById(R.id.deskripsi);
        update = (Button)rootView.findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                fillKode = kode.getText().toString();
                fillKapasitas = kapasitas.getText().toString();
                fillDeskripsi = deskripsi.getText().toString();

                String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/updateRuangan/" + fillKode + "&" + fillKapasitas + "&" + fillDeskripsi+ "/");
                if(notif.trim().equals("\"sukses\"")){
                    Toast.makeText(getActivity(), "Update berhasil disimpan", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Update tidak berhasil dilakukan", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}
