#### 实验三ListView
##### 有参考Android移动开发基础教程，有参考CSDN博客，原文链接（https://blog.csdn.net/fjnu_se/article/details/80827352）
###### 主要思想为实现将一个字符串与一组图片捆绑到ListView上显示的功能
1.先创建好ListView布局
```
main.xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <ListView
       android:id="@+id/mylist"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:listSelector="@color/colorAccent"
        >
    </ListView>
</LinearLayout>
<!--android:listSelector="@color/colorAccent" 当点击到某个item时,item会改变颜色,对应Activity里的点击事件-->
<!--颜色对应的是res/values/colors.xml-->
```
2.创建Item布局 
创建好ListView布局之后,需要创建ListView的Item(条目布局)，每个条目里都有一个图片以及一个应用名称
```
simple_items.xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <ListView
       android:id="@+id/mylist"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:listSelector="@color/colorAccent"
        >
    </ListView>
</LinearLayout>
<!--android:listSelector="@color/colorAccent" 当点击到某个item时,item会改变颜色,对应Activity里的点击事件-->
<!--颜色对应的是res/values/colors.xml-->
```
3.编写界面交互代码
创建好了界面,接下来需要在MainActivity里面编写代码,用于实现ListView中显示的应用图标以及名称。由于需要使用图片资源，在drawable目录下添加了相应的图片资源
```
package com.example.hp.listview;

import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

     private String[] names=new String[]{"Lion","Tiger","Monkey","Dog","Cat","elephant"};
     private int[] image=new int[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog ,R.drawable.cat,R.drawable.elephant};
     @Override
    protected void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE); //设置当前的Activity无Title并且全屏
         setContentView(R.layout.main);  //所在的Activity采用R.layout下的main布局文件进行布局
         final int color1=0xFFC5B5FF;
         final int color2=0xFFFFFFFF;
         //创建一个list集合,list集合的元素是Map
         List<Map<String,Object>> ListItems=new ArrayList<Map<String, Object>>();
         for(int i=0;i<names.length;i++){
             Map<String,Object> listItem=new HashMap<String,Object>();
             listItem.put("header",names[i]);
             listItem.put("images",image[i]);
             ListItems.add(listItem);  //加入list集合
         }
         //创建一个SimpleAdapter,因为在使用ListView时需要进行数据适配

         SimpleAdapter simpleAdapter=new SimpleAdapter(this,ListItems,R.layout.simple_items,new String[]{"header","images"},new int[]{R.id.header,R.id.images});
         final ListView list=(ListView)findViewById(R.id.mylist);
         //为ListView设置Adapter
         list.setAdapter(simpleAdapter);
         list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
             public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                 int flag=0;
                 System.out.println(names[position]+position+"被单击");
                 switch (flag){
                     case 0:
                         view.setSelected(true);
                         CharSequence text=names[position];
                         int duration=Toast.LENGTH_SHORT;
                         Toast toast=Toast.makeText(MainActivity.this,text,duration);
                         toast.show();
                         flag=1;
                         break;
                     case 1:
                         view.setBackgroundColor(color2);
                         view.setSelected(false);
                         CharSequence text1=names[position];
                         int duration1=Toast.LENGTH_SHORT;
                         Toast toast1=Toast.makeText(MainActivity.this,text1,duration1);
                         toast1.show();
                         flag=0;
                         break;
                 }
             }
         });
         list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                 System.out.println(names[position]+"选中");
             }
             public void onNothingSelected(AdapterView<?> parent){

             }

         });

     }

    }

```
代码截图:
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190331160446191.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwOTQ2MDUz,size_16,color_FFFFFF,t_70)
