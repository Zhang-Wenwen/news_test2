package com.twtstudio.zhangwenwen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/11/24.
 * recycler的适配器还是没有分出来，需要后期改善
 */

public class NewsFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {
    private View cache ;
    int page = 1;
    int type;
    int number = 2;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    NewsAdapter newsAdapter;
    LinearLayoutManager layoutManager;
    boolean isLoading = false;
    List<ListBean.DataBean> mNewsList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (cache == null) {
            layoutManager = new LinearLayoutManager(getActivity());
            newsAdapter = new NewsAdapter(getActivity(),mNewsList);
            View view = inflater.inflate(R.layout.fragment, container, false);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
            refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(newsAdapter);
            final NewsAdapter newsAdapter = new NewsAdapter(getActivity(),mNewsList);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://open.twtstudio.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final API api = retrofit.create(API.class);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Response<ListBean> response = api.getNewsBean(type, 1).execute();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ListBean body = response.body();
                                if (body != null && body.error_code == -1) {
                                    List<ListBean.DataBean> dataBeanList = body.data;
                                    newsAdapter.addDataList(dataBeanList);
                                    newsAdapter.notifyDataSetChanged();
                                    refreshLayout.setRefreshing(false);
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshData();
                }
            });
            cache = view;
            return view;
        }
        else {
            return cache;
        }
    }

    private void refreshData() {
        final NewsAdapter newsAdapter = new NewsAdapter(getActivity(),mNewsList);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://open.twtstudio.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final API api = retrofit.create(API.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Response<ListBean> response = api.getNewsBean(type, 1).execute();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ListBean body = response.body();
                            if (body != null && body.error_code == -1) {
                                  mNewsList = response.body().data;
                                newsAdapter.addDataList(mNewsList);
                                newsAdapter.notifyDataSetChanged();
                                refreshLayout.setRefreshing(false);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void onActivityCreated(Bundle savedInstaneState) {
        super.onActivityCreated(savedInstaneState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://open.twtstudio.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final API api = retrofit.create(API.class);
        //    监听屏幕滑动事件
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int mposition = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mposition + 1 == newsAdapter.getItemCount() && !isLoading) {
                    isLoading = true;
                    new Thread(new Runnable(){
                        @Override
                        public void run(){
                            try {
                                final Response<ListBean> response = api.getNewsBean(1,1).execute();
                               getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ListBean body = response.body();
                                        if (body!=null && body.error_code == -1){
                                            List<ListBean.DataBean> dataBeanList = body.data;
                                            newsAdapter.addDataList(dataBeanList);
                                        }
                                    }
                                });
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Response<ListBean> response = api.getNewsBean(1, number).execute();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ListBean body = response.body();
                                        if (body != null && body.error_code == -1) {
                                            List<ListBean.DataBean> dataBeanList = body.data;
                                            newsAdapter.addDataList(dataBeanList);
                                            isLoading = false;
                                        }
                                    }
                                });
                                number++;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
};


