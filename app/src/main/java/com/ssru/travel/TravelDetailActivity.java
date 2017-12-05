package com.ssru.travel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.ssru.travel.model.ModelGallery;
import com.ssru.travel.model.ModelTravelDetail;

import java.util.HashMap;

import at.blogc.android.views.ExpandableTextView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TravelDetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,View.OnClickListener{
    Context mContext;
    TextView txt_title;
    LinearLayout view,view_detail,view_address,view_time,view_nearby;
    private SliderLayout mDemoSlider;
    HashMap<String, String> url_maps;
    Button btn_search,btn_image360,btn_image;
    String la, lo;
    int id = 0;
    String TAG = "TravelDetailActivity";
    ImageView img_ex1,img_ex2, img_ex4, img_ex5;
    String image360;

    ExpandableTextView ex_detail,ex_address, ex_time,ex_nearby;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_temple);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        url_maps = new HashMap<String, String>();

        txt_title = (TextView) findViewById(R.id.txt_title);
        view = (LinearLayout) findViewById(R.id.view);

        view_detail = (LinearLayout) findViewById(R.id.view_detail);
        view_address = (LinearLayout) findViewById(R.id.view_address);
        view_time = (LinearLayout) findViewById(R.id.view_time);
        view_nearby = (LinearLayout) findViewById(R.id.view_nearby);

        btn_search = (Button) findViewById(R.id.btn_search);
        btn_image360 = (Button) findViewById(R.id.btn_image360);
        btn_image = (Button) findViewById(R.id.btn_image);

        ex_detail = (ExpandableTextView) this.findViewById(R.id.ex_detail);
        ex_address = (ExpandableTextView) this.findViewById(R.id.ex_address);
        ex_time = (ExpandableTextView) this.findViewById(R.id.ex_time);
        ex_nearby = (ExpandableTextView) this.findViewById(R.id.ex_nearby);

        img_ex1 = (ImageView) findViewById(R.id.img_ex1);
        img_ex2 = (ImageView) findViewById(R.id.img_ex2);
        img_ex4 = (ImageView) findViewById(R.id.img_ex4);
        img_ex5 = (ImageView) findViewById(R.id.img_ex5);

        setSettingEx(ex_detail);
        setSettingEx(ex_address);
        setSettingEx(ex_time);
        setSettingEx(ex_nearby);


        EditText df;

        view_detail.setOnClickListener(this);
        view_address.setOnClickListener(this);
        view_time.setOnClickListener(this);
        view_nearby.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        btn_image360.setOnClickListener(this);
        btn_image.setOnClickListener(this);

        Bundle i = getIntent().getExtras();
        id = i.getInt("id");
        new ConnectAPI().getTravelId(mContext, id);
    }

    private void setSettingEx(ExpandableTextView ex) {
        ex.setAnimationDuration(750L);
        ex.setInterpolator(new LinearOutSlowInInterpolator());
    }

    public void setView(String string, String url) {
        Gson gson = new Gson();
        ModelTravelDetail model = gson.fromJson(string, ModelTravelDetail.class);

        image360 = model.getTravel().getImage360();

        for (ModelGallery name :
                model.getGallery()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(model.getTravel().getName())
                    .image(ConnectAPI.URL + "/travelImageGallery/" + name.getGalleryImageName())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", model.getTravel().getName());

            mDemoSlider.addSlider(textSliderView);
        }

//        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setClickable(false);


        la = model.getTravel().getLatitude();
        lo = model.getTravel().getLongitude();


        getScreenOrientation(model.getTravel().getName());

        ex_detail.setText(Html.fromHtml(model.getTravel().getDetail()));
        ex_address.setText(model.getTravel().getAddress());
        ex_time.setText(model.getTravel().getTimeOpen() + "-" + model.getTravel().getTimeClose());
        ex_nearby.setText(model.getTravel().getPlaceMost());

        view.setVisibility(View.VISIBLE);
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void onResume() {
        super.onResume();

        new ConnectAPI().getTravelId(mContext, id);
    }

    public void getScreenOrientation(String templeName) {
        String[] temp = templeName.split(" ");
        String name = templeName.substring(0, temp[0].length()) + "\n" +
                templeName.substring(temp[0].length());

        Display screenOrientation = getWindowManager().getDefaultDisplay();
        if (screenOrientation.getWidth() == screenOrientation.getHeight()) {
            txt_title.setText(name);
        } else {
            if (screenOrientation.getWidth() < screenOrientation.getHeight()) {
                txt_title.setText(name);
            } else {
                txt_title.setText(templeName);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+la+","+lo));
                startActivity(intent);
                break;
            case R.id.btn_image360:
                Intent intent1 = new Intent(this, Img360Activity.class);
                intent1.putExtra("image", image360);
                startActivity(intent1);
                break;
            case R.id.view_detail:
                setEx(ex_detail,img_ex1);
                break;
            case R.id.view_address:
                setEx(ex_address,img_ex2);
                break;
            case R.id.view_time:
                setEx(ex_time,img_ex4);
                break;
            case R.id.view_nearby:
                setEx(ex_nearby,img_ex5);
                break;
                case R.id.btn_image:
                    Intent intent2 = new Intent(this, ViewPagerActivity.class);
                    intent2.putExtra("id", id);
                    startActivity(intent2);
                break;
        }
    }

    private void setEx(ExpandableTextView ex,ImageView img) {
        ex.toggle();
        if (ex.isExpanded()) {
            img.animate().rotation(0).start();
            ex.collapse();
        } else {
            img.animate().rotation(180).start();
            ex.expand();
        }
    }
}
