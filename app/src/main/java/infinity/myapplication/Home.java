package infinity.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by AmalD on 8/6/2015.
 */
public class Home extends Activity {

  String key;
    TextView signup,forgot,investo;

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override

        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences settings=getSharedPreferences("prefs",0);
        boolean firstRun=settings.getBoolean("firstRun",false);
        if(firstRun==false)//if running for first time
        {
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("firstRun",true);
            editor.commit();


            Intent i=new Intent(Home.this,MainActivity.class);//Activity to be     launched For the First time
            startActivity(i);
            finish();
        }
        else
        {
            Intent a=new Intent(Home.this,Main.class);//Default Activity
            startActivity(a);
            finish();
        }


        

    }
}
