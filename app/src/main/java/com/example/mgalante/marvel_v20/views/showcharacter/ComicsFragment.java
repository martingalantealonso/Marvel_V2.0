package com.example.mgalante.marvel_v20.views.showcharacter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mgalante.marvel_v20.R;
import com.example.mgalante.marvel_v20.api.entities.Comic;
import com.example.mgalante.marvel_v20.control.adapters.ComicsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgalante on 17/01/17.
 */

public class ComicsFragment extends Fragment implements ComicContract.View{

    private static final String ARG_CHARACTER = "charId";
    private int characterId;
    private Context mContext;
    private ComicPresenterImpl mPresenter;
    private RecyclerView mRecyclerView;
    private ComicsRecyclerViewAdapter adapter;


    public ComicsFragment() {
    }

    public static ComicsFragment newInstance(int characterId) {
        ComicsFragment fragment = new ComicsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CHARACTER, characterId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            characterId = getArguments().getInt(ARG_CHARACTER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resource_list, container, false);

        // Set the adapter
        mContext = view.getContext();
        if (view instanceof RecyclerView) {

            mRecyclerView = (RecyclerView) view;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            List<Comic> entities = new ArrayList<>();
            adapter = new ComicsRecyclerViewAdapter(mContext, entities);
            mRecyclerView.setAdapter(adapter);
            this.mPresenter.getComics(characterId);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void setPresenter(ComicContract.Presenter presenter) {
        this.mPresenter = (ComicPresenterImpl) presenter;
    }

    @Override
    public void fillData(List<Comic> list) {
        adapter.fillData(list);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void showMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showList(boolean show) {

    }

    @Override
    public void showProgressBar(boolean show) {

    }


}
