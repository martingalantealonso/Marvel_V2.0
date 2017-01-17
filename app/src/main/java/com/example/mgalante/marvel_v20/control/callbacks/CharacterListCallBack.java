package com.example.mgalante.marvel_v20.control.callbacks;

import android.view.View;

import com.example.mgalante.marvel_v20.api.entities.Characters;

/**
 * Created by mgalante on 16/01/17.
 */

public interface CharacterListCallBack {
    void onClickCharacter(View v, Characters item);
}
