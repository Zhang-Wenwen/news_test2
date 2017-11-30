package com.twtstudio.zhangwenwen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
//import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/17.
 **/

public class ContentActivity extends AppCompatActivity {
    private WebView webView;

    static public void actionStart(Context context, String newsTitle, String number) {
        Intent intent = new Intent(context, ContentActivity.class);
        intent.putExtra("newsTitle", newsTitle);
        intent.putExtra("news_number", number); //获取新闻的index
        context.startActivity(intent);
    }
        @SuppressLint("SetJavaScriptEnabled")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.content);
            final WebView webView = (WebView)findViewById(R.id.web_view);
            final String number = getIntent().getStringExtra("news_number");
            Log.d("111", "onCreate: "+number);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return  false;
                }
            });
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://open.twtstudio.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final API api = retrofit.create(API.class);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Response<NewsBean> response = api.getNewsContent(number).execute();
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                NewsBean body = response.body();
                                if (body !=null && body.error_code == -1){
                                   webView.loadData(body.data.content,"text/html;charset=utf-8",null);
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
//            webView.loadUrl("http://open.twtstudio.com/api/v1/"+number);

        }
}
