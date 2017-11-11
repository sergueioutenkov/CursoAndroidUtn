package com.serguei.catmeter.catfactslist.service;// Created by Serguei Outenkov on 8/28/16.

import com.serguei.catmeter.catfactslist.model.CatFactsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatFactsAPI {

    //URL base de nuestra API
    String ENDPOINT = "https://catfact.ninja/";

    /**
     * Método que obtiene la lista de curiosidades de los gatos
     * Ejemplo URL: http://catfacts-api.appspot.com/api/facts?number=5
     *
     * @param number
     * @return
     */
    @GET("facts")
    Call<CatFactsResponse> getCatFacts(@Query("limit") int number);
}
