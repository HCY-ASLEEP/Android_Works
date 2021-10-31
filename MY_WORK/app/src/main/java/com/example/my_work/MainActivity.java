package com.example.my_work;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.my_work.ui.main.SectionsPagerAdapter;
import com.example.my_work.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //使用 binding 需要在 build.gradle 文件里面注册一下;
    private ActivityMainBinding binding;
    private EditText editText;
    private Button button_in_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //这里的思路是先将碎片装到 viewPagerAdapter 里面,然后使得 viewPager 使用 adapter,然后将 tabLayout 与 viewPager 挂钩;

        //首先将 PagerAdapter 与 viewPager 相关联，这样子 fragment 就可以在 viewPager 上面切换啦;
        // viewPager 是一个容器，上面可以放置 fragment;
        // PagerAdapter 将 fragment 适配到 viewPager 上面，PagerAdapter 管理了 fragment 在 viewPager 上面的切换;
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        //将 tabLayout 和 viewPager 相关联起来，这样子就可以达到每当 viewPager 里面呈现出某一个 fragment 的时候，tabLayout 上面就可以展示这个 fragment 相对应的 tab;
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        //这里设置了一个简单文本输入框，也就是 editText ;
        //在 editText 后面还有一个按钮，如果这个 editText 里面有东西，就用 toast 显示输入内容，如果没有的话，就打开外部链接;
        editText=binding.edittextInToolbar;
        button_in_toolbar=binding.goButtonInToolbar;
        button_in_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText=editText.getText().toString();
                if(!inputText.isEmpty()){
                    Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
                }
                else{
                    //打开系统自带浏览器的 intent;
                    //TODO: 显示 intent 选择列表;
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.csdn.net"));
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"No input!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //这是位于 toolbar 最前面的 textView ，我也为这个 textView 设置了点击事件;
        //我设置了两个点击事件，一个是长按出菜单，一个是点击就立即出菜单，两个出现的菜单是不一样的;
        //接下来这一个是短按出菜单,用的是 setOnClickListener 方法;
        //在里面的 onClick 方法里面 设置点击之后的事件，点击之后的事件为 showPopupMenu 方法，这个方法是我自己定义的方法，用于展示 PopupMenu;
        //PopupMenu 是一种可以在指定的 view 周围弹出菜单的 Menu;
        final TextView textView_toolbar=binding.textviewToolbar;
        textView_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(textView_toolbar);
            }
        });

        //这一句话为 toolbar 最前端的 textView 设置长按点击事件;
        registerForContextMenu(textView_toolbar);
    }





    //下面这些函数都是定义在 onCreate 之外的方法;
    //这一段代码是自定义的展示 PopupMenu 的方法;
    private void showPopupMenu(View view){
        //首先实例化一个 PopupMenu;
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.my_menu,popupMenu.getMenu());

        //然后设置菜单里面每一个菜单子项的点击事件，特定的菜单子项可以通过 menuItem 的 getItemId() 方法去获得 子项的 id;
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(getApplicationContext(),menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //将我们的 popupMenu 展示出来，这一句话特备重要！就和 toast 最后的那一个 show() 一样;
        popupMenu.show();
    }





    //接下来两个函数是定义长按出菜单的点击事件，以及点击事件的处理;
    //首先要创建一个长按菜单;
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_2, menu);
        //或者动态配置，不需要第二步添加的菜单
        //menu.add(0, Menu.FIRST + 1, 1, R.string.menu_send);
        //menu.add(0, Menu.FIRST + 2, 2, R.string.menu_change);
        //menu.add(0, Menu.FIRST + 3, 3, R.string.menu_delete);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override

    //然后定义长按出现的菜单里面的点击事件
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_in_menu2)
        {
            Toast.makeText(MainActivity.this,"You click menu2 just now",Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}