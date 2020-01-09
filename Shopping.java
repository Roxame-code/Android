package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Shopping extends Activity{
    private ListView mListView;
    SQLiteHelper mSQLiteHelper;
    String id;
    TextView Shopping_name,content;
    //商品名称与价格数据集合
    private String[] titles={
            "詹姆斯15全明星","詹姆斯15白黑红","詹姆斯15黑银","詹姆斯15联名","詹姆斯15暗玫瑰","詹姆斯15黑金金","詹姆斯15白酒红","詹姆斯15白金","詹姆斯15香缤色","詹姆斯15黑白"
    };
    private  String[] prices={
            "1900元","2500元","3500元","6000元","5000元","3500元","3000元","3900元","4500元","1900元"
    };
    //图片数据集合
    private int[] icons={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j};
    private android.view.LayoutInflater LayoutInflater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping);
        ImageView car=(ImageView) findViewById(R.id.car);
        mListView=(ListView) findViewById(R.id.lv);//初始化ListView控件
        MybaseAdapter mAdapter =new MybaseAdapter();//
        mSQLiteHelper = new SQLiteHelper(this);
        mListView.setAdapter(mAdapter);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Shopping.this, Shoppinglist.class);
                startActivityForResult(intent,1);
            }
        });
        initData();
    }
    protected void initData(){
//        mSQLiteHelper=new SQLiteHelper(this);
//        Shopping_name.setText("购物车");
}
    class MybaseAdapter extends BaseAdapter {
            @Override
            public int getCount() { //获取item的总数
                return titles.length;//返回ListView Item条目的总数
            }
            @Override
            public Object getItem(int position) {
                return titles[position];//返回Item的数据对象
            }
            @Override
            public long getItemId(int position) {
                return position; //返回Item的id
            }
            //得到Item的View视图
            class ViewHolder {
                TextView title, price;
                ImageView iv;
                Button addshop;

            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = View.inflate(Shopping.this, R.layout.list_item, null);
                    holder = new ViewHolder();
                    holder.title =  convertView.findViewById(R.id.title);
                    holder.price = convertView.findViewById(R.id.price);
                    holder.iv =  convertView.findViewById(R.id.iv);
                    holder.addshop = convertView.findViewById(R.id.addshop);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.addshop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean n = mSQLiteHelper.insertData(titles[position],prices[position],icons[position]);
                        if(n){
                            Toast.makeText(Shopping.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Shopping.this,"加入购物车失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                holder.title.setText(titles[position]);
                holder.price.setText(prices[position]);
                holder.iv.setBackgroundResource(icons[position]);
                return convertView;
            }
        }
    }
