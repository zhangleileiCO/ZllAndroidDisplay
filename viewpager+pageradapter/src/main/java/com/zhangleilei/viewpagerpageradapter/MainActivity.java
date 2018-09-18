package com.zhangleilei.viewpagerpageradapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhangleilei.myviews.ZoomImageView;

public class MainActivity extends Activity {
    private Context mContext;
    private ViewPager viewPager;
    private int[] imgs=new int[]{R.drawable.dashboard,R.drawable.mine,R.drawable.msg,R.drawable.msg_filter};
    private ImageView[] imageViews =new ImageView[imgs.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ZoomImageView imageView=new ZoomImageView(getApplicationContext());
                imageView.setImageResource(imgs[position]);
                container.addView(imageView);
                imageViews[position]=imageView;
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(imageViews[position]);
            }

            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view==o;
            }
        });
    }
}
