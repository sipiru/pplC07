package ppl.sipiru4;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.User;
import ppl.sipiru4.model.DetailPermohonan;

public class FormPeminjaman extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_peminjaman_ui);
        final String username = User.getUsername(this);

        final EditText ruang = (EditText)findViewById(R.id.ruang);
        ruang.setText(User.getKodeRuangan(this));

        final EditText nama = (EditText)findViewById(R.id.nama);
        nama.setText(User.getNama(this));

        final EditText perihal = (EditText)findViewById(R.id.perihal);
        final EditText kegiatan = (EditText)findViewById(R.id.kegiatan);

        final EditText waktuMulai = (EditText)findViewById(R.id.waktuMulai);
        waktuMulai.setText(User.getWaktuMulai(this));

        final EditText waktuSelesai = (EditText)findViewById(R.id.waktuSelesai);
        waktuSelesai.setText(User.getWaktuSelesai(this));

        final EditText peralatan = (EditText)findViewById(R.id.peralatan);

        Button btnSubmit = (Button)findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO : semua isian disimpan ke database
/*                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new DaftarRuangan());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                String token1 = perihal.getText().toString();
                String token2 = kegiatan.getText().toString();
//                Log.e("perihal",token1.length()+"");
//                Log.e("kegiatan",token2.length()+"");
                Log.e("check1",""+token1.equals("(\\s)+"));
                if (token1.length()==0||token2.length()==0||token1.equals("(\\s)+")||token2.equals("(\\s)+")) {
                    Toast.makeText(getApplicationContext(), "isian perihal atau kegiatan tidak valid", Toast.LENGTH_SHORT).show();
                }
                else {
                    int statusP;

                    if (peralatan.getText().equals("\\s+")||peralatan.getText().length()==0){
                        statusP = 0;
                    }
                    else  {
                        statusP = 1;
                    }
                    Log.e("username",username);
                    String namaP = nama.getText().toString().replaceAll(" ","%20");
                    Log.e("nama", namaP);
                    String waktuAwal = waktuMulai.getText().toString().replaceAll(" ","%20");
                    Log.e("waktuAwal", waktuAwal);
                    String waktuAkhir = waktuSelesai.getText().toString().replaceAll(" ","%20");
                    Log.e("waktuAkhir", waktuAkhir);

                    String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/mengajukanPeminjaman/"
                            + username+"&"+namaP+"&"+statusP+"&"+ruang.getText()+"&"
                            +waktuAwal+"&"+waktuAkhir+"&"+perihal.getText()+"&"+peralatan.getText()+"&0/");
                    if (notif.equals("sukses")){
                        Toast.makeText(getApplicationContext(), "permohonan berhasil disubmit", Toast.LENGTH_SHORT).show();
                        FormPeminjaman.this.finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "ruangan tidak bisa dipinjam", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
