package com.twtstudio.zhangwenwen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/10.
 * 用于填入数据，点击列表的标题之后的主要内容
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private static final int ITEM_IMAGE_ONLY = 0;
    private static final int ITEM_IMAGE_COMMON = 1;
    private List<ListBean.DataBean> mNewsList = new ArrayList<>();
    private List<NewsBean.DataBean> mNewsBean = new ArrayList<>();
    static private Context mContext;
    public NewsAdapter(Context context,List<ListBean.DataBean> mNewsList){//构造函数
        this.mNewsList = mNewsList;
        mContext = context;
    }
    public String title;   //还没有给fragment设置题目
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.listView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
             int position = holder.getAdapterPosition();
                ListBean.DataBean newsBean = mNewsList.get(position);
             ContentActivity.actionStart(mContext,
                     newsBean.subject,
                     newsBean.index);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int position) {
        ListBean.DataBean dataBean = mNewsList.get(position);
        viewHolder.news_title.setText(dataBean.subject); //  填充数据 标题
        viewHolder.news_summary.setText(dataBean.summary); //  填充数据 内容简介
        Glide.with(mContext)
                .load(dataBean.pic)
                .placeholder(R.mipmap.ic_launcher)   //默认的图片信息
                .into(viewHolder.image_view);//填充图片信息
        //.placeholder()
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void addDataList(List<ListBean.DataBean> dataBeanList) {
        mNewsList.addAll(dataBeanList);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view;
        TextView news_title;
        TextView  news_summary;
        View listView;
          public ViewHolder(View itemView) {
            super(itemView);
              listView = itemView;
              image_view = (ImageView) itemView.findViewById(R.id.image_view);
              news_title = (TextView) itemView.findViewById(R.id.news_title);
              news_summary = (TextView) itemView.findViewById(R.id.news_summary);
          }
    }
}
