package com.example.androiddemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddemo.R;
import com.example.androiddemo.entry.ListDatas;

public class ListViewAdapter extends ArrayAdapter<ListDatas> {

    private int listId;

    public ListViewAdapter(Context context, int resource, java.util.List<ListDatas> objects) {
        super(context, resource, objects);
        listId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListDatas listDatas=getItem(position);
//        View view= LayoutInflater.from(getContext()).inflate(listId,parent,false);
        View view;
        ViewHolder viewHolder = null;
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(listId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView=view.findViewById(R.id.list_img);
            viewHolder.textView=view.findViewById(R.id.list_tx);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(listDatas.getImage());
        viewHolder.textView.setText(listDatas.getName());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
