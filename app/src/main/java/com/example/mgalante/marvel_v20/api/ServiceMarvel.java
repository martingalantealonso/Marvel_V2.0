package com.example.mgalante.marvel_v20.api;

import android.content.Context;

/**
 * Created by mgalante on 17/01/17.
 * Aqui se crea el Servicio que se Genera en la clase ServiceGenerator.
 */

public class ServiceMarvel {

    private static apiService service=null;

    public static apiService getService(Context context){
        service=ServiceGenerator.createService(apiService.class,context);
        return  service;
    }
}
