package infinity.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by AmalD on 8/6/2015.
 */
public class verification_otp extends Activity {


    String key;
    Button proceed;
    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.verification_otp);

        key=getIntent().getExtras().getString("id");
        System.out.println("keyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + key);

        Button proceed=(Button)findViewById(R.id.submit_otp);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "VALIDATED-INVESTO", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(verification_otp.this, bank_details.class));
                Intent a=new Intent(getApplicationContext(),bank_details.class);//Default Activity
                //a.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                a.putExtra("id",key);
                startActivity(a);
            }
        });

    }


}
