package infinity.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class HomeFragment extends Fragment {
public  HomeFragment(String key)
{

    this.key=key;
}
    private View parentView;
    private ResideMenu resideMenu;
    TextView bala,jm,rnd,lst;
    String key;
    JSONObject json;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    Context context;

    // Progress Dialog
    private ProgressDialog pDialog;

    private static final String _URL = "http://192.168.137.1/investo/withd.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.home, container, false);
        setUpViews();
        return parentView;





    }


    @Override
    public void onResume() {
        super.onResume();
        new Balance().execute(key);

    }

    private void setUpViews() {
        MenuActivity parentActivity = (MenuActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        /*parentView.findViewById(R.id.btn_open_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });*/

        // add gesture operation's ignored views
       // key=new MenuActivity().Key;
        bala=(TextView)parentView.findViewById(R.id.tbal);
        jm=(TextView)parentView.findViewById(R.id.tinv);
        rnd=(TextView)parentView.findViewById(R.id.trnd);
        lst=(TextView)parentView.findViewById(R.id.tlast);


        FrameLayout ignored_view = (FrameLayout) parentView.findViewById(R.id.ignored_view);
        resideMenu.addIgnoredView(ignored_view);
        new Balance().execute(key);
    }

    public class Balance extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        public Balance() {
            super.onPreExecute();
          //  Toast.makeText(context.getApplicationContext(), "Fetching ..", Toast.LENGTH_SHORT).show();
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

            try {
                int bal=Integer.parseInt(json.getString("si"))-Integer.parseInt(json.getString("sw"));

                bala.setText(String.valueOf(bal+Integer.parseInt(json.getString("rnd"))));
                jm.setText(String.valueOf(bal));
                 rnd.setText(json.getString("rnd"));
            } catch (Exception fr) {
            }

        }
    }


}
