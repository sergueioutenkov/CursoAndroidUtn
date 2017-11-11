package com.serguei.catmeter.catfactslist.model;// Created by Serguei Outenkov on 8/28/16.

import java.util.List;

/**
 * Clase que representa el modelo obtenido de la API
 */
public class CatFactsResponse {
    public List<CatFact> data;

    public static class CatFact{
        public String fact;
    }
}
