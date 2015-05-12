package ppl.sipiru4;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;

public class DetailHistoryMR extends Activity {
    Peminjaman peminjaman;
    Context context = this;
    Bundle b;
    private static String file_url = "http://api.androidhive.info/progressdialog/hive.jpg";
    public static final int progress_bar_type = 0;
    // Progress Dialog Object
    private ProgressDialog pDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_history_mr);

        // mendapatkan nilai-nilai yang dioper dari DaftarPendingMR.class
        b = getIntent().getExtras();
        peminjaman = b.getParcelable("peminjaman");
        Log.e("peminjaman", peminjaman.getKodeRuangan() + " " + peminjaman.getNamaP() + " " + peminjaman.getId());

        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText(peminjaman.getKodeRuangan());

        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText(peminjaman.getNamaP());

        TextView username = (TextView)findViewById(R.id.username);
        username.setText(peminjaman.getUsernameP());

        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText(peminjaman.getPerihal());

        TextView kegiatan = (TextView)findViewById(R.id.kegiatan);
        kegiatan.setText(peminjaman.getKegiatan());

        TextView waktuMulai = (TextView)findViewById(R.id.waktuMulai);
        waktuMulai.setText(peminjaman.getMulai());

        TextView waktuSelesai = (TextView)findViewById(R.id.waktuSelesai);
        waktuSelesai.setText(peminjaman.getSelesai());

        TextView peralatan = (TextView)findViewById(R.id.peralatan);
        peralatan.setText(peminjaman.getPeralatan());

        Button download = (Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory().getPath()+"/jai_ho.mp3");
                // Check if the Music file already exists
                if (file.exists()) {
                    Toast.makeText(getApplicationContext(), "File already exist under SD card", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "File doesn't exist under SD Card, downloading Mp3 from Internet", Toast.LENGTH_LONG).show();
                    // Trigger Async Task (onPreExecute method)
                    new DownloadFileFromURL().execute(file_url);
                }
            }
        });
    }
    /**
     * Showing Dialog
     * */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {
        JSONArray hasil;
//        JSONObject object;
        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
//            super.onPreExecute();
//            pDialog = new ProgressDialog(context);
//            pDialog.setMessage("Logging in...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(true);
//            pDialog.show();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                OutputStream output = new FileOutputStream("/sdcard/downloadedfile.jpg");
                hasil = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/peminjaman/");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
//            // dismiss the dialog after the file was downloaded
//            dismissDialog(progress_bar_type);
            pDialog.dismiss();
            Toast.makeText(context, hasil.toString(),Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(context, MainActivityMR.class);
//            startActivity(i);
        }
    }
}


