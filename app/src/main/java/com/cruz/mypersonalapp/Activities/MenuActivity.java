package com.cruz.mypersonalapp.Activities;

/**
 * Created by FERNANDO on 12/10/2017.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cruz.mypersonalapp.Model.User;
import com.cruz.mypersonalapp.R;
import com.cruz.mypersonalapp.Repository.UserRepository;
import com.vstechlab.easyfonts.EasyFonts;


public class MenuActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private static final String TAG = MenuActivity.class.getSimpleName();
    // SharedPreferences
    private SharedPreferences sharedPreferences;


    private TextView usernameText;
    private TextView passwordText;
    private TextView tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        usernameText = (TextView)findViewById(R.id.fullname_text);
        passwordText=(TextView)findViewById(R.id.pass_text);
        tip=(TextView)findViewById(R.id.tip);


        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("password", null);

        Log.d(TAG, "username: " + username);

        User user = UserRepository.getUser(username);

        usernameText.setText(sharedPreferences.getString("username", null));
        passwordText.setText(sharedPreferences.getString("password", null));


        //Cambiando estilo de letra demo
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to goStyle
            goStyle();
    }else {
            startActivity(new Intent(this, MainActivity.class));

        }

/*

*/
        //fin


        // Setear Toolbar como action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set drawer toggle icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, android.R.string.ok, android.R.string.cancel);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Set NavigationItemSelectedListener
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Do action by menu item id
                switch (menuItem.getItemId()){

                    case R.id.nav_user:
                        Toast.makeText(MenuActivity.this, "Su profile", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MenuActivity.this, MenuActivity.class));
                        break;

                    case R.id.nav_logout:
                        Toast.makeText(MenuActivity.this, "Logout...", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(MenuActivity.this, MainActivity.class));

                        // remove from SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        boolean success = editor.putBoolean("islogged", false).commit();
                    //        boolean success = editor.clear().commit(); // not recommended
                        startActivity(new Intent(MenuActivity.this,MainActivity.class));
                    finish();
                        break;

                    case R.id.nav_settings:

                        Toast.makeText(MenuActivity.this, "Configuracion...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MenuActivity.this,MyPreferencesActivity.class));
                        break;

                }
                // Close drawer
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Change navigation header information
        ImageView photoImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.menu_photo);
        //photoImage.setBackgroundResource(R.drawable.bg_profile);

        TextView fullnameText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.menu_fullname);
        fullnameText.setText(username);

    }
public  void goStyle(){

    String est = sharedPreferences.getString("cambioEstilo", null);
    if(est !=null){
    if(est.equalsIgnoreCase("1")){
        usernameText.setTypeface(EasyFonts.droidSerifBoldItalic(this));
        passwordText.setTypeface(EasyFonts.droidSerifBoldItalic(this));
        tip.setTypeface(EasyFonts.droidSerifBoldItalic(this));
    }else if (est.equalsIgnoreCase("2")) {
        usernameText.setTypeface(EasyFonts.caviarDreams(this));
        passwordText.setTypeface(EasyFonts.caviarDreams(this));
        tip.setTypeface(EasyFonts.caviarDreams(this));
    }else if(est.equalsIgnoreCase("3")){
        usernameText.setTypeface(EasyFonts.robotoBlackItalic(this));
        passwordText.setTypeface(EasyFonts.robotoBlackItalic(this));
        tip.setTypeface(EasyFonts.robotoBlackItalic(this));
    }
    }else{
        Toast.makeText(MenuActivity.this, "Realice sus configuraciones a gusto", Toast.LENGTH_SHORT).show();
    }
}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // Option open drawer
                if(!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);   // Open drawer
                else
                    drawerLayout.closeDrawer(GravityCompat.START);    // Close drawer
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
