package infinity.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmalD on 8/27/2015.
 */
public class ProfileDetails extends Activity {

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    JSONObject json;
    String use,phone,k,Key;
    TextView we,mob,unm,name;

   //private static final String _URL = "http://investo12.hostei.com/profile.php";
   private static final String _URL = "http://192.168.137.1/investo/profile.php";
    TextView personal,financial;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //reset();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.basic_infos);
        name=(TextView)findViewById(R.id.personal);
        we = (TextView) findViewById(R.id.textViewWelcome);
        unm = (TextView) findViewById(R.id.textViewUserName);
        mob=(TextView)findViewById(R.id.textMobileNo);
        Key=getIntent().getExtras().getString("id");
        System.out.println("keyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + Key);
        new GetProfile().execute(Key);

        personal=(Button)findViewById(R.id.btnp);
        financial=(Button)findViewById(R.id.btnf);

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ProfileDetails.this,personnel_details.class);//Default Activity
                a.putExtra("id",Key);
                startActivity(a);
                finish();
            }
        });
        financial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ProfileDetails.this,finance_details.class);//Default Activity
              //  a.putExtra("id",Key);
                startActivity(a);
                finish();
            }
        });
     }

    void reset() {

        try {
            json.put("name", null);
            json.put("phone", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class GetProfile extends AsyncTask<String,String,String>{

        public GetProfile() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ProfileDetails.this);
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
                    System.out.println(use + phone);

                    we.setText("Welcome  "+use);
                    unm.setText("    " + use);
                    name.setText("Name  :"+ use);
                    mob.setText("Contact : " + phone);
                }
                catch (Exception e){}


            }else{
                Toast.makeText(getApplicationContext(), "Error...!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}