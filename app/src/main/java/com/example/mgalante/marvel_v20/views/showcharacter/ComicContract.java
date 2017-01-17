package com.example.mgalante.marvel_v20.views.showcharacter;

import android.content.Context;

import com.example.mgalante.marvel_v20.api.entities.Comic;
import com.example.mgalante.marvel_v20.views.BasePresenter;
import com.example.mgalante.marvel_v20.views.BaseView;

import java.util.List;

/**
 * Created by mgalante on 17/01/17.
 */

public interface ComicContract {
    interface View extends BaseView<Presenter>{
        //fillData
        void fillData(List<Comic> list);
    }

    interface  Presenter extends BasePresenter<Context,View>{
        //getComics
        void getComics(int characterId);
    }
}
