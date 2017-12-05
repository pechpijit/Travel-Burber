package com.ssru.travel;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.ssru.travel.model.ModelGallery;
import com.ssru.travel.model.ModelNews;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ViewPagerActivity extends AppCompatActivity {
    ViewPager mViewPager;
    int id = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_pager);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        id = getIntent().getExtras().getInt("id");

        new ConnectAPI().getGalleryId(this,id);

    }

    public void setView(String string, String URL) {

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelGallery>>() {
        }.getType();
        Collection<ModelGallery> enums = gson.fromJson(string, collectionType);
        final ArrayList<ModelGallery> posts = new ArrayList<ModelGallery>(enums);

        mViewPager.setAdapter(new SamplePagerAdapter(ViewPagerActivity.this,posts,URL));
    }

    static class SamplePagerAdapter extends PagerAdapter {
        ArrayList<ModelGallery> img;
        Context context;
        String url;
        public SamplePagerAdapter(ViewPagerActivity viewPagerActivity, ArrayList<ModelGallery> img,String url) {
            this.context = viewPagerActivity;
            this.img = img;
            this.url = url;
        }

        @Override
        public int getCount() {
            return img.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            final PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);

            Picasso.with(context)
                    .load(url+"/travelImageGallery/"+img.get(position).getGalleryImageName())
                    .into(photoView, new Callback() {
                        @Override
                        public void onSuccess() {
                            attacher.update();
                        }

                        @Override
                        public void onError() {
                        }
                    });
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
