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
public class CreateTabel extends Fragment {

    EditText kode;
    EditText nama;
    EditText kapasitas;
    EditText deskripsi;
    String fillKode, fillNama, fillKapasitas, fillDeskripsi;
    Button create;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.create_tabel_admin, container, false);

        kode = (EditText)rootView.findViewById(R.id.role);
        nama = (EditText)rootView.findViewById(R.id.id);
        kapasitas = (EditText)rootView.findViewById(R.id.kapasitas);
        deskripsi = (EditText)rootView.findViewById(R.id.deskripsi);
        create = (Button)rootView.findViewById(R.id.btnCreate);
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                fillKode = kode.getText().toString();
                fillNama = nama.getText().toString();
                fillKapasitas = kapasitas.getText().toString();
                fillDeskripsi = deskripsi.getText().toString();

                String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/menambahRuangan/" + fillKode + "&" + fillNama+ "&" + fillKapasitas + "&" + fillDeskripsi+"/");
                if(notif.trim().equals("\"sukses\"")){
                    Toast.makeText(getActivity(), "Tabel baru baru berhasil disimpan", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "Error. Create tabel ruangan tidak dapat dilakukan",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        return rootView;
    }
}
