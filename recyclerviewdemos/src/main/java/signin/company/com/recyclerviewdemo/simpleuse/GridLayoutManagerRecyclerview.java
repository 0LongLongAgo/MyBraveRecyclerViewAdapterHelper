package signin.company.com.recyclerviewdemo.simpleuse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import signin.company.com.recyclerviewdemo.R;

public class GridLayoutManagerRecyclerview extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private TestAdapter mAdapter;
    GridLayoutManager gridLayoutManager;
    public static void newInstance(Context context){
        context.startActivity(new Intent(context,GridLayoutManagerRecyclerview.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        //添加分割线
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(GridLayoutManagerRecyclerview.this));

        //设置布局管理器
        gridLayoutManager =new GridLayoutManager(this,4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.v("TAG", "position:"+position);
                Log.v("TAG", "position % 5:"+(position % 5));
                if (position % 5 == 0) {
                    return 4;
                }else{
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        mRecyclerView.setAdapter(mAdapter = new TestAdapter());

    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 1; i <60; i++)
        {
            mDatas.add(String.valueOf(i));
        }
    }

    class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder>
    {

        @Override
        public int getItemViewType(int position) {
            if (position % 5 == 0) {
                return 0;
            }else{
                return 1;
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = null;
            if (viewType == 0) {
                holder = new MyViewHolder(LayoutInflater.from(
                        GridLayoutManagerRecyclerview.this).inflate(R.layout.item_text, parent,
                        false));

            } else if (viewType == 1) {
                holder = new MyViewHolder(LayoutInflater.from(
                        GridLayoutManagerRecyclerview.this).inflate(R.layout.item_img, parent,
                        false));
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
//            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

//            TextView tv;
//
            public MyViewHolder(View view)
            {
                super(view);
//                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
