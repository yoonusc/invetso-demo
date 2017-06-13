package infinity.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by    INVESTO on 8/25/2015.
            */
    public class signup extends Activity{


        EditText name,email,password,c_password,mob;
        Button signup;
        String key;
        // JSON parser class
        JSONParser jsonParser = new JSONParser();

        Context context;

        // Progress Dialog
    private ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://192.168.137.1/investo/register.php";

    //private static final String LOGIN_URL = "http://investo12.hostei.com/register.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.registration);

        name= (EditText)findViewById(R.id.editFullName);
        email= (EditText)findViewById(R.id.editMailAddress);
        password= (EditText)findViewById(R.id.editPasswordS);
        c_password= (EditText)findViewById(R.id.editPasswordC);
        mob= (EditText)findViewById(R.id.editMobileNo);

        signup= (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=name.getText().toString();
                String uemail=email.getText().toString();
                String upass= password.getText().toString();
                String c_upass=c_password.getText().toString();
                String umob=mob.getText().toString();


                if (upass.equals(c_upass)) {
                    if(upass.length()>6) {
                        if(umob.length()!=10){
                           Toast.makeText(getApplicationContext(), "Please Enter a Valid mobile number !!!", Toast.LENGTH_LONG).show();
                        }else {
                            new CreateUser().execute(uname, uemail, upass, umob);
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Password too small  !!!", Toast.LENGTH_LONG).show();
                    }
                } else
                {
                    Toast.makeText(getApplicationContext(),"Password Not Matching  !!!",Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    public class CreateUser extends AsyncTask<String,String,String>{
        /**
         * Before starting background thread Show Progress Dialog
         * */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(signup.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int s;
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("uname", args[0]));
                params.add(new BasicNameValuePair("uemail",args[1]));
                params.add(new BasicNameValuePair("upass",args[2]));
                params.add(new BasicNameValuePair("umob",args[3]));
                Log.d("request!", "starting");


                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);
                System.out.println(json.toString());

                // full json response
                Log.d("Login attempt", json.toString());

                // json success element
                s = json.getInt(TAG_SUCCESS);
                if (s == 1) {
                    Log.d("User Created!", json.toString());
                    key=json.getString("email");
                    finish();
                    return json.getString(TAG_SUCCESS);
                }else{
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }

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
            if (result != null){
                Toast.makeText(getApplicationContext(), "Rgisterd INVESTO", Toast.LENGTH_SHORT).show();
                Intent a=new Intent(getApplicationContext(),verification_otp.class);//Default Activity
                a.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                a.putExtra("id",key);
                startActivity(a);

            }
        }
    }
}
