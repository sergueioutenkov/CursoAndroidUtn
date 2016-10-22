package com.serguei.catmeter.catfactslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serguei.catmeter.R;
import com.serguei.catmeter.catfactslist.model.CatFactsResponse;
import com.serguei.catmeter.catfactslist.service.CatFactsAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatFactsListActivity extends AppCompatActivity {

    //Vistas
    RecyclerView recyclerView;
    ProgressBar loadingBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //"Inflamos" el menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciamos nuestro recycler view
        recyclerView = (RecyclerView) findViewById(R.id.cat_facts_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(CatFactsListActivity.this));

        //Instanciamos nuestra loading bar
        loadingBar = (ProgressBar) findViewById(R.id.loading_bar);

        //Hacemos la llamada a la API
        getCatFacts();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh) {

            //Si se hace click en el icono refresh del menu, llamamos a la API
            getCatFacts();
        }

        return true;
    }

    private void getCatFacts() {

        //Instanciamos el parseador Gson
        Gson gson = new GsonBuilder().create();

        //Creamos la instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CatFactsAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Instanciamos la interface gracias a Retrofit
        CatFactsAPI catFactsAPI = retrofit.create(CatFactsAPI.class);

        //Ocultamos el recycler view, peor mostramos el loading
        recyclerView.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);

        //Inscanciamos la llamada (Call) a la API, pasando como parametro
        // la cantidad de facts sobre los gatos que queremos obtener
        Call<CatFactsResponse> call = catFactsAPI.getCatFacts(30);

        //Ejecutamos la llamada en un thread distinto al main (por eso el "enqueue" en vez de "execute")
        call.enqueue(new Callback<CatFactsResponse>() {
            @Override
            public void onResponse(Call<CatFactsResponse> call, Response<CatFactsResponse> response) {

                //Cuando obtenemos la respuesta, populamos el adapter
                CatFactsAdapter adapter = new CatFactsAdapter(response.body().facts, CatFactsListActivity.this);
                recyclerView.setAdapter(adapter);

                //Mostrar el recycler view, ocultar el loading
                recyclerView.setVisibility(View.VISIBLE);
                loadingBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CatFactsResponse> call, Throwable t) {

                //Si falla, mostramos el recycler view, ocultamos el loading y mostramos un mensaje
                recyclerView.setVisibility(View.VISIBLE);
                loadingBar.setVisibility(View.GONE);

                Toast.makeText(CatFactsListActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
