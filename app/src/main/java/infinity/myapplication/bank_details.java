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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by investo on 15/10/15.
 */
public class bank_details extends Activity {

    private  ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String key;
    public String[] banks = {"Bank Name","State Bank of India", "Andhra Bank", "Bank of Baroda", "Bank of India", "Bank of Maharashtra", "Central Bank of India", "Corporation Bank", "Indian Bank", "Syndicate Bank"};

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private static final String _URL = "http://192.168.137.1/investo/account_reg.php";
   // private static final String _URL = "http://investo12.hostei.com/account_reg.php";


    Button save_account;
    EditText ac_no,customer_id,password,c_password;
    Spinner bank_name;


    String _ac_no,_customer_id,_password,_c_password,_bank_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.bank_details);
try {
    key = getIntent().getExtras().getString("id");
    System.out.println("keyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + key);
}
 catch(Exception e){}

        bank_name = (Spinner) findViewById(R.id.bank);
        ac_no=(EditText)findViewById(R.id.account_number);

        save_account=(Button)findViewById(R.id.save_account);

        ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,banks);
        bank_name.setAdapter(adapter2);

        save_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _ac_no=ac_no.getText().toString();

                _bank_name=bank_name.getSelectedItem().toString();
                if (_ac_no.equals("")|| _bank_name.equals("") ){
                    Toast.makeText(getApplicationContext(), "Empty Fields ...!!", Toast.LENGTH_SHORT).show();
                } else {
                    new Account().execute();
                 }

            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       bank_name.setSelection(position);
         _bank_name= (String) bank_name.getSelectedItem();
        System.out.println(" bank name : "+_bank_name);

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }



    class Account extends AsyncTask<String,String ,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(bank_details.this);
            pDialog.setMessage("Saving Data...");
            pDialog.setIndeterminate(true);
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
                params.add(new BasicNameValuePair("email",key));
                params.add(new BasicNameValuePair("bank_name",_bank_name));
                params.add(new BasicNameValuePair("ac_no",_ac_no));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(_URL, "POST", params);
                System.out.println(json.toString());

                // full json response
                Log.d("Updating database...", json.toString());
                // json success element
                s = json.getInt(TAG_SUCCESS);
                if (s == 1) {
                    Log.d("Update success!", json.toString());
                    finish();
                    return json.getString(TAG_SUCCESS);
                }else{
                    Log.d("Update Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            pDialog.show();

        }

        /**
         * After completing background task Dismiss the progress dialog
         ***/


        @Override
        protected void onPostExecute(String result) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (result != null){

               Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("id", key);
                startActivity(intent);


            }
        }
    }
}
