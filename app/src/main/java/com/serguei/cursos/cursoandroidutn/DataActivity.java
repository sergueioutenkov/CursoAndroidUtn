package com.serguei.cursos.cursoandroidutn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by serguei on 01/10/16.
 */

public class DataActivity extends AppCompatActivity {

    static final String USER_NAME = "userName";

    TextView welcomeMessage;
    EditText name;
    EditText lastName;
    EditText address;
    Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //Inicializamos las vistas
        welcomeMessage = (TextView) findViewById(R.id.welcome_message);
        name = (EditText) findViewById(R.id.data_name);
        lastName = (EditText) findViewById(R.id.data_lastname);
        address = (EditText) findViewById(R.id.data_address);
        saveButton = (Button) findViewById(R.id.data_save_button);

        //Obtenemos el userName que nos fue pasado desde LoginActivity
        Intent i = getIntent();
        String userName = i.getExtras().getString(USER_NAME);
        //Formateamos el texto para que contenga la nombre de usuario
        welcomeMessage.setText(String.format(getResources().getString(R.string.data_welcome_message), userName));

        //Al guardar, guardamos el nombre y apellido, asi como un boolean diciendo que el usuario ya
        //completo el login y el registro de datos
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos las shared preferences
                SharedPreferences sharedPref = DataActivity.this.getSharedPreferences(getString(R.string.shared_prefs_name), Context.MODE_PRIVATE);

                //Instanciamos el editor
                SharedPreferences.Editor editor = sharedPref.edit();

                //Guardamos el nombre y el apellido
                editor.putString(getString(R.string.saved_name), name.getText().toString());
                editor.putString(getString(R.string.saved_lastname), lastName.getText().toString());
                //Asi como tambien el estado del login
                editor.putBoolean(getString(R.string.saved_login_status), true);

                //Aplicamos los cambios
                editor.apply();

                //Nos trasladamos a la MainActivity
                Intent i = new Intent(DataActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
