package com.zhangleilei.fragmenttabhost_fragment;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangleilei.fragmenttabhost_fragment.fragment.*;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost fragmentTabHost;
    private Class[] fragments=new Class[]{  HomeFragment.class,AroundFragment.class, MeFragment.class,MoreFragment.class};
    private int[]resTitles=new int[]{R.string.tab_title_home,R.string.tab_title_around,R.string.tab_title_me,R.string.tab_title_more};
    private int[] resIcons=new int[]{R.drawable.tab_home_selector,R.drawable.tab_around_selector, R.drawable.tab_me_selector,R.drawable.tab_more_selector};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTabHost=(FragmentTabHost) findViewById(R.id.fragment_tab_host);
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        for (int i=0;i<fragments.length;i++){
            View view=getLayoutInflater().inflate(R.layout.tab_item,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.tab_item_iv);
            TextView textView =(TextView)view.findViewById(R.id.tab_item_tv);
            imageView.setImageResource(resIcons[i]);
            textView.setText(resTitles[i]);
            fragmentTabHost.addTab(fragmentTabHost.newTabSpec(""+i).setIndicator(view),fragments[i],null);
        }

        fragmentTabHost.setCurrentTab(0);
    }

}
