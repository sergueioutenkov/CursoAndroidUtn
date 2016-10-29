package com.serguei.cursos.cursoandroidutn;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by serguei on 29/10/16.
 */

//Clase que representa la instancia de nuestra aplicacion
public class NotesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializamos REALM
        Realm.init(this);

        //Aca podemos configurar REALM como queramos, aca solamente seteamos que borre la instancia de Realm si ocurre una migracion
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

        //Seteamos la config creada anteriormente como default de REALM
        Realm.setDefaultConfiguration(config);
    }
}
