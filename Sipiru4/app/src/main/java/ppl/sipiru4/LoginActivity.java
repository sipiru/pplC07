package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;

public class LoginActivity extends Activity {



    private EditText username;

    private EditText password;

    private Button login;


    int numberOfRemainingLoginAttempts = 3;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_ui);


        setupVariables();

    }



    public void authenticateLogin(View view) {
        //login as admin
        //TODO : periksa apakah username dan password terdaftar sebagai salah satu role
        if ((username.getText().toString().equals("p") &&
                password.getText().toString().equals("p"))
){
            Toast.makeText(getApplicationContext(), "Hello peminjam!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityP.class);
            // passing array index
            i.putExtra("id", "peminjam");
            startActivity(i);
        }
        else if(username.getText().toString().equals("mr") &&
                password.getText().toString().equals("mr")){
            Toast.makeText(getApplicationContext(), "Hello manager ruangan!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityMR.class);
            // passing array index
            i.putExtra("id", "manager ruangan");
            startActivity(i);
        }
        else if(username.getText().toString().equals("mk") &&
                password.getText().toString().equals("mk")){
            Toast.makeText(getApplicationContext(), "Hello manager kemahasiswaan!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityK.class);
            // passing array index
            i.putExtra("id", "manager kemahasiswaan");
            startActivity(i);
        }
        else if(username.getText().toString().equals("itf") &&
                password.getText().toString().equals("itf")){
            Toast.makeText(getApplicationContext(), "Hello fasum itf!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityP.class);
            // passing array index
            i.putExtra("id", "fasum itf");
            startActivity(i);
        }
        else if(username.getText().toString().equals("adm") &&
                password.getText().toString().equals("adm")){
            Toast.makeText(getApplicationContext(), "Hello admin!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivityP.class);
            // passing array index
            i.putExtra("id", "admin");
            startActivity(i);
        }else {
            Toast.makeText(getApplicationContext(), "Seems like you 're not admin!",
                    Toast.LENGTH_SHORT).show();

            numberOfRemainingLoginAttempts--;
            if (numberOfRemainingLoginAttempts == 0) {
                login.setEnabled(false);
            }
        }

    }



    private void setupVariables() {

        username = (EditText) findViewById(R.id.editText1);

        password = (EditText) findViewById(R.id.editIsiPesan);

        login = (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateLogin(v);
            }
        });


    }



}