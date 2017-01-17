package com.example.mgalante.marvel_v20.views.showcharacter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mgalante.marvel_v20.R;
import com.example.mgalante.marvel_v20.api.entities.Characters;
import com.example.mgalante.marvel_v20.api.entities.Url;
import com.example.mgalante.marvel_v20.views.BaseActivity;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowCharacter extends BaseActivity {

    private static final String EXTRA_CHARACTER = "character";
    private Characters mCharacter;

    @Bind(R.id.main_information_holder)
    LinearLayout mHolder;
    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.subname)
    TextView mDescription;
    @Bind(R.id.comiclink)
    Button mComicLink;
    @Bind(R.id.detail)
    Button mDetail;
    @Bind(R.id.wiki)
    Button mWiki;
    @Bind(R.id.tablayout)
    TabLayout mTablayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_character);

        ButterKnife.bind(this);

        Gson gson = new Gson();
        String json = getIntent().getExtras().getString(EXTRA_CHARACTER);
        mCharacter = gson.fromJson(json, Characters.class);
        setTitle(mCharacter.getName());
        String urlImage = mCharacter.getThumbnail().getPath() + "." + mCharacter.getThumbnail().getExtension();
        Glide.with(this).load(urlImage).into(mAvatar);

        mName.setText(mCharacter.getName());
        mDescription.setText(mCharacter.getDescription());

        mComicLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 openUrl(mCharacter.getUrls(), "comiclink");
            }
        });

        mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl(mCharacter.getUrls(), "detail");
            }
        });

        mWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl(mCharacter.getUrls(), "wiki");
            }
        });

    }

    private void openUrl(List<Url> urls, String type) {
        for (Url url:urls
                ) {
            if (url.getType().equals(type)) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url.getUrl()));
                startActivity(i);
            }
        }
    }


    @Override
    public int getLayoutId() {
        return  R.layout.activity_show_character;
    }
}
