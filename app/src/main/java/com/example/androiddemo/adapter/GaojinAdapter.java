package com.example.androiddemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.map.MapView;
import com.example.androiddemo.R;
import com.example.androiddemo.listener.OnLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

public class GaojinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private ArrayList<String> ary;
    private final static int TYPE_CONTENT=0;//正常内容
    private final static int TYPE_FOOTER=1;//加载View

    @Override
    public int getItemViewType(int position) {
        if (position==ary.size()){
            return TYPE_FOOTER;//判断滑到最底部时，返回的ViewType为TYPE_CONTENT。
        }
        return TYPE_CONTENT;
    }

//    public GaojinAdapter(Context context, ArrayList<String> ary,int updates) {
//        this.context=context;
//        this.ary=ary;
//        this.updates=updates;
//    }

    public GaojinAdapter(Context context, ArrayList<String> ary) {
        this.context=context;
        this.ary=ary;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_FOOTER){
            View view=LayoutInflater.from(context).inflate(R.layout.foot_view_activity,parent,false);
            return new FootViewHolder(view);
        }else{
            View view= LayoutInflater.from(context).inflate(R.layout.gaojin_recyclerview, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ary.size()+1;
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        ContentLoadingProgressBar progressBar;
        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.pb_progress);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.gaojin1)
        TextView gaojin1;
        @BindView(R.id.gaojin2)
        TextView gaojin2;
        @BindView(R.id.gaojin3)
        TextView gaojin3;
        @BindView(R.id.gaojin4)
        TextView gaojin4;
        @BindView(R.id.gaojin5)
        TextView gaojin5;
        @BindView(R.id.gaojin_btn1)
        Button gaojin_btn1;
        @BindView(R.id.gaojin_btn2)
        Button gaojin_btn2;
        @BindView(R.id.gaojin_btn3)
        Button gaojin_btn3;
        @BindView(R.id.gaojin_map)
        MapView gaojin_map;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gaojin1=itemView.findViewById(R.id.gaojin1);
            gaojin2=itemView.findViewById(R.id.gaojin2);
            gaojin3=itemView.findViewById(R.id.gaojin3);
            gaojin4=itemView.findViewById(R.id.gaojin4);
            gaojin5=itemView.findViewById(R.id.gaojin5);
            gaojin_btn1=itemView.findViewById(R.id.gaojin_btn1);
            gaojin_btn2=itemView.findViewById(R.id.gaojin_btn2);
            gaojin_btn3=itemView.findViewById(R.id.gaojin_btn3);
            gaojin_map=itemView.findViewById(R.id.gaojin_map);
            gaojin_btn1.setOnClickListener(this);
            gaojin_btn2.setOnClickListener(this);
            gaojin_btn3.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.gaojin_btn1:
//                    onItemClickListener.onItemClick();
                    if (gaojin_map.getVisibility()== View.GONE){
                        gaojin_map.setVisibility(View.VISIBLE);
                    }else {
                        gaojin_map.setVisibility(View.GONE);
                    }
                    break;

                case R.id.gaojin_btn2:
                    break;

                case R.id.gaojin_btn3:
                    break;
            }
        }

    }
}
