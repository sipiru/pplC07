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
        if (username.getText().toString().equals("admin") &&

                password.getText().toString().equals("admin")) {

            Toast.makeText(getApplicationContext(), "Hello admin!",

                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivityAdmin.class));

        }
        //login as peminjam
        else if (username.getText().toString().equals("peminjam") &&

                password.getText().toString().equals("peminjam")) {

            Toast.makeText(getApplicationContext(), "Hello Peminjam!",

                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivityPeminjam.class));

        }
        else if (username.getText().toString().equals("Manager Ruangan") &&

                password.getText().toString().equals("Ruangan Ruangan")) {

            Toast.makeText(getApplicationContext(), "Hello Manager Ruangan!",

                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivityPeminjam.class));

        }
        else if (username.getText().toString().equals("fasumitf") &&

                password.getText().toString().equals("fasumitf")) {

            Toast.makeText(getApplicationContext(), "Hello FasumITF!",

                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivityPeminjam.class));

        }

        else {

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

        password = (EditText) findViewById(R.id.editText3);

        login = (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateLogin(v);
            }
        });


    }



}