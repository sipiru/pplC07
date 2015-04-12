package ppl.sipiru4;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ppl.sipiru4.R;

public class Logout extends Fragment {

    public Logout() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.logout_ui, container, false);
        Button btnYes = (Button) rootView.findViewById(R.id.buttonYes);
        btnYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                goToAttract(v);
            }
        });
        Button btnNo = (Button) rootView.findViewById(R.id.buttonNo);
        btnNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //nothing to do
            }
        });
        return rootView;
    }
    public void goToAttract(View v)
    {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}