package com.serguei.cursos.cursoandroidutn;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by serguei on 01/10/16.
 */

public class MainActivity extends AppCompatActivity {

    TextView helloMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloMessage = (TextView) findViewById(R.id.hello_message);

        //Obtenemos el nombre y apellido de las shared preferences
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.shared_prefs_name), Context.MODE_PRIVATE);
        String name = sharedPref.getString(getString(R.string.saved_name), "name");
        String lastName = sharedPref.getString(getString(R.string.saved_lastname), "last name");

        helloMessage.setText(String.format(getString(R.string.hello_message_text), name + " " + lastName));
    }
}
