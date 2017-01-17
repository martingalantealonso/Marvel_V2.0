package com.example.mgalante.marvel_v20.views;

/**
 * Created by mgalante on 16/01/17.
 */

public interface BaseView<T> {
    void setPresenter (T presenter);
    void showMessage(String message);
    void showList (boolean show);
    void showProgressBar (boolean show);
}
