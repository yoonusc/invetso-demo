package infinity.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmalD on 10/15/2015.
 */
public class historydetails extends FragmentActivity {


    TextView balance,inv,with,rnd;
    String key;
    JSONObject json;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    Context context;

    // Progress Dialog
    private ProgressDialog pDialog;

    private static final String _URL = "http://192.168.137.1/investo/withd.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.history);

        key = getIntent().getExtras().getString("id");

        balance = (TextView) findViewById(R.id.textbal);
        inv = (TextView) findViewById(R.id.textinv);
        with= (TextView) findViewById(R.id.textwith);
        balance = (TextView) findViewById(R.id.textbal);
        rnd= (TextView) findViewById(R.id.rnd);

        new Balance().execute(key);

    }


    public class Balance extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        public Balance() {
            super.onPreExecute();
            pDialog = new ProgressDialog(historydetails.this);
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setInverseBackgroundForced(true);
            pDialog.setMessage("Fetching Data....");
            pDialog.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int s;
            List<NameValuePair> res = new ArrayList<>();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("user", args[0]));

                Log.d("request!", "Fetching balance ");
                //Posting user data to script
                json = jsonParser.makeHttpRequest(_URL, "POST", params);

              /*  if(st==-1){
                    Log.d("Empty feild", json.toString());

                }else if (s == 1) {
                    Log.d("Login successfull", json.toString());
                    finish();
                    return json.getString("age");
                }else{*/
                //Log.d("Login Failure!", json.getString("age"));
                return "abc";


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         ***/

        protected void onPostExecute(String result) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            try {
                int bal=Integer.parseInt(json.getString("si"))-Integer.parseInt(json.getString("sw"));
                balance.setText(String.valueOf(bal+Integer.parseInt(json.getString("rnd"))));
                rnd.setText(json.getString("rnd"));
                inv.setText(json.getString("si"));
                with.setText(json.getString("sw"));
            } catch (Exception fr) {
            }

        }
    }

}
