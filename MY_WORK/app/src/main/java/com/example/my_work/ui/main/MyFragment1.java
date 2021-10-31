package com.example.my_work.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_work.R;
import com.example.my_work.databinding.FragmentMainBinding;
import com.example.my_work.databinding.MyFragment1Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyFragment1 extends Fragment {

    private List<MyItem> myItemList = new ArrayList<>();
    private MyFragment1Binding binding;

    //这个是自己定义的一个类，这个是方便 PagerAdapter 为 viewPager 创建添加 fragment 方便而自定义的，内容非常简单;
    public static MyFragment1 newInstance(int index) {
        MyFragment1 fragment = new MyFragment1();
        return fragment;
    }

    //碎片的创建;
    //这里我要说一下，碎片的创建和碎片视图的加载是分开的;
    //创建使用 onCreate 方法,而加载视图用 onCreateView 方法;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载 fragment 的视图布局;
        binding = MyFragment1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //这个 fragment 布局里面有一个 recycleView;
        //加载 recycleView 里面每一个子项的数据;
        initMyItems();

        //adapter 总的思路是,先将数据装到 adapter 里面,然后再使得 recycleView 使用 adapter;

        //实例化 recycleView;
        RecyclerView recyclerView = binding.recyclerView;
        //实例化 recycleView 的 adapter,并且将数据装到adapter里面 ，等一下 recycleView 关联 adapter 的时候 adapter 会将每一个 item 数据装进 recycleView 里面;
        //注意这里的传入上下文 context 的方法！直接使用 getActivity() 方法！下面也是一样！
        MyItemAdapter myItemAdapter = new MyItemAdapter(getActivity(), myItemList);
        //这一句非常重要！如果没有它会使 recycleView 无法呈现！这句话说明了 recycleView 的布局,因为 recycleView 还可以支持水平滚动！
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //让 recycleView 使用 adapter,将 recycleView 与 adapter 关联起来;
        recyclerView.setAdapter(myItemAdapter);
        //使得每一个子项之间都有横线分隔开来;
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //初始每一个 item 的数据;
    private void initMyItems() {
        for (int i = 0; i < 20; i++) {
            MyItem item = new MyItem("  MyItem"+i , R.mipmap.ic_launcher);
            myItemList.add(item);
        }
    }
}