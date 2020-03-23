package com.bawei.zhuyanping20200323.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zhuyanping20200323.R;
import com.bawei.zhuyanping20200323.bean.ShopBean;
import com.bawei.zhuyanping20200323.util.NetUtil;

import java.util.List;

public class Mybase extends BaseAdapter {
    private List<ShopBean.DataBean> list;

    public Mybase(List<ShopBean.DataBean> list) {

        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHonider honider;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybase,parent,false);
            honider = new ViewHonider();
            honider.img = convertView.findViewById(R.id.img);
            honider.name = convertView.findViewById(R.id.name);
            honider.price = convertView.findViewById(R.id.price);
            convertView.setTag(honider);
        }else{
            honider = (ViewHonider) convertView.getTag();
        }
        ShopBean.DataBean dataBean = list.get(position);
        honider.name.setText(dataBean.getGoods_name());
        honider.price.setText(dataBean.getCurrency_price());
        NetUtil.getInstance().doGetpoho(dataBean.getGoods_thumb(), honider.img);
        return convertView;
    }
    class ViewHonider{
        TextView name;
        TextView price;
        ImageView img;
    }
}
