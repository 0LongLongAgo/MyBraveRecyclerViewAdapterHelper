package signin.company.com.recyclerviewdemo.simpleuse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import signin.company.com.recyclerviewdemo.R;

public class StaggeredLayoutManagerRecyclerview extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private TestAdapter mAdapter;

    public static void newInstance(Context context){
        context.startActivity(new Intent(context,StaggeredLayoutManagerRecyclerview.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        RoundRectDecoration dividerItemDecoration = new RoundRectDecoration(StaggeredLayoutManagerRecyclerview.this, RoundRectDecoration.VERTICAL);
//        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(StaggeredLayoutManagerRecyclerview.this,R.drawable.shape_for_divider));
//        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(StaggeredLayoutManagerRecyclerview.this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
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
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    StaggeredLayoutManagerRecyclerview.this).inflate(R.layout.item_text, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {

            holder.tv.setText(mDatas.get(position));
            int height =150+30*position%100;

            FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
            holder.tv.setLayoutParams(ll);
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
