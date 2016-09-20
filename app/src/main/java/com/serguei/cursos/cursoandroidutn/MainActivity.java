package com.serguei.cursos.cursoandroidutn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Definimos los atributos que representan a las vistas
    TextView greetingLabel;
    Button clickMeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Este metodo setea el layout "activity_main" como el layout que representa a esta activity (pantalla)
        setContentView(R.layout.activity_main);

        // Para interactuar con las vistas, debemos inicializarlas.
        // Lo hacemos llamando el metodo "findViewById()" pasando como parametro el ID que representa a nuestra vista,
        // a traves de la clase "R" que representa a los recursos de nuestra app (layouts, strings, drawables, etc.)
        // Debemos castear al tipo de objeto que estamos buscando, ya que el metodo nos devuelve un objeto de tipo "View"
        greetingLabel = (TextView) findViewById(R.id.greeting_label);
        clickMeButton = (Button) findViewById(R.id.click_me_button);

        //Vamos a hacer que al clickear en el boton, el label cambie a "Hola Usuario!" en español, y "Hello User!" en ingles.

        //Primero, seteamos un "OnClickListener" al boton, para ejecutar codigo cuando el usuario haga click en el mismo
        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiamos el texto del label, este texto sera localizado, es decir,
                // mostrara el texto guardado en "values-en" si el dispositivo del usuario esta en ingles,
                // y mostrara el texto guardado en "values" si el dispositivo esta en cualquier otro idioma (entre ellos español).
                // Nuevamente nos ayudamos con la clase R para referenciar recursos (en este caso un "string")
                greetingLabel.setText(R.string.label_user_greeting);
            }
        });

    }
}
