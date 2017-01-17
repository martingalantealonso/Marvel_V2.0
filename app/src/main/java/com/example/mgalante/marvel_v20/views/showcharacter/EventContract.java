package com.example.mgalante.marvel_v20.views.showcharacter;

import android.content.Context;

import com.example.mgalante.marvel_v20.api.entities.Event;
import com.example.mgalante.marvel_v20.views.BasePresenter;
import com.example.mgalante.marvel_v20.views.BaseView;

import java.util.List;

/**
 * Created by mgalante on 17/01/17.
 */

public interface EventContract {
    interface View extends BaseView<Presenter>{
        void fillData(List<Event> list);
    }
    interface  Presenter extends BasePresenter<Context,View>{
        void getEvents(int characterId);
    }
}
