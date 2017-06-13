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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmalD on 8/24/2015.
 */
public class Main extends Activity {
    JSONParser jsonParser = new JSONParser();
    String status;
    Context context;

    JSONObject json;
    private SharedPreference sharedPreference;

    Activity contexts = this;


    // Progress Dialog
    private ProgressDialog pDialog;

  private static final String LOGIN_URL = "http://192.168.137.1/investo/login.php";
    //private static final String LOGIN_URL = "http://investo12.hostei.com/login.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

     EditText username,password;
    TextView forgot_pwd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.main);

         username=(EditText)findViewById(R.id.username);
         password=(EditText)findViewById(R.id.password);
        forgot_pwd=(TextView)findViewById(R.id.forgot_password);

        Button signup = (Button) findViewById(R.id.signups);
        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, forgot_pwd1.class));

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main.this, signup.class));
            }
        });

        Button signin = (Button) findViewById(R.id.singin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  startActivity(new Intent(Main.this,MenuActivity.class)
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.length() == 0 || pass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //specified constraints.
                //email specification
                //password constraints
                new Login().execute(user, pass);
            }
        });

    }

    void reset() {

        try {
            json.put(TAG_MESSAGE,null);
            json.put(TAG_SUCCESS,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    class Login extends AsyncTask<String,String,String>{

        public Login() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Main.this);
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setInverseBackgroundForced(true);
            pDialog.setMessage("Logging....");
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
                params.add(new BasicNameValuePair("password",args[1]));
                Log.d("request!", "start login attempt");
                System.out.println(args[0]+args[1]);
                //Posting user data to script
                json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);
               // System.out.println(json.toString());

                // full json response
                Log.d("before json parsing", json.toString());

                // json success element
                s = json.getInt(TAG_SUCCESS);
                String st=json.getString("success");
                if(s==-1){
                    Log.d("Empty feild", json.toString());
                    Toast.makeText(getApplicationContext(), "Invalid credential", Toast.LENGTH_SHORT).show();
                }else if (s == 1) {
                    Log.d("Login successfull", json.toString());
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

        @Override
        protected void onPostExecute(String s) {
            pDialog.dismiss();
            try {
                String st=json.getString("success");
                if(s=="-1"){
                    Log.d("Empty feild", json.toString());

                }else if (s == "1") {
                    Log.d("Login successfull", json.toString());
                    finish();
                   // return json.getString(TAG_SUCCESS);
                }else{
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                   // return json.getString(TAG_MESSAGE);

                }
                if (s != null) {
                    reset();

                    /*   sharedpreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

 editor.putString(Name,json.getString("user"));
    editor.commit();
    String value = sharedpreferences.getString(Name,"");
    System.out.println("kfkljjjjjjjjjjjjjfjkdfngseteeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    Keyreader k=new Keyreader();
    k.write("id", json.getString("user"));
    String value=k.read("id");
    System.out.println("kfkljjjjjjjjjjjjjfjkdfngseteeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    System.out.println(value);
    Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();*/


                        Toast.makeText(getApplicationContext(), "Last Login : xx/xx/xxxx IP :xxx.xxx.xxx.xxx", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", json.getString("user"));
                        getApplicationContext().startActivity(intent);
                }else{
                        Toast.makeText(getApplicationContext(), "Network error ...!!", Toast.LENGTH_SHORT).show();
                    }
                }
            catch (Exception e){

                System.out.println("msg"+e.getMessage());

            }
        }
    }
}
