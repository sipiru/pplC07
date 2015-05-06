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
public class UpdateRole extends Fragment {

    EditText role;
    EditText id;
    String fillRole;
    String fillID;

    Button update;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View rootView = inflater.inflate(R.layout.update_akun_admin, container, false);
        role = (EditText)rootView.findViewById(R.id.role);
        id = (EditText)rootView.findViewById(R.id.id);
        update = (Button)rootView.findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                fillRole = role.getText().toString();
                fillID = id.getText().toString();
                String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/updateRole/"+ fillRole + "&" + fillID + "/");
                Toast.makeText(getActivity(), "Role baru berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
