package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import ppl.sipiru4.Entity.JSONHelper;

public class LoginActivity extends Activity {
    private EditText username;
    private EditText password;
    private Button login;
    private static final String user = "user"; //konstanta untuk menyimpan username menggunakan SharedPreferences
    SharedPreferences settings;
    int numberOfRemainingLoginAttempts = 5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);
        setupVariables();
        settings = getSharedPreferences("user", MODE_PRIVATE);
    }

    private void setupVariables() {
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.ruang);
        login = (Button) findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    authenticateLogin(v);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void authenticateLogin(View view) throws IOException, JSONException {
        //TODO : periksa apakah username dan password terdaftar sebagai salah satu role
        String uname = username.getText()+"";
        String pass = password.getText()+"";

        if ((username.getText().toString().equals("p") &&
                password.getText().toString().equals("p"))
        ){
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(user,""+uname);
            editor.commit();

            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityP.class);
            // passing array index
            i.putExtra("id", "peminjam");
            startActivity(i);
        }
        else if(username.getText().toString().equals("mr") &&
                password.getText().toString().equals("mr")){
            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityMR.class);
            // passing array index
            i.putExtra("id", "manager ruangan");
            startActivity(i);
        }
        else if(username.getText().toString().equals("mk") &&
                password.getText().toString().equals("mk")){
            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityK.class);
            // passing array index
            i.putExtra("id", "manager kemahasiswaan");
            startActivity(i);
        }
        else if(username.getText().toString().equals("itf") &&
                password.getText().toString().equals("itf")){
            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityFI.class);
            // passing array index
            i.putExtra("id", "fasum itf");
            startActivity(i);
        }
        else if(username.getText().toString().equals("adm") &&
                password.getText().toString().equals("adm")){
            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityP.class);
            // passing array index
            i.putExtra("id", "admin");
            startActivity(i);
        }else {
            Toast.makeText(getApplicationContext(), "akun tidak terdaftar", Toast.LENGTH_SHORT).show();

            numberOfRemainingLoginAttempts--;
            if (numberOfRemainingLoginAttempts == 0) {
                login.setEnabled(false);
            }
        }

    }


}