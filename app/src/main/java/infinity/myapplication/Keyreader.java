/**
 * Created by ${INVESTO} on 10/16/2015.
 */
package infinity.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Keyreader extends Activity
{
    SharedPreferences sharedpreferences;
    public static final String PREFS_NAME = "MyApp_Settings";
    public static final String Name ="id";

  String  read(String key)
  {
      sharedpreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
     /* sharedpreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedpreferences.edit();*/
      String value = sharedpreferences.getString(key,"85");
      return value;
  }
    boolean write(String key,String data)
    {
        sharedpreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.putString(key,data);
      /*sharedpreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, data);*/
        editor.commit();
        return true;

    }
    boolean de(String key)
    {
        sharedpreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedpreferences.edit().remove("text").commit();
        return true;
    }

}
