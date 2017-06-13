package infinity.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by AmalD on 8/27/2015.
 */
public class Support extends Activity {


    TextView signup, forgot, investo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.support);


        final ImageView mailme = (ImageView) findViewById(R.id.imageButton9);
        mailme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

               // mailme.startAnimation(animFadein);

                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + getResources().getString(R.string.mail_id)));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Help");
                startActivity(intent);


            }
        });


        ImageView callus = (ImageView) findViewById(R.id.imageButton10);
        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9446089816"));
                startActivity(intent);

            }
        });


        ImageView faq = (ImageView) findViewById(R.id.imageButton12);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://www.investo.com/faq";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        ImageView vistus = (ImageView) findViewById(R.id.imageButton13);
        vistus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://www.investo.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}

