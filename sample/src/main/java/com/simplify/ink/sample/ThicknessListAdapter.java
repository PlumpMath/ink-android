package com.simplify.ink.sample;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by studioh on 9/11/16.
 */
public class ThicknessListAdapter implements ListAdapter {
    private ArrayList<ThicknessItem> ThicknessItemList = new ArrayList<ThicknessItem>();

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return ThicknessItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return ThicknessItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        if (view == null) {
            LayoutInflater infl = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infl.inflate(R.layout.thicknessgrid, viewGroup, false);
        }
        ThicknessItem ti = ThicknessItemList.get(i);
        View v = view.findViewById(R.id.penCircle);
        v.setLayoutParams(new LinearLayout.LayoutParams((int)ti.getThickness(), (int)ti.getThickness()));
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return ThicknessItemList.size()==0;
    }

    public void addItem(float thickness)
    {
        ThicknessItem ti = new ThicknessItem();
        ti.setThickness(thickness);
        ThicknessItemList.add(ti);
    }
}
