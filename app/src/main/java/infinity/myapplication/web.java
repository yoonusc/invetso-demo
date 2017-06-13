package infinity.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;



/**
 * Created by investo on 07/01/16.
 */
public class web extends Activity {
    private WebView webView;
     String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webcontent);
         id=getIntent().getExtras().getString("id");
        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);



        webView.loadUrl("http://192.168.137.1./investo/index.php?abc="+id);

    }


}
