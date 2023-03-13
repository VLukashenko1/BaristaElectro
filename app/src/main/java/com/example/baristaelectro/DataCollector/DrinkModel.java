package com.example.baristaelectro.DataCollector;

import android.graphics.drawable.Drawable;

public class DrinkModel {
    public String name;
    public Drawable drawable;
    public boolean checked;

    public DrinkModel(String name, Drawable drawable) {
        this.name = name;
        this.drawable = drawable;
        this.checked = false;
    }

    public DrinkModel(String name, Drawable drawable, boolean checked){
        this.name = name;
        this.drawable = drawable;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        return "DrinkModel{" +
                "name='" + name + '\'' +
                ", drawable=" + drawable +
                ", checked=" + checked +
                '}';
    }
}
