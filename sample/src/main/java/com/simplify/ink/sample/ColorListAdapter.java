package com.simplify.ink.sample;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class ColorListAdapter implements ListAdapter {
    private ArrayList<ColorItem> ColorItemList = new ArrayList<ColorItem>();

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return ColorItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return ColorItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater infl = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infl.inflate(R.layout.colorgrid, parent, false);
        }
        ColorItem ci = ColorItemList.get(position);
        ((ImageView) (convertView.findViewById(R.id.colorItem))).setBackgroundColor(Color.parseColor(ci.getColorString()));
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return ColorItemList.size() == 0;
    }

    public void addItem(String Color) {
        ColorItem cl = new ColorItem();
        cl.setColorString(Color);
        ColorItemList.add(cl);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
