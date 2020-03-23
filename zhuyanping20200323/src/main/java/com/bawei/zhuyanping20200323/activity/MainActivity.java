package com.bawei.zhuyanping20200323.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.zhuyanping20200323.R;
import com.bawei.zhuyanping20200323.adapter.Mybase;
import com.bawei.zhuyanping20200323.base.BaseActivity;
import com.bawei.zhuyanping20200323.bean.ShopBean;
import com.bawei.zhuyanping20200323.util.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    PullToRefreshListView pullToRefreshListView;
    List<ShopBean.DataBean> list = new ArrayList<>();
    int page = 1;
    @Override
    protected void initdata() {
        getdata();
    }

    private void getdata() {
        if (NetUtil.getInstance().Hannet(this)){
            String httpurl = "";
            if (page == 1){
                httpurl = "http://blog.zhaoliang5156.cn/api/shop/shop1.json";
            }else if (page == 2){
                 httpurl = "http://blog.zhaoliang5156.cn/api/shop/shop2.json";
            }else {
                 httpurl = "http://blog.zhaoliang5156.cn/api/shop/shop3.json";
            }
            NetUtil.getInstance().doGet(httpurl, new NetUtil.MyCall() {
                @Override
                public void doGetSu(String json) {
                    ShopBean shopBean = new Gson().fromJson(json, ShopBean.class);
                     list.addAll(shopBean.getData());
                    Mybase mybase = new Mybase(list);
                    pullToRefreshListView.setAdapter(mybase);
                    pullToRefreshListView.onRefreshComplete();
                }

                @Override
                public void doGetEr() {

                }
            });
        }else {
            //网络失败  吐司
            Toast.makeText(this, "网络失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initview() {
        pullToRefreshListView = findViewById(R.id.pull);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                page=1;
                getdata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getdata();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}
