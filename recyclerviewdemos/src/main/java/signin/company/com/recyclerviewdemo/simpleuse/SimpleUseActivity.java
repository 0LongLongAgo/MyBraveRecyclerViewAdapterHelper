package signin.company.com.recyclerviewdemo.simpleuse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import signin.company.com.recyclerviewdemo.R;
import signin.company.com.recyclerviewdemo.baserecyclerviewadapterhelper.pulltorefresh.PullToRefreshAndPushDownloadActivity;

public class SimpleUseActivity extends AppCompatActivity implements View.OnClickListener {
    public static void newInstance(Context context){
        context.startActivity(new Intent(context,SimpleUseActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_use);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_bt_LinearLayoutManagerRecyclerview:
                LinearLayoutManagerRecyclerview.newInstance(SimpleUseActivity.this);
                break;
            case R.id.id_bt_GridLayoutManagerRecyclerview:
                GridLayoutManagerRecyclerview.newInstance(SimpleUseActivity.this);
                break;

            case R.id.id_bt_StaggeredLayoutManagerRecyclerview:
                StaggeredLayoutManagerRecyclerview.newInstance(SimpleUseActivity.this);
                break;
            case R.id.id_bt_LinearLayoutManagerTimeLineRecyclerview:
                LinearLayoutManagerTimeLineRecyclerview.newInstance(SimpleUseActivity.this);
                break;
            case R.id.id_bt_PullToRefreshAndPushDownloadActivity:
                PullToRefreshAndPushDownloadActivity.newInstance(SimpleUseActivity.this);
                break;
        }
    }
}
