package infinity.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MenuActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private MenuActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemSupport;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemWithdraw;
    private ResideMenuItem itemDeposit;
    private ResideMenuItem itemRoundUps;
    private ResideMenuItem itemHistory;
    private ResideMenuItem itemInvite;
    private ResideMenuItem itemLogout;
    private ResideMenuItem itemAvatar;
    private ResideMenuItem itemweb;
    public String Key;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainui);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )

        try {
            Key = getIntent().getExtras().getString("id");
            changeFragment(new HomeFragment(Key));
            System.out.println("keyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + Key);
        }
        catch (Exception e)
        {
         System.out.println(e.getMessage());
        }

    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
       // resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        //resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");
        itemRoundUps  = new ResideMenuItem(this, R.drawable.icon_roundups,  "Round Ups");
        itemDeposit = new ResideMenuItem(this, R.drawable.icon_deposit, "Deposit");
        itemWithdraw = new ResideMenuItem(this, R.drawable.icon_withdraw, "Withdraw");
        itemHistory = new ResideMenuItem(this, R.drawable.icon_history, "History");
        itemInvite = new ResideMenuItem(this, R.drawable.icon_invite, "Invite");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");
        itemLogout = new ResideMenuItem(this, R.drawable.icon_logout, "Logout");
        itemProfile = new ResideMenuItem(this, R.drawable.icon_profile, "profile");
        itemSupport = new ResideMenuItem(this,R.drawable.icon_support, "Support");
        itemweb = new ResideMenuItem(this,R.drawable.icon_withdraw, "Payment");


        itemHome.setOnClickListener(this);
        itemRoundUps.setOnClickListener(this);
        itemDeposit.setOnClickListener(this);
        itemWithdraw.setOnClickListener(this);
        itemHistory.setOnClickListener(this);
        itemInvite.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemLogout.setOnClickListener(this);
        itemSupport.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemweb.setOnClickListener(this);


        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemRoundUps, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemDeposit, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemWithdraw, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHistory, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemInvite, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);

        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSupport, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemLogout, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemweb, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemDeposit){
            Intent a=new Intent(MenuActivity.this,deposit.class);//Default Activity
            a.putExtra("id",Key);
            startActivity(a);

        }if (view == itemWithdraw){
            Intent a=new Intent(MenuActivity.this,withdrawal.class);//Default Activity
            a.putExtra("id",Key);
            startActivity(a);

        }if (view == itemRoundUps){
            Intent a=new Intent(MenuActivity.this,roundup.class);//Default Activity
            a.putExtra("id",Key);
            startActivity(a);

        }if (view == itemHistory){
            Intent a=new Intent(MenuActivity.this,historydetails.class);//Default Activity
            a.putExtra("id",Key);
            startActivity(a);

        }else if (view == itemHome){
            changeFragment(new HomeFragment(Key));
        }else if (view == itemProfile){

            Intent a=new Intent(MenuActivity.this,ProfileDetails.class);//Default Activity
            a.putExtra("id",Key);
            startActivity(a);

        }else if (view == itemSupport){

            Intent a=new Intent(MenuActivity.this,Support.class);//Default Activity
            startActivity(a);

        }
        else if (view == itemweb){

            Intent a=new Intent(MenuActivity.this,web.class);//Default Activity
            a.putExtra("id",Key);
            startActivity(a);

        }

        else if (view == itemSettings){
            changeFragment(new SettingsFragment());
        }
        else if (view == itemLogout){
/*
            Intent a=new Intent(MenuActivity.this,Main.class);//Default Activity
            startActivity(a);
            finish();
            */

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MenuActivity.this);

            alertDialogBuilder.setTitle("Investo");
            alertDialogBuilder.setMessage("Do you really want to quit");
            // set positive button: Yes message
            alertDialogBuilder.setIcon(R.drawable.investo_logo);
            alertDialogBuilder.setPositiveButton("Quit",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    // go to a new activity of the app
                    MenuActivity.this.finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                }
            });
            // set negative button: No message
            alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    // cancel the alert box and put a Toast to the user

                    dialog.cancel();

                  //  Toast.makeText(getApplicationContext(), "You are still in home",
                          //  Toast.LENGTH_LONG).show();
                }
            });
            // set neutral button: Exit the app message
            //sendMessage("confi");

            AlertDialog alertDialog = alertDialogBuilder.create();
            // show alert
            alertDialog.show();


        }

        resideMenu.closeMenu();
    }

    /*private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };*/

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}
