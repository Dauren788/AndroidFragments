package com.example.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartherd.multiplescreensupport.MainActivity;

public class LoginActivity extends AppCompatActivity implements MainActivity.LogOutListener{

    static Context context ;
    FragmentManager fragmentManager;
    Button log;
    final String SAVED_TEXT = "initial";
    EditText email , password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // log.setOnClickListener(this);

        context = this;
        Log.e("my" , "createMain");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString("email" , "");
        String savedPassword = sharedPreferences.getString("password" , "");
        savedEmail = savedEmail.trim();
        savedPassword = savedPassword.trim();

        if( (!savedEmail.isEmpty()) && (!savedPassword.isEmpty()) ){
            Log.e("my" , "toMainPage " + savedEmail + " pass " + savedPassword);
            this.toMainPage();
        }

        log = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    public void log(View v){
        this.save();
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        if(emailText.isEmpty() || passwordText.isEmpty()){
            Toast.makeText(this ,"Can not be empty email or password!" ,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "email:" + emailText + "password:" + passwordText , Toast.LENGTH_LONG).show();
            this.toMainPage();
        }
    }
    public void toMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void save(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email" , email.getText().toString());
        editor.putString("password" , password.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void out() {
        Log.e("my" , "MainActivity OUT");
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email" , "");
        editor.putString("password", "");
        editor.apply();
    }

   //Override
   //ublic void onClick(View view) {
   //   Intent i;
   //   i = new Intent(this, MainActivity.class);
   //   startActivity(i);
   //
   //
   //
}
