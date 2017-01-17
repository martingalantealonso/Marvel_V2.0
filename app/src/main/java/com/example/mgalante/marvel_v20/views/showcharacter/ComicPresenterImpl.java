package com.example.mgalante.marvel_v20.views.showcharacter;

import android.content.Context;

import com.example.mgalante.marvel_v20.R;
import com.example.mgalante.marvel_v20.api.SendRequest;
import com.example.mgalante.marvel_v20.api.ServiceMarvel;
import com.example.mgalante.marvel_v20.api.apiServer;
import com.example.mgalante.marvel_v20.api.entities.BaseResponse;
import com.example.mgalante.marvel_v20.api.entities.Comic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mgalante on 17/01/17.
 */
public class ComicPresenterImpl implements ComicContract.Presenter{

    private ComicContract.View mView;
    private Context mContext;

    @Override
    public void attach(Context context, ComicContract.View view) {
        this.mContext=context;
        this.mView=view;
        mView.setPresenter(this);
    }

    @Override
    public void getComics(int characterId) {
        apiServer server = ServiceMarvel.getService(mContext);
        final SendRequest request=SendRequest.create();
        Call<BaseResponse<Comic>> call =server.getComicsByCharacter(characterId ,String.valueOf(request.getTimeStamp()), request.getPublicKey(),request.getHashSignature());
        call.enqueue(new Callback<BaseResponse<Comic>>() {
            @Override
            public void onResponse(Call<BaseResponse<Comic>> call, Response<BaseResponse<Comic>> response) {
                if(response.isSuccessful()){
                    if(response.body().data!=null && response.body().data.results.size()>0){
                        mView.fillData(response.body().data.results);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Comic>> call, Throwable t) {
                mView.showMessage(mContext.getString(R.string.error));
            }
        });

    }
}
