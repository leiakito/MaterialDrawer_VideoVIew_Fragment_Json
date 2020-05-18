package com.leia.material;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leia.util.ResultBean;
import com.leia.util.Sqlite;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListViewAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    Sqlite sqlite;
    SQLiteDatabase db;
    List<ResultBean> datas;
    RecyclerView recyclerView;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = view.findViewById(R.id.video_list_recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //准备数据
        initData();
        //设置Recyclerview控件对象呈现方式为Listview
        showListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        showListView();
    }

    private void initData() {
        //创建sqlite 对象
        sqlite = new Sqlite(getActivity().getApplicationContext());
       //Sqlitedatabase db对象  获取 sqlite对象的getWriteDatabase()方法
        db=sqlite.getWritableDatabase();
        //对数组进行 实例
        datas=new ArrayList<>();
        //清除残存数据
        datas.clear();
        //new游标对象
        Cursor cursor=db.query("video",null,null,null,null,null,null);
            //游标不等于null 而且长度大于0
            if (cursor!=null&&cursor.getCount()>0){
                Log.d("msg+videoFragment","------------------------------------");
                Log.d("msg+videoFragment","开始查询");
                while (cursor.moveToNext()){
                    String videoname=cursor.getString(cursor.getColumnIndex("videoname"));
                    String videourl = cursor.getString(cursor.getColumnIndex("videourl"));
                    String img = cursor.getString(cursor.getColumnIndex("imageurl"));
                    String info=cursor.getString(cursor.getColumnIndex("info"));
                    ResultBean resultBean=new ResultBean(img,videoname,videourl,info.trim());
                    datas.add(resultBean);
                }
                Log.d("msg+videoFragment","------------------------------------");
                Log.d("msg",datas.toString());
            }
            db.close();
    }

    private void showListView() {
        //recycler管理样式 LineayLayoutManager
        LinearLayoutManager manager=new LinearLayoutManager(getActivity().getApplicationContext());
        //水平还是垂直
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置正反方向
        manager.setReverseLayout(false);
        //给recyclerView对象 设置布局管理器
        recyclerView.setLayoutManager(manager);
        //创建适配器
        ListViewAdapter adapter=new ListViewAdapter(getActivity().getApplicationContext(),datas);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, ResultBean datas) {
                Toast.makeText(getActivity().getApplicationContext(), "点击了我"+recyclerView.getChildAdapterPosition(view), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity().getApplicationContext(),VideoActivity.class);

                            intent.putExtra("url",datas.getVideourl());
                            intent.putExtra("videoname",datas.getVideoname());
                            intent.putExtra("videoinfo",datas.getInfo());
                            intent.putExtra("imageurl",datas.getImageurl());

                        startActivity(intent);
            }
        });
    }
}
