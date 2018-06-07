package signin.company.com.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import signin.company.com.recyclerviewdemo.fragment.MyFragmentActivity;
import signin.company.com.recyclerviewdemo.simpleuse.SimpleUseActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (Button) findViewById(R.id.id_et);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_bt_simpleuse:
                SimpleUseActivity.newInstance(MainActivity.this);
                break;
            case R.id.id_et:
                MyFragmentActivity.newInstance(MainActivity.this);
                break;
        }
    }
}
