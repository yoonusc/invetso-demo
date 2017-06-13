package infinity.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmalD on 8/6/2015.
 */
public class personnel_details extends Activity {
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    JSONObject json;
    String use,emai,phone,k,Key;
    TextView name;
    EditText tname,temail,tphone;

    private static final String _URL = "http://192.168.137.1/investo/profile.php";

   // private static final String _URL = "http://investo12.hostei.com/profile.php";

    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.personel_details);
        name=(TextView)findViewById(R.id.textView10);
        tname=(EditText)findViewById(R.id.editFname);
        temail=(EditText)findViewById(R.id.editLname);
        tphone=(EditText)findViewById(R.id.tp);




        Key=getIntent().getExtras().getString("id");
        System.out.println("keyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + Key);
        new GetProfile().execute(Key);

    }
    class GetProfile extends AsyncTask<String,String,String> {

        public GetProfile() {
            super.onPreExecute();
            pDialog = new ProgressDialog(personnel_details.this);
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

            int s;
            List<NameValuePair> res=new ArrayList<>();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", args[0]));

                Log.d("request!", "start login attempt");
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
                return json.getString("name");




            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pDialog.dismiss();
            if (s != null) {
                try {

                    // System.out.println(json.toString());

                    // full json response
                    Log.d("before json parsing",json.toString());
                    // json success element
                    //s = json.getInt();

                    use=json.getString("name");
                    phone=json.getString("phone");
                    emai=json.getString("email");
                    System.out.println(use + phone + emai);

                    name.setText(use);
                    tname.setText(use);
                    temail.setText(emai);
                    tphone.setText(phone);




                }
                catch (Exception e){}


            }else{
                Toast.makeText(getApplicationContext(), "Error...!!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
