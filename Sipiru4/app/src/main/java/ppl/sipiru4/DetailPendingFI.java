package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.Entity.User;
import ppl.sipiru4.controller.PeminjamanController;
import ppl.sipiru4.controller.PenggunaController;

public class DetailPendingFI extends Activity {
    final Context context = this;
    PeminjamanController peminjamanController;
    Bundle b;
    SharedPreferences setting;
    PenggunaController penggunaController;
    String editAlat;
    EditText peralatan;
    String previousAlat;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar()!=null) {
            getActionBar().setTitle("Detail Permohonan Pending");
        }
        setContentView(R.layout.detail_pending_fi);
		
		// mendapatkan informasi user dari session manager
        setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);
        User user = new User(setting.getString(LoginActivity.KEY_USERNAME,null), setting.getString(LoginActivity.KEY_NAMA,null),
                setting.getString(LoginActivity.KEY_ROLE,null));
        penggunaController = new PenggunaController(user);

        // mendapatkan nilai-nilai yang dioper dari DaftarPendingFI.class
        b = getIntent().getExtras();
        if (b!=null){
            Peminjaman peminjaman = b.getParcelable("peminjaman");
            peminjamanController = new PeminjamanController(peminjaman);

            Log.e("peminjaman", peminjamanController.getPeminjaman().getKodeRuangan() + " " + peminjamanController.getPeminjaman().getNamaP()
                    + " " + peminjamanController.getPeminjaman().getId());

            String[] input1 = peminjamanController.getPeminjaman().getMulai().split(" ");
            String[] input2 = peminjamanController.getPeminjaman().getSelesai().split(" ");
            String[] format1 = input1[0].split("-");
            String[] format2 = input2[0].split("-");
            String date1 = format1[2]+"-"+format1[1] + "-" + format1[0] + " " + input1[1];
            String date2 = format2[2]+"-"+format2[1] + "-" + format2[0] + " " + input2[1];
            String dateView1 = null;
            String dateView2 = null;
            try {
				// format tanggal agar lebih mudah dibaca pengguna
                Date init1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date1);
                dateView1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(init1);
                Date init2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date2);
                dateView2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(init2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            TextView ruang = (TextView)findViewById(R.id.ruang);
            ruang.setText(peminjamanController.getPeminjaman().getKodeRuangan());

            TextView nama = (TextView)findViewById(R.id.nama);
            nama.setText(peminjamanController.getPeminjaman().getNamaP());

            TextView username = (TextView)findViewById(R.id.username);
            username.setText(peminjamanController.getPeminjaman().getUsernameP());

            TextView prihal = (TextView)findViewById(R.id.prihal);
            prihal.setText(peminjamanController.getPeminjaman().getPerihal());

            TextView kegiatan = (TextView) findViewById(R.id.kegiatan);
            kegiatan.setText(peminjamanController.getPeminjaman().getKegiatan());

            TextView waktuMulai = (TextView)findViewById(R.id.waktuMulai);
            waktuMulai.setText(dateView1);

            TextView waktuSelesai = (TextView)findViewById(R.id.waktuSelesai);
            waktuSelesai.setText(dateView2);

            peralatan = (EditText)findViewById(R.id.peralatan);
            peralatan.setText(peminjamanController.getPeminjaman().getPeralatan());
            editAlat = peralatan.getText().toString();
            previousAlat = editAlat;

			// button update untuk mengubah peralatan dari suatu peminjaman. jika tidak ada peralatan yang dipinjam peminjam, maka tidak diizinkan diubah.
            Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
            if (peralatan.getText().toString().trim().length()!=0) {
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (peralatan.getText().toString().trim().length()==0) {
                            Toast.makeText(context, "peralatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                            // set title
                            alertDialogBuilder.setTitle("Apakah anda yakin untuk mengupdate peralatan?");
                            // set dialog message
                            alertDialogBuilder
                                    .setMessage("Tekan Ya untuk mengupdate")
                                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            editAlat = peralatan.getText().toString().replaceAll(" ", "%20");
                                            previousAlat = editAlat;
                                            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                            if (networkInfo!=null && networkInfo.isConnected()) {
												
												// mengupdate database menggunakan webserver
                                                new TaskHelper1().execute("http://ppl-c07.cs.ui.ac.id/connect/updatePeralatan/"
                                                        + peminjamanController.getPeminjaman().getId() + "&" + editAlat);
                                            }
                                            else {
                                                Toast.makeText(context, "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    })
                                    .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            peralatan.setText(previousAlat);
                                            dialog.cancel();
                                        }
                                    });
                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    }
                });
            }
            else {
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Tidak bisa mengupdate peralatan karena peminjam tidak meminjam apapun", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            Button btnSetuju = (Button)findViewById(R.id.btnSetuju);
			// button setuju untuk menyetujui suatu peminjaman
            btnSetuju.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    // set title
                    alertDialogBuilder.setTitle("Apakah anda yakin untuk menyetujui permohonan ini?");
                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk menyetujui")
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
									// memeriksa koneksi internet
                                    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                    if (networkInfo!=null && networkInfo.isConnected()) {
										// mengupdate database menggunakan webserver
                                        new TaskHelper2().execute("http://ppl-c07.cs.ui.ac.id/connect/acceptByITF/"
                                                + peminjamanController.getPeminjaman().getId());
                                    }
                                    else {
                                        Toast.makeText(context, "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });

            Button btnTolak = (Button)findViewById(R.id.btnTolak);
			// button tolak untuk menolak peminjaman. penolak harus memasukkan alasan penolakan agar pengguna tahu alasan peminjaman ditolak.
            btnTolak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Alasan penolakan");

                    final EditText alasan = new EditText(context);

                    alasan.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(alasan);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final String alasanTolak = alasan.getText().toString();
                            if (alasanTolak.trim().length()==0) {
                                Toast.makeText(context, "alasan tidak boleh kosong" , Toast.LENGTH_SHORT).show();
                            }
                            else {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                // set title
                                alertDialogBuilder.setTitle("Apakah anda yakin untuk menolak permohonan ini?");
                                // set dialog message
                                alertDialogBuilder
                                        .setMessage("Tekan Ya untuk konfirmasi")
                                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
												// mengecek koneksi internet
                                                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                                if (networkInfo!=null && networkInfo.isConnected()) {
													// mengubah database menggunakan webserver
                                                    new TaskHelper2().execute("http://ppl-c07.cs.ui.ac.id/connect/rejectPeminjaman/"
                                                            + peminjamanController.getPeminjaman().getId() + "&"+alasanTolak.replaceAll(" ","%20"));
                                                }
                                                else {
                                                    Toast.makeText(context, "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        })
                                        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });
                                // create alert dialog
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }
                        }
                    });
                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            });
        }
        else {
            Toast.makeText(context, "Error mendapatkan data peminjaman", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper1 extends android.os.AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Sedang diproses...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                return JSONParser.getNotifFromURL(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String notif) {
            if (notif == null) {
                pDialog.dismiss();
                Toast.makeText(context, "gagal menghubungkan server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (notif.trim().equals("\"sukses\"")){
                Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
                peralatan.setText(editAlat);
            }
            else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MainActivityFI.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("user",penggunaController.getCurrentPengguna());
                i.putExtra("navPosition",0);
                startActivity(i);
            }
            pDialog.dismiss();
        }
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper2 extends android.os.AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Sedang diproses...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                return JSONParser.getNotifFromURL(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String notif) {
            if (notif == null) {
                pDialog.dismiss();
                Toast.makeText(context, "gagal menghubungkan server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (notif.trim().equals("\"sukses\"")){
                Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(getApplicationContext(),MainActivityFI.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.putExtra("user",penggunaController.getCurrentPengguna());
            i.putExtra("navPosition",0);
            startActivity(i);
            pDialog.dismiss();
        }
    }
}


