package signin.company.com.recyclerviewdemo.baserecyclerviewadapterhelper.pulltorefresh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import signin.company.com.recyclerviewdemo.R;
import signin.company.com.recyclerviewdemo.baserecyclerviewadapterhelper.pulltorefresh.loadmore.CustomLoadMoreView;
import signin.company.com.recyclerviewdemo.data.DataServer;

public class PullToRefreshAndPushDownloadActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    public static void newInstance(Activity activity) {
        Intent intent = new Intent(activity,PullToRefreshAndPushDownloadActivity.class);
//        intent.putExtra("balance", balance);
//        intent.putExtra("mCurrentWithDrawType", mCurrentWithDrawType);
//        intent.putExtra("mCurrentWithDrawPayType", mCurrentWithDrawPayType);
        activity.startActivity(intent);
    }
    private RecyclerView mRecyclerView;
    private PullToRefreshAdapter pullToRefreshAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mLoadMoreEndGone = false;
    private static final int TOTAL_COUNTER = 18;
    private static final int PAGE_SIZE = 6;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;//数据条目计数器
    private boolean isErr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_and_push_download);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        addHeadView();
    }

    @Override
    public void onRefresh() {
        pullToRefreshAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
                pullToRefreshAdapter.setEnableLoadMore(true);
            }
        }, delayMillis);
    }
    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        headView.findViewById(R.id.iv).setVisibility(View.GONE);
        ((TextView) headView.findViewById(R.id.tv)).setText("change load view");
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadMoreEndGone = true;
                pullToRefreshAdapter.setLoadMoreView(new CustomLoadMoreView());
                mRecyclerView.setAdapter(pullToRefreshAdapter);
                Toast.makeText(PullToRefreshAndPushDownloadActivity.this, "change complete", Toast.LENGTH_LONG).show();
            }
        });
        pullToRefreshAdapter.addHeaderView(headView);
    }
    private void initAdapter() {
        pullToRefreshAdapter = new PullToRefreshAdapter();
        pullToRefreshAdapter.getData().clear();
        pullToRefreshAdapter.setOnLoadMoreListener(this, mRecyclerView);
        pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        View emptyView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        pullToRefreshAdapter.setEmptyView(emptyView);
//        pullToRefreshAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(pullToRefreshAdapter);
        mCurrentCounter = pullToRefreshAdapter.getData().size();
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(PullToRefreshAndPushDownloadActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        if (pullToRefreshAdapter.getData().size() < PAGE_SIZE) {//已经没有更多数据
            pullToRefreshAdapter.loadMoreEnd(true);
        } else {
//            if (mCurrentCounter >= TOTAL_COUNTER) {//总条目
////                    pullToRefreshAdapter.loadMoreEnd();//default visible
//                //是否显示加载
//                pullToRefreshAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
//            } else {
//                if (isErr) {//错误样式出现过
//                    pullToRefreshAdapter.setEnableLoadMore(true);
                    mRecyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pullToRefreshAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
//                            mCurrentCounter = pullToRefreshAdapter.getData().size();
                            pullToRefreshAdapter.loadMoreComplete();
                            mSwipeRefreshLayout.setEnabled(true);
                        }
                    },2000);
//
//                } else {
//                    isErr = true;
//                    Toast.makeText(PullToRefreshAndPushDownloadActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
//                    pullToRefreshAdapter.loadMoreFail();
//
//                }
            }

//        }
    }
}
