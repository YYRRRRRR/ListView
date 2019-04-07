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


