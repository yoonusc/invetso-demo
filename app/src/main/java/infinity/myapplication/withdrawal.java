package infinity.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmalD on 10/16/2015.
 */
public class withdrawal extends Activity {


    EditText amount;
    TextView balance;
    Button confirm;
    String key;
    JSONObject json;
    int bal;
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


        setContentView(R.layout.withdraw);

        key = getIntent().getExtras().getString("id");
        amount = (EditText) findViewById(R.id.editAmount);
        balance = (TextView) findViewById(R.id.editTextbalance);
        confirm = (Button) findViewById(R.id.submit);
        new Balance().execute(key);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String b =amount.getText().toString();
                if(b.equals("")){

                    Toast.makeText(getApplicationContext(), "Please Enter a Valid Amount !!!", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println(bal+b);
                    if((bal-Integer.parseInt(b))>0) {
                        new Transaction().execute(key, b);
                    }
                    else
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(withdrawal.this);

                        alertDialogBuilder.setTitle("Investo");
                        alertDialogBuilder.setMessage("Insufficient Balance ");
                        // set positive button: Yes message
                        alertDialogBuilder.setIcon(R.drawable.investo_logo);
                        alertDialogBuilder.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // go to a new activity of the app

                               // startActivity(new Intent(withdrawal.this, MenuActivity.class));
                            amount.setText("");

                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        // show alert
                        alertDialog.show();

                    }
                }

            }
        });
    }

    public class Transaction extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(withdrawal.this);
            pDialog.setMessage("Fetching data.....");
            pDialog.setIndeterminate(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int s;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("user", args[0]));
                params.add(new BasicNameValuePair("amount", args[1]));

                Log.d("request!", "Fetching balance ");
                //Posting user data to script
                json = jsonParser.makeHttpRequest(_URL, "POST", params);



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
                if (json.getString("coment").equals("ok")) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(withdrawal.this);

                    alertDialogBuilder.setTitle("Investo");
                    alertDialogBuilder.setMessage("Your Transaction Done");
                    // set positive button: Yes message
                    alertDialogBuilder.setIcon(R.drawable.investo_logo);
                    alertDialogBuilder.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // go to a new activity of the app

                          //  startActivity(new Intent(withdrawal.this, MenuActivity.class));
                        amount.setText("");
                            new Balance().execute(key);

                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show alert
                    alertDialog.show();


                }
            }
            catch (Exception e){}

        }
    }

    public class Balance extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        public Balance() {
            super.onPreExecute();
            pDialog = new ProgressDialog(withdrawal.this);
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
                bal=Integer.parseInt(json.getString("si"))-Integer.parseInt(json.getString("sw"));
                balance.setText(String.valueOf(bal+Integer.parseInt(json.getString("rnd"))));
            } catch (Exception fr) {
            }

        }
    }

}
