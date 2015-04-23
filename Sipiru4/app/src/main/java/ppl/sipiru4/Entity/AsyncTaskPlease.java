package ppl.sipiru4.Entity;

import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * Created by ACER on 21/04/2015.
 */
public class AsyncTaskPlease extends AsyncTask<String, Integer, Double> {
    final static String URL = "http://ppl-c07.cs.ui.ac.id/connect/yogie.clinov&yogie123/";

    @Override
    protected Double doInBackground(String... params) {
        PostData(params[0]);
        return null;
    }

    private String PostData(String params) {
        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost("http://somewebsite.com/receiver.php");

        try {
            // Add your data
//            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//            nameValuePairs.add(new BasicNameValuePair("myHttpData", valueIWantToSend));
//            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            StringBuilder url = new StringBuilder();
            HttpGet httpGet = new HttpGet(url.toString());
            HttpResponse response = httpclient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status==200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                return data;
            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        return null;
    }

    protected void onPostExecute(Double result){
//        Toast.makeText(AsyncTaskPlease.this, "command sent", Toast.LENGTH_LONG).show();
    }

}
