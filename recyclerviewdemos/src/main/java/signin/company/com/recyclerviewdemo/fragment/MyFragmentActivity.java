package signin.company.com.recyclerviewdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import signin.company.com.recyclerviewdemo.R;

public class MyFragmentActivity extends AppCompatActivity {
    LinearLayout id_ll_container;
    public static void newInstance(Context context){
        context.startActivity(new Intent(context,MyFragmentActivity.class));
    }
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        id_ll_container = (LinearLayout) findViewById(R.id.id_ll_container);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        MyFragment fragment0 = MyFragment.newInstance("0","") ;
        MyFragment fragment1 = MyFragment.newInstance("1","") ;
        MyFragment fragment2 = MyFragment.newInstance("2","") ;
//        ft.add( R.id.id_ll_container,fragment);
        addNewFragment(id_ll_container,fragment0, R.id.view_tag_id0,false);
        addNewFragment(id_ll_container,fragment1, R.id.view_tag_id1,false);
        addNewFragment(id_ll_container,fragment2, R.id.view_tag_id2,true);

//        fm.beginTransaction().add( fragment1,fragment1.getClass().getName()).commit();
//        fm.beginTransaction().add( fragment2,fragment2.getClass().getName()).commit();
    }

    public void addNewFragment(LinearLayout container,MyFragment myFragment,int id,boolean isLast) {
        FrameLayout frameLayout = new FrameLayout(this);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        frameLayout.setLayoutParams(ll);
        frameLayout.setId(id);
        container.addView(frameLayout);
        ft.add(id,myFragment);
        if (isLast) {
            ft.commit();
        }
    }

}
