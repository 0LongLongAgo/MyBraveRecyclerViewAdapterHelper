package signin.company.com.recyclerviewdemo.simpleuse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import signin.company.com.recyclerviewdemo.R;
import signin.company.com.recyclerviewdemo.simpleuse.divider.RoundRectDecoration;

public class LinearLayoutManagerRecyclerview extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private TestAdapter mAdapter;
    public static void newInstance(Context context) {
        context.startActivity(new Intent(context, LinearLayoutManagerRecyclerview.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        RoundRectDecoration dividerItemDecoration = new RoundRectDecoration(LinearLayoutManagerRecyclerview.this, RoundRectDecoration.VERTICAL);
////        dividerItemDecoration.setDrawable(new ColorDrawable(ContextCompat.getColor(LinearLayoutManagerRecyclerview.this,R.color.holo_red_dark)));
//        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(LinearLayoutManagerRecyclerview.this,R.drawable.shape_for_divider));
//        mRecyclerView.addItemDecoration(dividerItemDecoration);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new RoundRectDecoration(LinearLayoutManagerRecyclerview.this, RoundRectDecoration.VERTICAL_LIST ));
//        mRecyclerView.addItemDecoration(new RoundRectDecoration(LinearLayoutManagerRecyclerview.this, RoundRectDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter = new TestAdapter());
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 1; i < 60; i++) {
            mDatas.add(String.valueOf(i));
        }
    }

    class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    LinearLayoutManagerRecyclerview.this).inflate(R.layout.item_text, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
            holder.container.setTag(Integer.valueOf(position+1));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            FrameLayout container;
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                container = (FrameLayout) view;
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
