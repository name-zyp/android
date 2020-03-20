package com.example.myapplicationa3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyBase extends BaseAdapter {
    private List<String> list;

    public MyBase(List<String> list) {

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
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybase,parent,false);
            honider = new ViewHonider();
            honider.textView = convertView.findViewById(R.id.textview);
            convertView.setTag(honider);
        }else{
            honider = (ViewHonider) convertView.getTag();
        }
        //["阿尼玛","蒂芙尼","Gucci","Jack\u0026Jones","宝马X7"];
        honider.textView.setText("阿尼玛");
        honider.textView.setText("蒂芙尼");
        honider.textView.setText("Gucci");
        honider.textView.setText("Jack\\u0026Jones");
        honider.textView.setText("宝马X7");
        return convertView;
    }
    class ViewHonider{
        TextView textView;
    }
}
