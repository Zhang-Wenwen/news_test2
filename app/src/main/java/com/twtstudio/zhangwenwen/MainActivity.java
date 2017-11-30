package com.twtstudio.zhangwenwen;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<NewsFragment> listFrag = new ArrayList<>();
//    List<ListBean.DataBean> mNewsList;
//    NewsAdapter newsAdapter = new NewsAdapter(this,mNewsList);
    LinearLayoutManager layoutManager;
    ViewPager viewpager;
    TabLayout list_tabLayout;
    FragmentPagerAdapter fragmentPagerAdapter;
    boolean isLoading = false;
    int number = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_tabLayout = findViewById(R.id.list_tabLayout);
        viewpager = findViewById(R.id.viewpager);
        NewsFragment newsFragment1 = new NewsFragment();
        newsFragment1.setType(1);
        NewsFragment newsFragment2 = new NewsFragment();
        newsFragment1.setType(2);
        NewsFragment newsFragment3 = new NewsFragment();
        newsFragment1.setType(3);
        NewsFragment newsFragment4 = new NewsFragment();
        newsFragment1.setType(4);
        listFrag.add(newsFragment1);
        listFrag.add(newsFragment2);
        listFrag.add(newsFragment3);
        listFrag.add(newsFragment4);
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFrag.get(position);
            }

            @Override
            public int getCount() {
                return listFrag.size();
            }
        };
        list_tabLayout.setupWithViewPager(viewpager);
        list_tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        viewpager.setAdapter(fragmentPagerAdapter);
        //网络请求部分
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://open.twtstudio.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final API api = retrofit.create(API.class);
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    final Response<ListBean> response = api.getNewsBean(1,1).execute();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ListBean body = response.body();
                            if (body!=null && body.error_code == -1){
                                List<ListBean.DataBean> dataBeanList = body.data;
//                                newsAdapter.addDataList(dataBeanList);
                            }
                        }
                    });
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
