package infinity.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${INVESTO} on 10/16/2015.
 */
public class Test extends Activity {

    JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://192.168.137.1/investo/login.php";
    JSONObject json;
    TextView tes;
    Login lo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tes=(TextView)findViewById(R.id.textView11);
        lo.execute("f","f","f");
    }

class Login extends AsyncTask<String,String,String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... args) {

        int s;
        List<NameValuePair> res=new ArrayList<>();
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", args[0]));
            params.add(new BasicNameValuePair("password", args[1]));
            Log.d("request!", "start login attempt");
            System.out.println(args[0]+args[1]);
            //Posting user data to script
            json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);
            System.out.println(json.toString());

            // full json response
            Log.d("before json parsing", json.toString());

            // json success element




        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
      tes.setText(json.toString());
    }
}
}

