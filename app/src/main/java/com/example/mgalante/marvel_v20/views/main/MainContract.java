package com.example.mgalante.marvel_v20.views.main;

import android.content.Context;

import com.example.mgalante.marvel_v20.api.entities.Characters;
import com.example.mgalante.marvel_v20.views.BasePresenter;
import com.example.mgalante.marvel_v20.views.BaseView;

import java.util.List;

/**
 * Created by mgalante on 16/01/17.
 */

public interface MainContract {

    interface View extends BaseView<Presenter>{
        void fillData(List<Characters> characters);
    }

    interface Presenter extends BasePresenter<Context,View>{
        void getHeroes(String search);
    }
}
