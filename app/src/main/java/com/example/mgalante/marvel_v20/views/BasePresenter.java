package com.example.mgalante.marvel_v20.views;

/**
 * Created by mgalante on 16/01/17.
 */

public interface BasePresenter<T,V>  {

    void attach(T context, V view);
}
