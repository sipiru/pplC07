package ppl.sipiru4;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ppl.sipiru4.Entity.JSONParser;

public class FormPeminjaman extends Activity {
    SharedPreferences setting;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_peminjaman_ui);

        Bundle b = getIntent().getExtras();

        String kodeRuangan = b.getString("kodeRuangan");
        String waktuAwal = b.getString("waktuAwal");
        String waktuAkhir = b.getString("waktuAkhir");

        setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);

        final String username = setting.getString(LoginActivity.KEY_USERNAME,null);

        final EditText ruang = (EditText)findViewById(R.id.ruang);
        ruang.setText(kodeRuangan);

        final EditText nama = (EditText)findViewById(R.id.nama);
        nama.setText(setting.getString(LoginActivity.KEY_NAMA,null));

        final EditText perihal = (EditText)findViewById(R.id.perihal);
        final EditText kegiatan = (EditText)findViewById(R.id.kegiatan);

        final EditText waktuMulai = (EditText)findViewById(R.id.waktuMulai);
        waktuMulai.setText(waktuAwal);

        final EditText waktuSelesai = (EditText)findViewById(R.id.waktuSelesai);
        waktuSelesai.setText(waktuAkhir);

        final EditText peralatan = (EditText)findViewById(R.id.peralatan);

        Button btnSubmit = (Button)findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
/*                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new DaftarRuangan());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                String token1 = perihal.getText()+"";
                String token2 = kegiatan.getText()+"";

                if (token1.trim().length()==0 || token2.trim().length()==0) {
                    Toast.makeText(getApplicationContext(), "Isian perihal atau kegiatan tidak valid", Toast.LENGTH_SHORT).show();
                }
                else {
                    int statusP;

                    if ((peralatan.getText()+"").trim().length()==0){
                        statusP = 0;
                        peralatan.setText("Tidak ada");
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
                    String alat = peralatan.getText().toString().replaceAll(" ","%20");

                    String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/mengajukanPeminjaman/"
                            + username+"&"+namaP+"&"+statusP+"&"+ruang.getText()+"&"
                            +waktuAwal+"&"+waktuAkhir+"&"+perihal.getText()+"&"+alat+"&0/");
//                    Toast.makeText(getApplicationContext(), notif + " length " + notif.length(), Toast.LENGTH_SHORT).show();
//                    finish();
                    if (notif.trim().equals("\"sukses\"")){
                        Toast.makeText(getApplicationContext(), "Permohonan berhasil disubmit", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Maaf, ruangan tidak bisa dipinjam. Silakan cek jadwal ruangan",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
