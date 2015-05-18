package ppl.sipiru4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import ppl.sipiru4.Entity.JSONParser;

public class CreateRole extends Fragment {
    ArrayList<String> roleString;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstancesState){
        View rootView = inflater.inflate(R.layout.create_role, container, false);
        final EditText username = (EditText) rootView.findViewById(R.id.username);
        final EditText nama = (EditText) rootView.findViewById(R.id.namaPengguna);
        final Spinner role = (Spinner) rootView.findViewById(R.id.role);
        final EditText noHP = (EditText) rootView.findViewById(R.id.nohp);
        roleString = new ArrayList<>();
        roleString.add("manajer ruangan");
        roleString.add("manajer kemahasiswaa");
        roleString.add("manajer umum");
        roleString.add("manajer itf");
        roleString.add("admin");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, roleString) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView)v).setTextSize(22);
                ((TextView)v).setTextColor(getResources().getColorStateList(R.color.abc_primary_text_disable_only_material_light));

                return v;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView)v).setTextSize(22);

                return v;
            }
        };
        role.setAdapter(adapter);

        Button update = (Button) rootView.findViewById(R.id.buttonAdd);
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String roleValues = roleString.get(role.getSelectedItemPosition())+ "";
                String uname = username.getText().toString();
                String name = nama.getText().toString();
                String no_hp = noHP.getText().toString();
                String link;
                if(uname.trim().length()==0) {
                    Toast.makeText(getActivity(), "mohon isi username", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (roleValues.equals("admin")) {
                        link = "http://ppl-c07.cs.ui.ac.id/connect/createAdmin/" + uname+"&"+name.replaceAll(" ","%20")
                                +"&"+roleValues.replaceAll(" ","%20") ;
                        new SubmitHelper().execute(link);
                    }
                    else {
                        if (no_hp.trim().length()==0) {
                            Toast.makeText(getActivity(), "mohon isi no hp untuk manager", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            link = "http://ppl-c07.cs.ui.ac.id/connect/createManager/"+uname+"&"+name.replaceAll(" ","%20")+"&"
                                    +roleValues.replaceAll(" ","%20");
                            new SubmitHelper().execute(link);
                        }
                    }
                }
            }
        });
        return rootView;
    }

    // kelas AsyncTask untuk mengakses URL
    private class SubmitHelper extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Menambah role ke database...");
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
                Toast.makeText(getActivity(),"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (data.trim().equals("\"sukses\"")){
                Toast.makeText(getActivity(), "Role berhasil ditambah", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),MainActivityA.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("navPosition",2);
                startActivity(i);
            }
            else {
                Toast.makeText(getActivity(), "Error.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }
}
