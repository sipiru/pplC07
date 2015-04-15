package ppl.sipiru4;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

import ppl.sipiru4.Entities.ProgressTask;


public class NyobaJSON extends ActionBarActivity {

    TextView tvOutput;
    EditText usrInput;
    EditText pswInput;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Activity activity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nyoba_json);

        JSONArray jsonArray = null;
        usrInput = (EditText) findViewById(R.id.usrInput);
        pswInput = (EditText) findViewById(R.id.pswInput);
        tvOutput = (TextView) findViewById(R.id.tvOutput);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = usrInput.getText().toString();
                String psw = pswInput.getText().toString();
//                ProgressTask pt = new ProgressTask(Constants.SVR_ADDR + usr + '&' + psw,
//                        activity, activity.getApplicationContext());
                tvOutput.setText(usr + psw);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nyoba_json, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
