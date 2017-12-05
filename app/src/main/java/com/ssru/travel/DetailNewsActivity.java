package com.ssru.travel;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.ssru.travel.model.ModelNews;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailNewsActivity extends AppCompatActivity {
    TextView txt_title, txt_detail;
    ImageView imageView;
    Context mContext;
    LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_detail = (TextView) findViewById(R.id.txt_detail);
        imageView = (ImageView) findViewById(R.id.image);
        view = (LinearLayout) findViewById(R.id.view);

        Bundle i = getIntent().getExtras();
        int id = i.getInt("id");
        new ConnectAPI().getNewsId(mContext, id);

    }

    public void setView(String string, String url) {
        Gson gson = new Gson();
        ModelNews model = gson.fromJson(string, ModelNews.class);

        try {
            Glide.with(mContext)
                    .load(url + "/newsImage/" + model.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(R.drawable.nopic)
                    .into(imageView);
        } catch (Exception ew) {

        }

        txt_title.setText(model.getName());
        txt_detail.setText(Html.fromHtml(model.getDetail()));
        view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
