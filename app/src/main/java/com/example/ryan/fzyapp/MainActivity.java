package com.example.ryan.fzyapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.ryan.fzyapp.adapters.FragmentAdapter;
import com.example.ryan.fzyapp.base.BaseActivity;
import com.example.ryan.fzyapp.fragment.AndroidFragment;
import com.example.ryan.fzyapp.fragment.AppFragment;
import com.example.ryan.fzyapp.fragment.IosFragment;
import com.example.ryan.fzyapp.fragment.MeiZhiFragment;
import com.example.ryan.fzyapp.fragment.QianDuanFragment;
import com.example.ryan.fzyapp.fragment.RestVideoFragment;
import com.example.ryan.fzyapp.fragment.TaZhangFragment;
import com.example.ryan.fzyapp.fragment.XiaTuiJieFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity  {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
    }

    @Override
    protected void initData() {
        List<String> titles = new ArrayList<>();
        titles.add("妹纸");
        titles.add("Android");
        titles.add("iOS");
        titles.add("休息视频");
        titles.add("拓展资源");
        titles.add("前端");
        titles.add("瞎推荐");
        titles.add("App");

        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(MeiZhiFragment.newInstance());
        fragments.add( AndroidFragment.newInstance());
        fragments.add(IosFragment.newInstance());
        fragments.add(RestVideoFragment.newInstance());
        fragments.add( TaZhangFragment.newInstance());
        fragments.add( QianDuanFragment.newInstance());
        fragments.add( XiaTuiJieFragment.newInstance());
        fragments.add( AppFragment.newInstance());


        FragmentStatePagerAdapter fragmentStatePagerAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(fragmentStatePagerAdapter);
        //将 TabLayout 和 ViewPager 关联起来
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(fragmentStatePagerAdapter);

    }
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


}
