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
    private List<MyItem> myItemList;
    private Context context;

    public MyItemAdapter(Context context,List<MyItem> myItemList){
        this.context=context;
        this.myItemList=myItemList;
    }
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=View.inflate(context,R.layout.my_item_layout,null);
       final ViewHolder holder=new ViewHolder(view);
       holder.myItemImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int position=holder.getAdapterPosition();
               MyItem myItem=myItemList.get(position);
               Toast.makeText(v.getContext(),"you click on me "+myItem.getName(),Toast.LENGTH_SHORT).show();
           }
       });

       holder.myItemTextView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
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
               context.startActivity(intent);
           }
       });
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemAdapter.ViewHolder holder, int position) {
        MyItem myItem=myItemList.get(position);
        holder.myItemImage.setImageResource(myItem.getImageId());
        holder.myItemTextView.setText(myItem.getName());
    }

    @Override
    public int getItemCount() {
        return myItemList.size();
    }

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
