package com.zhangleilei.radiogroupfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.zhangleilei.radiogroupfragment.fragment.AroundFragment;
import com.zhangleilei.radiogroupfragment.fragment.HomeFragment;
import com.zhangleilei.radiogroupfragment.fragment.MeFragment;
import com.zhangleilei.radiogroupfragment.fragment.MoreFragment;

public class MainActivity extends AppCompatActivity {
    RadioGroup mRgBottomMenu;
    //数组 存储Fragment
    private Class[] mFragmentClasss = new Class[] {HomeFragment.class,AroundFragment.class,MeFragment.class,MoreFragment.class};
    private Fragment[] mFragments=new Fragment[4];
    //当前Fragent的下标
    private int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRgBottomMenu = (RadioGroup) findViewById(R.id.rg_bottom_menu);

        for (int i=0;i<mFragmentClasss.length;i++){
            mFragments[i]=Fragment.instantiate(this,mFragmentClasss[i].getName(),null);
        }

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_content,mFragments[0]).commit();

        mRgBottomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        setIndexSelected(0);
                        break;
                    case R.id.rb_around:
                        setIndexSelected(1);
                        break;
                    case R.id.rb_me:
                        setIndexSelected(2);
                        break;
                    case R.id.rb_more:
                        setIndexSelected(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    //设置Fragment页面
    private void setIndexSelected(int index) {
        if (currentIndex == index) {
            return;
        }
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //隐藏当前Fragment
        ft.hide(mFragments[currentIndex]);
        //判断Fragment是否已经添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.main_content,mFragments[index]).show(mFragments[index]);
        }else {
            //显示新的Fragment
            ft.show(mFragments[index]);
        }
        ft.commit();
        currentIndex = index;
    }
}
