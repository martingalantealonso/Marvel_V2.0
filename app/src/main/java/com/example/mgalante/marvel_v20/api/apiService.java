package com.example.mgalante.marvel_v20.api;

import com.example.mgalante.marvel_v20.api.entities.BaseResponse;
import com.example.mgalante.marvel_v20.api.entities.Characters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mgalante on 17/01/17.
 */

public interface apiService {

    interface LoadCharactersCallBack {
        void onCharactersLoaded(BaseResponse<Characters> post);
    }

    @GET("/v1/public/characters")
    Call<BaseResponse<Characters>> getCharactersByStartsWith(@Query("nameStartsWith") String nameStartsWith
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature);
}
