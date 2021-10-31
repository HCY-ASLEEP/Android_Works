package com.example.my_work.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_work.MainActivity;
import com.example.my_work.R;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.ViewHolder> {
    //这里的 context 非常重要！有了上下文才可以启动其他活动！
    private List<MyItem> myItemList;
    private Context context;

    // adapter 构造函数;
    public MyItemAdapter(Context context,List<MyItem> myItemList){
        this.context=context;
        this.myItemList=myItemList;
    }

    //ViewHolder 用于缓存每一个 item ,提高视图的加载速度;
    //这是创建 viewHolder;
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       //实例化一个 item 的视图;
       View view=View.inflate(context,R.layout.my_item_layout,null);
       //缓存这一个子项视图;
       final ViewHolder holder=new ViewHolder(view);

       //子项视图里面的点击事件;
       //这是子项视图的短点击事件;
       holder.myItemImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //这里说一下，holder.getAdapterPosition()可以获得当前子项的位置;
               int position=holder.getAdapterPosition();
               MyItem myItem=myItemList.get(position);
               Toast.makeText(v.getContext(),"you click on me "+myItem.getName(),Toast.LENGTH_SHORT).show();
           }
       });

       //这是子项视图的长点击事件;
       //我觉得这个方法因该更加熟悉得去使用！
       holder.myItemTextView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               //在 MainActivity 里面有关于 PopupMenu 的详细解释！
               PopupMenu popupMenu=new PopupMenu(v.getContext(), v);
               popupMenu.getMenuInflater().inflate(R.menu.menu_2,popupMenu.getMenu());
               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem menuItem) {
                       Toast.makeText(context.getApplicationContext(), menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                       return false;
                   }
               });
               popupMenu.show();
               return false;
           }
       });

       Button button=view.findViewById(R.id.button_in_recycleView);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("https://www.csdn.net"));
               //注意这一句话啊！
               //在碎片里面的 startActivity 都是要 context.startActivity(intent) 才可以启动活动;
               context.startActivity(intent);
               //严格来说，只有活动才可以启动活动！因此碎片里面的启动活动是调用它父活动的 startActivity 来启动活动！
           }
       });
       return holder;
    }


    //adapter 将数据赋值给每一个 item;
    @Override
    public void onBindViewHolder(@NonNull MyItemAdapter.ViewHolder holder, int position) {
        MyItem myItem=myItemList.get(position);
        holder.myItemImage.setImageResource(myItem.getImageId());
        holder.myItemTextView.setText(myItem.getName());
    }
    //adapter 不仅负责将每一个 item 装到 recycleView 里面，而且负责给每一个 item 赋予数据！

    //返回 recycleView 里面有多少个 item;
    @Override
    public int getItemCount() {
        return myItemList.size();
    }

    //自定义如何缓存一个 recycleView 里面的 item;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView myItemImage;
        TextView myItemTextView;
        public ViewHolder(View view){
            super(view);
            myItemImage=(ImageView) view.findViewById(R.id.my_item_image);
            myItemTextView=(TextView) view.findViewById(R.id.my_item_name);
        }
    }


}
