package com.leia.material;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {
    private Drawer result;
    private PrimaryDrawerItem item1;
    private Class fragmentclass;
    private Fragment fragment = null;
    private SecondaryDrawerItem item2;
    private Class videofragment;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
       if (event.getKeyCode()==KeyEvent.KEYCODE_BACK){
           return true;
       }else {
           return super.dispatchKeyEvent(event);
       }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //load default fragment
//        fragmentManager=getSupportFragmentManager();
//        fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_home,new HomeFragment());
        //ToolBar 控件 查找 设置属性
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.color.colorAccent));
        toolbar.setTitle("video list");
        setSupportActionBar(toolbar);
        //头部Header 对象 背景 设置参数 设置头部监听事件
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.head)
                .addProfiles(
                        new ProfileDrawerItem().withName("leia").withEmail("leiakitto@forxmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.head))
                )
                //头部监听 当头部点击时
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                //构建
                .build();
        //PrimaryDrawerItem item相当于 一个独立的item
        item1 = new PrimaryDrawerItem().withName("Home");
        item2 = new SecondaryDrawerItem().withName("VideoList").withIcon(R.drawable.video);
        //DrawerBuilder 抽屉构建者 设置toolbar对象 设置AccountHeader头部,设置粘性底部items 添加抽屉items
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(header)
                .withToolbar(toolbar)
                .addStickyDrawerItems(new PrimaryDrawerItem().withName("About leia"))
                //尝试添加Draweritems抽屉items对象 参数为 新建的item1-PrimaryDrawerItem 与item2-SecondaryDrawerItem
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem().withIdentifier(100),
                        item2,
                        new SecondaryDrawerItem().withIdentifier(200)
                )
                //单击抽屉item监听事件
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                Toast.makeText(MainActivity.this, "我是Home", Toast.LENGTH_SHORT).show();
                                fragmentclass = HomeFragment.class;
                                result.closeDrawer();
                                try {
                                    fragment = (Fragment) fragmentclass.newInstance();
                                    FragmentManager manager = getSupportFragmentManager();
                                    manager.beginTransaction().replace(R.id.filContent, fragment).commit();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
//                                Toast.makeText(MainActivity.this, "我是Videolist", Toast.LENGTH_SHORT).show();
                                videofragment = VideoFragment.class;
                                result.closeDrawer();
                                try {
                                    fragment = (Fragment) videofragment.newInstance();
                                    FragmentManager manager = getSupportFragmentManager();
                                    manager.beginTransaction().replace(R.id.filContent, fragment).commit();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                }
                                break;

                        }
//                        //碎片管理器 实例对象 获取 碎片管理器  getSupportFragmentManager();
//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        //管理器 开始交易 移植 参数为 1---容器 存放Fragment的容器 2----为 fragment.class 最后提交
//                        fragmentManager.beginTransaction().replace(R.id.filContent, fragment).commit();
                        return true;
                    }
                })
                .build();

        fragmentclass = HomeFragment.class;
        result.closeDrawer();
        try {
            fragment = (Fragment) fragmentclass.newInstance();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.filContent, fragment).commit();
            //构建
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //重写返回 onPressed() 防止抽屉打开时 返回键直接退出项目
    @Override
    public void onBackPressed() {
        //如果 DrawerBuilder构建对象 打开时候 则返回按键关闭 Drawer构建抽屉
        if (result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            //反之则退出项目
            super.onBackPressed();
        }
    }
}
