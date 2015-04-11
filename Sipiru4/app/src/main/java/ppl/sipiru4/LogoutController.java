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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(rootView);
        return rootView;
        /*        LayoutInflater inflate = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.logout_ui, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialoglayout);
        builder.show();*/
    }
}