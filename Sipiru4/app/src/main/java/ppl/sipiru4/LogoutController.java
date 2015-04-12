package ppl.sipiru4;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ppl.sipiru4.R;

public class LogoutController extends Fragment {

    public LogoutController() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.logout_ui, container, false);
        return rootView;
    }
}