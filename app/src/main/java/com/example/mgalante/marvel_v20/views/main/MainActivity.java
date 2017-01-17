package com.example.mgalante.marvel_v20.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mgalante.marvel_v20.R;
import com.example.mgalante.marvel_v20.api.ServiceMarvel;
import com.example.mgalante.marvel_v20.api.entities.Characters;
import com.example.mgalante.marvel_v20.control.adapters.CharactersRecyclerViewAdapter;
import com.example.mgalante.marvel_v20.control.callbacks.CharacterListCallBack;
import com.example.mgalante.marvel_v20.views.BaseActivity;
import com.example.mgalante.marvel_v20.views.showcharacter.ShowCharacter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainContract.View, CharacterListCallBack {

    private static final String EXTRA_CHARACTER = "character";
    @Bind(R.id.heroName)
    TextView mHeroName;
    @Bind(R.id.list_item)
    RecyclerView mListItem;

    private MainPresenterImpl presenter;

    private List<Characters> characters;
    private CharactersRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this, this);

        if (presenter == null)
            presenter = new MainPresenterImpl(new ServiceMarvel());

        presenter.attach(this, this);

        mHeroName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String trimmedText = mHeroName.getText().toString().trim();
                if (trimmedText.length() >= 3) {
                    initSearch(trimmedText);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        characters = new ArrayList<>();

        adapter = new CharactersRecyclerViewAdapter(getBaseContext(), characters, this);
        mListItem.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mListItem.setAdapter(adapter);

    }

    private void initSearch(String trimmedText) {
        presenter.getHeroes(trimmedText);
    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showList(boolean show) {

    }

    @Override
    public void showProgressBar(boolean show) {

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClickCharacter(View v, Characters item) {

        LinearLayout mHolder = (LinearLayout) v.findViewById(R.id.main_information_holder);
        Pair<View, String> holderPair = Pair.create((View) mHolder, "t_item_character");
        Pair<View, String> navPair=null;
        Pair<View, String> statusPair=null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            View navigationBar = findViewById(android.R.id.navigationBarBackground);
            View statusBar = findViewById(android.R.id.statusBarBackground);
            navPair = Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
            statusPair= Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);
        }

        Gson gson = new Gson();
        String json = gson.toJson(item);

        Intent i = new Intent(getBaseContext(), ShowCharacter.class);
        i.putExtra(EXTRA_CHARACTER, json);

        ActivityOptionsCompat options;
        if (ViewConfiguration.get(getApplicationContext()).hasPermanentMenuKey()) {
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, holderPair, navPair, statusPair);
        } else {
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, holderPair, statusPair);
        }
        ActivityCompat.startActivity(this, i, options.toBundle());

    }

    @Override
    public void fillData(List<Characters> characters) {
        this.characters = characters;
        adapter.fillData(characters);
        adapter.notifyDataSetChanged();
    }

}
