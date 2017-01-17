package com.example.mgalante.marvel_v20.views.main;

import android.content.Context;

import com.example.mgalante.marvel_v20.R;
import com.example.mgalante.marvel_v20.api.SendRequest;
import com.example.mgalante.marvel_v20.api.ServiceMarvel;
import com.example.mgalante.marvel_v20.api.entities.BaseResponse;
import com.example.mgalante.marvel_v20.api.entities.Characters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mgalante on 16/01/17.
 */

public class MainPresenterImpl implements MainContract.Presenter {

    private final ServiceMarvel mService;
    private MainContract.View mView;
    private Context mContext;

    public MainPresenterImpl(ServiceMarvel service) {
        this.mService = service;
    }

    @Override
    public void attach(Context context, MainContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void getHeroes(String search) {
        final SendRequest request = SendRequest.create();
        Call<BaseResponse<Characters>> call = mService.getService(mContext).getCharactersByStartsWith(search, "1", request.getPublicKey(), request.getHashSignature());
        call.enqueue(new Callback<BaseResponse<Characters>>() {
            @Override
            public void onResponse(Call<BaseResponse<Characters>> call, Response<BaseResponse<Characters>> response) {

                if (response.isSuccessful()) {
                    if (response.body().data != null && response.body().data.results.size() > 0){
                        mView.fillData(response.body().data.results);
                    }else{
                        mView.showMessage(mContext.getString(R.string.sin_resultados));
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Characters>> call, Throwable t) {
                mView.showMessage("Error al recuperar los datos");
            }
        });
    }
}
