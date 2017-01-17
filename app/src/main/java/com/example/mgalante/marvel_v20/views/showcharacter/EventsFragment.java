/*
 *   ~           _MMMMM`
 *   ~     __MMMMMMMMM`       J        openTrends Solucions i Sistemes, S.L.
 *   ~ JMMMMMMMMMMMMF       JM         http://www.opentrends.net
 *   ~ MMMMMMMMMMF       _JMM`         info@opentrends.net
 *   ~ MMMMMMMF`    .JMMMMF`
 *   ~ """")    _JMMMMMMF`             Veneçuela, 105 1-A
 *   ~ _MMMMMMMMMMMMMMM`      .M)      Barcelona, 08019
 *   ~ MMMMMMMMMMMMMF`     .JMM`       Spain
 *   ~ MMMMMMMMMM"     _MMMMMF
 *   ~ M4MMM""`   ._MMMMMMMM`          *************************************
 *   ~ _______MMMMMMMMMMMF             BP002-Bonpreu
 *   ~ MMMMMMMMMMMMMMMM"               *************************************
 *   ~ MMMMMMMMMMMMF"                  Copyright (C) 2015 openTrends, Tots els drets reservats
 *   ~ MMMMMMMM""                      Copyright (C) 2015 openTrends, All Rights Reserved
 *   ~
 *   ~                                 This program is free software; you can redistribute it and/or modify
 *   ~                                 it under the terms of the GNU General Public License as published by
 *   ~                                 the Free Software Foundation; either version 2 of the License, or
 *   ~                                 (at your option) any later version.
 *   ~
 *   ~                                 This program is distributed in the hope that it will be useful,
 *   ~                                 but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   ~                                 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   ~                                 GNU General Public License for more details.
 *   ~
 *   ~                                 You should have received a copy of the GNU General Public License along
 *   ~                                 with this program; if not, write to the Free Software Foundation, Inc.,
 *   ~                                 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.example.mgalante.marvel_v20.views.showcharacter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mgalante.marvel_v20.R;
import com.example.mgalante.marvel_v20.api.entities.Event;


import java.util.ArrayList;
import java.util.List;


public class EventsFragment extends Fragment implements EventContract.View {

    private static final String ARG_CHARACTER = "charId";
    private EventPresenterImpl mPresenter;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private int characterId;
    private EventsRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            characterId = getArguments().getInt(ARG_CHARACTER);
        }
    }

    public static EventsFragment newInstance(int characterId) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CHARACTER, characterId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resource_list, container, false);

        // Set the adapter
        mContext = view.getContext();
        if (view instanceof RecyclerView) {

            mRecyclerView = (RecyclerView) view;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            List<Event> entities = new ArrayList<>();
            adapter = new EventsRecyclerViewAdapter(mContext, entities);
            mRecyclerView.setAdapter(adapter);
            this.mPresenter.getEvents(characterId);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void setPresenter(EventContract.Presenter presenter) {
        this.mPresenter = (EventPresenterImpl) presenter;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void fillData(List<Event> list) {
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
