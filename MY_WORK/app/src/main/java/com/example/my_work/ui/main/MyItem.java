package com.example.my_work.ui.main;

public class MyItem {
    private String name;
    private int imageId;
    public MyItem(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
