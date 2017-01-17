package com.example.mgalante.marvel_v20.views.showcharacter;

import android.content.Context;

import com.example.mgalante.marvel_v20.R;
import com.example.mgalante.marvel_v20.api.SendRequest;
import com.example.mgalante.marvel_v20.api.ServiceMarvel;
import com.example.mgalante.marvel_v20.api.apiServer;
import com.example.mgalante.marvel_v20.api.entities.BaseResponse;
import com.example.mgalante.marvel_v20.api.entities.Event;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mgalante on 17/01/17.
 */

public class EventPresenterImpl implements EventContract.Presenter {

    private EventContract.View mView;
    private Context mContext;

    public void attach(Context context, EventContract.View view) {
        this.mView = view;
        this.mContext = context;
        mView.setPresenter(this);
    }



    @Override
    public void getEvents(int characterId) {
        apiServer server = ServiceMarvel.getService(mContext);
        final SendRequest request = SendRequest.create();
        Call<BaseResponse<Event>> call = server.getEventsByCharacter(characterId, String.valueOf(request.getTimeStamp()), request.getPublicKey(), request.getHashSignature());
        call.enqueue(new Callback<BaseResponse<Event>>() {
            @Override
            public void onResponse(Call<BaseResponse<Event>> call, Response<BaseResponse<Event>> response) {
                if (response.isSuccessful()) {
                    if (response.body().data != null && response.body().data.results.size() > 0) {
                        mView.showList(true);
                        mView.fillData(response.body().data.results);
                    } else {
                        mView.showMessage(mContext.getString(R.string.sin_resultados));
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Event>> call, Throwable t) {
                mView.showMessage("Error");
            }
        });
    }

}
