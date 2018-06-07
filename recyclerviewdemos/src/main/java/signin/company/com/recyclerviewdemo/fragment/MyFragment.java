package signin.company.com.recyclerviewdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import signin.company.com.recyclerviewdemo.R;

public class MyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        textView = (TextView) view.findViewById(R.id.id_tv);
        textView.setText( mParam1);
//        String str = "这是一个测试字符串这是一个测试字符串这是一个测试字符串这是一个测试字符串这是一个测试字符串";
//        SpannableString spannableString=new SpannableString(str);
////        spannableString.setSpan(colorSpan, 0, start, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        colorSpan=new ColorSpan(Color.WHITE, Color.GRAY);
//
//        spannableString.setSpan(new BackgroundColorSpan(ContextCompat.getColor(getActivity(),R.color.black)), 5, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setText(spannableString);
        return view;
    }


}
