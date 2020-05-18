package com.leia.material;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;

import com.leia.util.GetHttp;
import com.leia.util.ResultBean;
import com.leia.util.Sqlite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //view 查找到 layout xml 并setContentI(View 显示 )
        view = View.inflate(this, R.layout.activity_start, null);
        setContentView(view);

        SharedPreferences sp=getSharedPreferences("flag",MODE_PRIVATE);
            Boolean flag=sp.getBoolean("flag",false);
            if (flag==false){
                alphaAnimation();
            }else {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
    }
    private void alphaAnimation() {

        //alpha 对象 参数为 从 0.0f ---1.0f
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        //设置持续时间
        alphaAnimation.setDuration(3000);
        //为view对象设置启动动画 参数为 alphanimation 对象
        view.startAnimation(alphaAnimation);
        //为 animation 设置监听 对象
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            //当动画开始时候 将网络访问json  在工具类中将json解析成String 并且存入sqlite
            @Override
            public void onAnimationStart(Animation animation) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //json解析返回String
                            String result = GetHttp.getJson();
                            //将String 类型 转换为 对象
                            List<ResultBean> jsonBeans = new ArrayList<>();
                            JSONArray jsonArray = new JSONArray(result);
                            ResultBean resultBean = null;

                            Sqlite sqlite = new Sqlite(getApplicationContext());
                            SQLiteDatabase db = sqlite.getReadableDatabase();
                            Log.d("msg", "-----------------------StartActivity-----------------------------");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.optJSONObject(i);

                                String imageurl = jsonObject.optString("imageurl");
                                String videoname = jsonObject.optString("videoname");
                                String videourl = jsonObject.optString("videourl");
                                String info = jsonObject.optString("info");
                                resultBean = new ResultBean(imageurl, videoname, videourl, info);
                                jsonBeans.add(resultBean);
                                Log.d("msg", resultBean.toString().trim());

                                ContentValues values = new ContentValues();

                                values.put("imageurl", imageurl);
                                values.put("videoname", videoname);
                                values.put("videourl", videourl);
                                values.put("info", info);

                                db.insert("video", null, values);
                            }
                            db.close();
                            sqlite.close();
//                             SharedPreferences sp=getSharedPreferences("leia",MODE_PRIVATE);
//                                Editor editor=sp.edit();
//                                editor.putString("result",result.toString().trim());
//                                editor.commit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }).start();
                /**
                 *  SharedPreferences sp=getSharedPreferences("leia",MODE_PRIVATE);
                 * //                                Editor editor=sp.edit();
                 * //                                editor.putString("result",result.toString().trim());
                 * //                                editor.commit();
                 */
                Boolean flag;
                flag=true;
                SharedPreferences sp = getSharedPreferences("flag",MODE_PRIVATE);
                Editor editor=sp.edit();
                editor.putBoolean("flag", flag);
                editor.commit();
            }

            //当动画结束时则跳转到MainActivity中
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
