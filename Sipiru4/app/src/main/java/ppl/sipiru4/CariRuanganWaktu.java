package ppl.sipiru4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import java.util.Calendar;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.User;

public class CariRuanganWaktu extends Fragment {
    Button btnCari;
    Button ambilTglMulai;
    Button ambilTglSelesai;
    Button ambilJamMulai;
    Button ambilJamSelesai;
    static EditText tglMulai;
    static EditText tglSelesai;
    static EditText jamMulai;
    static EditText jamSelesai;
    boolean tglMulaiClicked;
    boolean tglSelesaiClicked;
    boolean jamMulaiClicked;
    boolean jamSelesaiClicked;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cari_ruangan_waktu_ui, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        tglMulai = (EditText)rootView.findViewById(R.id.TglMulai);
        tglSelesai = (EditText)rootView.findViewById(R.id.TglSelesai);
        jamMulai = (EditText)rootView.findViewById(R.id.mulai);
        jamSelesai = (EditText)rootView.findViewById(R.id.selesai);
        ambilTglMulai = (Button)rootView.findViewById(R.id.btnTglMulai);
        ambilTglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new SelectTglMulai();
//                ambilTglMulai.setText("");
                newFragment.show(getFragmentManager(),"");
                tglMulaiClicked=true;
            }
        });

        ambilTglSelesai = (Button)rootView.findViewById(R.id.btnTglSelesai);
        ambilTglSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new SelectTglSelesai();
                newFragment.show(getFragmentManager(),"");
                tglSelesaiClicked=true;
            }
        });

        ambilJamMulai = (Button)rootView.findViewById(R.id.btnJamMulai);
        ambilJamMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new SelectJamMulai();
                newFragment.show(getFragmentManager(),"");
                jamMulaiClicked=true;
            }
        });

        ambilJamSelesai = (Button)rootView.findViewById(R.id.btnJamSelesai);
        ambilJamSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new SelectJamSelesai();
                newFragment.show(getFragmentManager(),"");
                jamSelesaiClicked=true;
            }
        });

        btnCari = (Button) rootView.findViewById(R.id.buttonCari);
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tglMulaiClicked&&tglSelesaiClicked&&jamMulaiClicked&&jamSelesaiClicked) {
                    JSONArray jArray = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/showDaftarRuangan/"
                            +tglMulai.getText()+"%20"+jamMulai.getText()+"&"+tglSelesai.getText()+"%20"+jamSelesai.getText());

                    // simpan ke Shared Preference User
                    new User(getActivity(),tglMulai.getText()+" "+jamMulai.getText(),tglSelesai.getText()+" "+jamSelesai.getText());

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, new DaftarRuangan(jArray));
                    Toast.makeText(getActivity(),"daftar ruangan", Toast.LENGTH_SHORT).show();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else {
                    Toast.makeText(getActivity(),"mohon isi semua tangggal dan jam",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MyTag", "TabFragment0--onDestroyView");
    }
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("MyTag", "TabFragment0--onViewStateRestored");
    }

    public static class SelectTglMulai extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            String yearOutput = ""+year;
            String monthOutput = ""+month;
            String dayOutput = ""+day;
            if (month<10) {monthOutput = "0" + month;}
            if (day<10) {dayOutput = "0" + day;}
            tglMulai.setText(yearOutput + "-" + monthOutput + "-" + dayOutput);
        }
    }

    public static class SelectTglSelesai extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            String yearOutput = ""+year;
            String monthOutput = ""+month;
            String dayOutput = ""+day;
            if (month<10) {monthOutput = "0" + month;}
            if (day<10) {dayOutput = "0" + day;}
            tglSelesai.setText(yearOutput + "-" + monthOutput + "-" + dayOutput);
        }
    }

    public static class SelectJamMulai extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar time = Calendar.getInstance();
            int hour = time.get(Calendar.HOUR);
            int minutes = time.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),this,hour, minutes, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hour, int minutes){
            String hourOutput = ""+hour;
            String minutesOutput = ""+minutes;
            if (hour<10) {hourOutput="0"+hour;}
            if (minutes<10) {minutesOutput="0"+minutes;}
            jamMulai.setText(hourOutput + ":" + minutesOutput + ":00");
        }
    }

    public static class SelectJamSelesai extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar time = Calendar.getInstance();
            int hour = time.get(Calendar.HOUR);
            int minutes = time.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),this,hour, minutes, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hour, int minutes){
            String hourOutput = ""+hour;
            String minutesOutput = ""+minutes;
            if (hour<10) {hourOutput="0"+hour;}
            if (minutes<10) {minutesOutput="0"+minutes;}
            jamSelesai.setText(hourOutput + ":" + minutesOutput + ":00");
        }
    }
}
