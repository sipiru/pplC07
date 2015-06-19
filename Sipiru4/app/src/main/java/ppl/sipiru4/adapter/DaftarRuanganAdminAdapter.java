package ppl.sipiru4.adapter;

import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.MainActivityA;
import ppl.sipiru4.R;
import java.io.IOException;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DaftarRuanganAdminAdapter extends ArrayAdapter<Ruangan> {

    public DaftarRuanganAdminAdapter(Context context, ArrayList<Ruangan> items) {
        super(context, R.layout.ui_tiap_list_ruangan_admin, items );
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_ruangan_admin, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.kodeRuangan = (TextView) convertView.findViewById(R.id.kode);
            viewHolder.namaRuangan = (TextView) convertView.findViewById(R.id.nama);
            viewHolder.kapasitas = (TextView) convertView.findViewById(R.id.kapasitas);
            viewHolder.btnHapus = (Button) convertView.findViewById(R.id.btnHapus);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        Ruangan item = getItem(position);

        viewHolder.kodeRuangan.setText(item.getKode());
        viewHolder.namaRuangan.setText(item.getNama());
        viewHolder.kapasitas.setText(""+item.getKapasitas());
        viewHolder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(parent.getContext());
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk menghapus ruangan ini?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Semua peminjaman berkaitan dengan ruangan ini akan hilang. Tekan Ya untuk menghapus")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ConnectivityManager connMgr = (ConnectivityManager) parent.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                if (networkInfo!=null && networkInfo.isConnected()) {
                                    new DeleteHelper(parent).execute("http://ppl-c07.cs.ui.ac.id/connect/hapusRuangan/"
                                            + viewHolder.kodeRuangan.getText().toString());
                                }
                                else {
                                    Toast.makeText(parent.getContext(), "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // memunculkan alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView kodeRuangan;
        TextView namaRuangan;
        TextView kapasitas;
        Button btnHapus;
    }

    // kelas AsyncTask untuk mengakses URL
    private class DeleteHelper extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        ViewGroup parent;

        public DeleteHelper(ViewGroup parent) {
            this.parent = parent;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(parent.getContext());
            pDialog.setMessage("menghapus ruangan...");
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
        protected void onPostExecute(String data) {
            if (data==null) {
                pDialog.dismiss();
                Toast.makeText(getContext(),"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (data.trim().equals("\"sukses\"")){
                Toast.makeText(parent.getContext(), "Ruangan berhasil dihapus", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(),MainActivityA.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("navPosition",0);
                getContext().startActivity(i);
            }
            else {
                Toast.makeText(parent.getContext(), "Error.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }
}
