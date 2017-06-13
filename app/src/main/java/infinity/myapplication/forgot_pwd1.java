package infinity.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by AmalD on 8/6/2015.
 */
public class forgot_pwd1 extends Activity {


    Button proceed;
    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.forgot_pwd1);

        Button proceed=(Button)findViewById(R.id.submit_otp);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  new userCheck().execute();
              startActivity(new Intent(forgot_pwd1.this, forgot_pwd2.class));
            }
        });

    }

    class userCheck extends AsyncTask<String,String ,String >{

        public userCheck() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }

}
