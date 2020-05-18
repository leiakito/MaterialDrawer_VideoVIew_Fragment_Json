package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leia.material.R;
import com.leia.util.ResultBean;

import java.util.List;

/*
    Author:leia
    Write The Code Change The World    
*/public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
    private List<ResultBean> mdatas;
    private Context context;
    private TextView videoname;
    private TextView videoinfo;

    public ListViewAdapter(Context context, List<ResultBean> mdata) {
        this.context=context;
        this.mdatas=mdata;
    }


    //用于创建ViewHolder实例 并把加载的布局载入到构造函数中去 返回ViewHolder实例
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(parent.getContext(), R.layout.videolist,null);
        return new InnerHolder(view);
    }

    //用于对子项的数据进行赋值 ,每个子项被滚动到屏幕前执行 绑定数据
    //postion获取当前bean对象实例
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
            holder.setData(mdatas.get(position));
            ResultBean bean=mdatas.get(position);
        Glide.with(holder.itemView.getContext()).load(bean.getImageurl()).into(holder.icon);
    }
    //返回RecyclerView子条目个数
    @Override
    public int getItemCount() {
        if (mdatas!=null){
            return mdatas.size();
        }
        return 0;
    }
    //再其InnerHolder里面为其查找控件对其findbyid
    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            videoname=itemView.findViewById(R.id.video_name);
            videoinfo=itemView.findViewById(R.id.video_info);
            icon=itemView.findViewById(R.id.video_url);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClickListener(v,mdatas.get(getLayoutPosition()));
                    }
                }
            });
        }

        public void setData(ResultBean beans) {
                videoname.setText(beans.getVideoname());
                videoinfo.setText(beans.getInfo().trim());
        }
    }
    public interface OnItemClickListener{

        public void onItemClickListener(View view,ResultBean datas);
    }
    private  OnItemClickListener onItemClickListener;
    public  void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
