package com.ssru.travel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssru.travel.model.ModelImageSlide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener{
    String URL;
    private SliderLayout mDemoSlider;
    HashMap<String, String> url_maps;
    Context mContext;

    TextView txt_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        url_maps = new HashMap<String, String>();

        findViewById(R.id.menu1).setOnClickListener(this);
        findViewById(R.id.menu2).setOnClickListener(this);
        findViewById(R.id.menu3).setOnClickListener(this);
        findViewById(R.id.menu4).setOnClickListener(this);

        new ConnectAPI().getImgTravelAll(mContext);
    }

    public void setHeader(String string , String url) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelImageSlide>>() {
        }.getType();
        Collection<ModelImageSlide> enums = gson.fromJson(string, collectionType);
        final ArrayList<ModelImageSlide> posts = new ArrayList<ModelImageSlide>(enums);

        for (ModelImageSlide name:
                posts) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name.getName())
                    .image(url+"/travelImage/"+name.getImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name.getName());

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setClickable(false);
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        mDemoSlider.startAutoCycle();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu1:
                startActivity(new Intent(mContext,DetailHistoryActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.menu2:
                startActivity(new Intent(mContext,TravelAllActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.menu3:
                startActivity(new Intent(mContext,NewsActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.menu4:
                startActivity(new Intent(mContext,MapWatActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
