package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
* Created by Gina on 4/24/2015.
*/
public class DetailDisetujuiK extends Activity {

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       setContentView(R.layout.detail_peminjaman_mk);
       //TODO : get data peminjaman yang sudah disetujui manager kemahasiswaan
       TextView ruang = (TextView)findViewById(R.id.ruang);
       ruang.setText("R3113");
       TextView nama = (TextView)findViewById(R.id.nama);
       nama.setText("Rauhil Fahmi");
       TextView npm = (TextView)findViewById(R.id.npm);
       npm.setText("1206023543");
       TextView prihal = (TextView)findViewById(R.id.prihal);
       prihal.setText("Rapat BEM");
       TextView tgl = (TextView)findViewById(R.id.tgl);
       tgl.setText("20 Maret 2015");
       TextView jam = (TextView)findViewById(R.id.jam);
       jam.setText("19.00 - 20.00");
       TextView fasilitas = (TextView)findViewById(R.id.fasilitas);
       fasilitas.setText("1 Proyektor");
   }
}
