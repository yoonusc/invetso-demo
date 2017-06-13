package infinity.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmalD on 10/16/2015.
 */
public class roundup extends Activity  {

    private ProgressDialog pDialog;
    JSONObject json;
    ScrollView sv;
    RelativeLayout rela;
    LinearLayout lin;
    ListView list;
    int imgid;
    Activity c;
    private static final String _URL = "http://192.168.137.1/investo/raonds.php";

    JSONParser jsonParser = new JSONParser();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public static String key;

    String[] itemname=null;
    String[] descri=null;
    String[] date=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        key=getIntent().getExtras().getString("id");
        setContentView(R.layout.roundup);
        c=this;
        new Balance().execute(key);
    }
    void get(Activity a,String[] item,String[] descri,String[] date,int id)
    {

        CustomListAdapter adapter=new CustomListAdapter(a,item,descri,date,id);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        imgid=R.drawable.icon_deposit;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }


public class Balance extends AsyncTask<String, String, String> {
    /**
     * Before starting background thread Show Progress Dialog
     */
    public Balance() {
        super.onPreExecute();
        pDialog = new ProgressDialog(roundup.this);
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

            Log.d("request!", "Fetching Data");
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
            int i=1,j=0;
       int rng=Integer.parseInt(json.getJSONObject("0").getString("range"));
            itemname=new String[rng-1];
            descri=new String[rng-1];
            date=new String[rng-1];
        while(i<=rng-1)
            {

                String f =json.getJSONObject(String.valueOf(i)).getString("amount").toString();
                String d =json.getJSONObject(String.valueOf(i)).getString("descri").toString();
                String e=json.getJSONObject(String.valueOf(i)).getString("date").toString();
                itemname[j]=f;
                descri[j]=d;
                date[j]=e;
                System.out.println("resgfghfhgfghjhghjhhg" +f+d+e+i);
                i++;
                j++;

            }

  get(c,itemname,descri,date,imgid);

        } catch (Exception fr) { System.out.println("resgfghfhgfghjhghjhhg"+fr.getMessage());
            //i++;

        }

    }
}




}