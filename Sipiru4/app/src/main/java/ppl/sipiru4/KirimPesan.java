package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.controller.ManajerController;

public class KirimPesan extends Activity{
    Button buttonSend;
    Button buttonSave;
    Spinner spinner;
    TextView role;
    String[] noHp;
    String[] namaManajer;
    EditText textSMS;
    Context context;
    int posisi;
    ArrayAdapter<String> adapter;
    ManajerController manajerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar()!=null) {
            getActionBar().setTitle("Kirim Pesan");
        }
        setContentView(R.layout.kirim_pesan_ui);
        context = this;

        // mengakses URL menggunakan AsyncTask class
        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/showAllManager/");

        textSMS = (EditText) findViewById(R.id.editTextSMS);
        role = (TextView) findViewById(R.id.jabatan);
        spinner = (Spinner) findViewById(R.id.spinnerPhoneNo);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posisi = spinner.getSelectedItemPosition();
                role.setText("Role : " + manajerController.getManajer(posisi).getRole());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttonSave = (Button) findViewById(R.id.buttonSimpan);
        buttonSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namaManajer[posisi];
                String phoneNo = noHp[posisi];

                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                intent.putExtra(ContactsContract.Intents.Insert.NAME, nama);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNo);
                startActivityForResult(intent, 1);
            }
        });

        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNo = noHp[posisi];
                final String sms = textSMS.getText().toString();
                if (sms.trim().length()==0) {
                    Toast.makeText(context, "Isi pesan harus ditulis", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    // set title
                    alertDialogBuilder.setTitle("Apakah anda yakin untuk mengirim pesan tersebut?");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk mengirim")
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    try {
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                                        Toast.makeText(context, "SMS terkirim", Toast.LENGTH_LONG).show();
                                        textSMS.setText("");
                                    } catch (Exception e) {
                                        Toast.makeText(context, "SMS gagal terkirim. coba lagi nanti",
                                                Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

                    // memunculkan alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Mendapatkan semua kontak manajer...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... args) {
            try {
                return JSONParser.getJSONfromURL(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONArray hasil) {
            if (hasil == null) {
                pDialog.dismiss();
                Toast.makeText(context,"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }

            manajerController = new ManajerController(hasil);
            int size = manajerController.getSize();

            noHp = new String[size];
            namaManajer = new String[size];
            // memasukkan nama ruangan ke ArrayList Ruangan
            for (int i = 0; i < size; i++) {
                noHp[i] = manajerController.getManajer(i).getNo_hp();
                namaManajer[i] = manajerController.getManajer(i).getNama();
            }

            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, namaManajer){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);
                    ((TextView)v).setTextSize(24);

                    return v;
                }

                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);
                    ((TextView)v).setTextSize(24);

                    return v;
                }
            };
            spinner.setAdapter(adapter);
            pDialog.dismiss();
        }
    }
}