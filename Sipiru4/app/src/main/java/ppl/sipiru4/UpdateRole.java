package ppl.sipiru4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;

import ppl.sipiru4.Entity.JSONParser;

public class UpdateRole extends Fragment {

    private Button update;
    private Spinner spinnerRole;
    private String role;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstancesState){
        final String[] roles = getResources().getStringArray(R.array.role);
        View rootView = inflater.inflate(R.layout.update_akun_admin, container, false);
        update = (Button) rootView.findViewById(R.id.btnUpdate);
        spinnerRole = (Spinner) rootView.findViewById(R.id.editText3);
        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = roles[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String msg = "http://ppl-c07.cs.ui.ac.id/connect/updateRole/"
                        + savedInstancesState.getString("user") + '&' + role;
                Log.e("Update Role msg", msg);
                JSONArray info = null;
                try {
                    info = JSONParser.getJSONfromURL(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {if (info != null && info.getString(0).equals(""))
                        Toast.makeText(getActivity(), "Role baru berhasil disimpan", Toast.LENGTH_SHORT).show();
                }catch(Exception _) {}
            }
        });
        return rootView;
    }
}
