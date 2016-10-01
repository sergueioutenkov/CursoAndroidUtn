package com.serguei.cursos.cursoandroidutn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView userName;
    TextView userPassword;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Chequeamos si el usuario ya esta logueado, en cuyo caso lo redirigimos a la MainActivity
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.shared_prefs_name), Context.MODE_PRIVATE);
        boolean loggedIn = sharedPref.getBoolean(getString(R.string.saved_login_status), false);
        if(loggedIn){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }

        //Instanciamos las vistas
        userName = (TextView) findViewById(R.id.login_user);
        userPassword = (TextView) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_button);

        //Logica al hacer click en el boton
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUserInput()){
                    // Transicion hacia la DataActivity
                    // Seteamos el intent explicitamente
                    Intent i = new Intent(LoginActivity.this, DataActivity.class);
                    //Guardamos en el intent el username, para pasarlo a la DataActivity
                    i.putExtra(DataActivity.USER_NAME, userName.getText().toString());
                    //Iniciamos la activity
                    startActivity(i);

                }
            }
        });
    }

    private boolean validateUserInput() {

        boolean displayUserNameError = false;
        boolean displayUserPasswordError = false;

        if (userName.getText().toString().isEmpty()) {
            displayUserNameError = true;
            userName.setError(getResources().getString(R.string.login_user_error_text));
        }

        if (!(userPassword.getText().toString().length() > 3)) {
            displayUserPasswordError = true;
            userPassword.setError(getResources().getString(R.string.login_password_error_text));
        }

        if(displayUserNameError || displayUserPasswordError){
            return false;
        }
        else{
            return true;
        }
    }

}
