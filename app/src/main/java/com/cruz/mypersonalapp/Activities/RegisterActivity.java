package com.cruz.mypersonalapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cruz.mypersonalapp.Model.User;
import com.cruz.mypersonalapp.R;
import com.cruz.mypersonalapp.Repository.UserRepository;

/**
 * Created by FERNANDO on 9/10/2017.
 */

public class RegisterActivity extends AppCompatActivity{
    private EditText user;
    private EditText pass;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user=(EditText)findViewById(R.id.us_usr) ;
        pass=(EditText) findViewById(R.id.us_pass);
        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String username = this.getIntent().getExtras().getString("IngresarUss");
        String password = this.getIntent().getExtras().getString("IngresaPass");

       user.setText(username);
       pass.setText(password);


    }


    public void us_register(View view){

        String username= user.getText().toString();
        String password= pass.getText().toString();

        User user= UserRepository.agregarUsuario(username,password);
        Toast.makeText(this, "Usuario nuevo creado:"+username, Toast.LENGTH_SHORT).show();

        // Save to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor
                .putString("username", user.getUsername())
                .putString("password", user.getPassword())
                .putBoolean("islogged", true)
                .commit();

        // Go to Dashboard
        goDashboard();
    }

    private void goDashboard(){
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
