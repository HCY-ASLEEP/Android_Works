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

    private ActivityMainBinding binding;
    private EditText editText;
    private Button button_in_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


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
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.csdn.net"));
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"No input!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        final TextView textView_toolbar=binding.textviewToolbar;
        textView_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(textView_toolbar);
            }
        });

        registerForContextMenu(textView_toolbar);
    }

    private void showPopupMenu(View view){
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.my_menu,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(getApplicationContext(),menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        popupMenu.show();
    }



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

    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_in_menu2)
        {
            Toast.makeText(MainActivity.this,"You click menu2 just now",Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}