package infinity.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * Created by AmalD on 8/27/2015.
 */
public class finance_details extends Activity implements View.OnClickListener {

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    Button btnfina,btnlink,btnfund;
    JSONObject json;
    String use,phone,k;
    TextView we,mob,unm;

    private static final String _URL = "http://192.168.137.1/investo/profile.php";
    TextView personal,financial;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //reset();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.financial_details);
        btnfina=(Button)findViewById(R.id.buttonf);
        btnlink=(Button)findViewById(R.id.buttonl);
        btnfund=(Button)findViewById(R.id.buttonfund);
        btnfina.setOnClickListener(this);
        btnlink.setOnClickListener(this);
        btnfund.setOnClickListener(this);
     }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonf:
                Intent a=new Intent(finance_details.this,financial.class);//Default Activity
                //a.putExtra("id");
                startActivity(a);
                break;
            case R.id.buttonl:
                Intent da=new Intent(finance_details.this,financial.class);//Default Activity
               // da.putExtra("id");
                startActivity(da);
                break;
            case R.id.buttonfund:

                break;
        }

    }
}