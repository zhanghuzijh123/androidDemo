package com.example.androiddemo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddemo.R;
import com.example.androiddemo.model.RecyclerNewView_ex;

import java.util.List;

public class RecyclerViewNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<RecyclerNewView_ex> mdata;
    private OnItemClickListener mOnItemClickListener;
    public static final int TYPE_NORMAL=0;
    public static final int TYPE_MORE=1;

    public RecyclerViewNewAdapter(List<RecyclerNewView_ex> data) {
        this.mdata=data;
    }

    //    该方法用于创建条目的view
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.item_recyclerview_new_activity,null);
        return new InnerHolder(view);
    }

    //    该方法用于绑定holder，一般用来设置数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((InnerHolder)holder).setData(mdata.get(position),position);
    }

    //    返回条目的个数
    @Override
    public int getItemCount() {
        if (mdata!=null){
            return 10;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position<=10){
            return TYPE_NORMAL;
        }
        return TYPE_MORE;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener=listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView recyclerNewView_tx1;
        private TextView recyclerNewView_tx2;
        private int mPosition;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
//            找到view中的控件
            recyclerNewView_tx1=(TextView) itemView.findViewById(R.id.recyclerNewView_tx1);
            recyclerNewView_tx2=(TextView) itemView.findViewById(R.id.recyclerNewView_tx2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(mPosition);
                    }
                }
            });
        }

        public void setData(RecyclerNewView_ex recyclerNewView_ex,int position) {
            this.mPosition=position;

            recyclerNewView_tx1.setText(recyclerNewView_ex.num);
            recyclerNewView_tx2.setText(recyclerNewView_ex.title);
        }
    }
}
