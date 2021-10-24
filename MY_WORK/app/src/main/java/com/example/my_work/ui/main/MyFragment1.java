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

    public static MyFragment1 newInstance(int index) {
        MyFragment1 fragment = new MyFragment1();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = MyFragment1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initMyItems();
        RecyclerView recyclerView = binding.recyclerView;
        MyItemAdapter myItemAdapter = new MyItemAdapter(getActivity(), myItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(myItemAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initMyItems() {
        for (int i = 0; i < 20; i++) {
            MyItem item = new MyItem("  MyItem"+i , R.mipmap.ic_launcher);
            myItemList.add(item);
        }
    }
}