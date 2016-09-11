package com.simplify.ink.sample;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.simplify.ink.InkView;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.Selector;
import java.util.Random;

public class MainActivity extends Activity
{
    InkView ink;
    GridView colorSelector, thicknessSelector;
    ColorListAdapter cla;
    ThicknessListAdapter tla;
    LinearLayout drawBg, eraserBg, thicknessBg;

    private int bgColor, thicknessRatio=6, currentThickness;
    private String[] ColorIds = {"#ffff0000", "#ffff5e00", "#ffffbb00", "#ff1ddb16", "#ff0100ff", "#ff000000", "#fff15f5f", "#fff29661", "#fff2cb61", "#ff86e57f", "#ff6b66ff", "#ffffffff",
            "#ffcc3d3d", "#ffcc723d", "#ffcca63d", "#ff47c83e", "#ff4641d9", "#ff8c8c8c", "#ff980000", "#ff993800", "#ff997000", "#ff2f9d27", "#ff050099", "#ff5d5d5d",
            "#ff670000", "#ff662500", "#ff664b00", "#ff22741c", "#ff030066", "#ff353535"};
    private float[] Thicknesses = {1.5f,3f,4.5f,6f,7.5f,9f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tla = new ThicknessListAdapter();
        cla = new ColorListAdapter();

        ink = (InkView) findViewById(R.id.ink);
        ink.setColor(Color.argb(255,0,0,0));
        ink.setMinStrokeWidth(1.5f);
        ink.setMaxStrokeWidth(6f);

        drawBg = (LinearLayout) findViewById(R.id.lineBtnBackground);
        eraserBg = (LinearLayout) findViewById(R.id.eraserBtnBackground);
        thicknessBg = (LinearLayout) findViewById(R.id.thicknessBtnBackground);
        bgColor = Color.argb(255,163,95,58);

        colorSelector = (GridView)findViewById(R.id.colorSelector);
        colorSelector.setAdapter(cla);

        thicknessSelector = (GridView)findViewById(R.id.thicknessSelector);
        thicknessSelector.setAdapter(tla);

        for(String str : ColorIds)
            cla.addItem(str);

        for(float thick : Thicknesses)
            tla.addItem(thick*thicknessRatio);

        colorSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ink.setColor(Color.parseColor(ColorIds[position]));
                colorSelector.setVisibility(View.GONE);
            }
        });

        thicknessSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentThickness = i;
                ink.setMinStrokeWidth(Thicknesses[i]*thicknessRatio/2);
                ink.setMaxStrokeWidth(Thicknesses[i]*thicknessRatio);
                thicknessSelector.setVisibility(View.GONE);
            }
        });
    }

    public void lineBtnClick(View view) {
        colorSelector.setVisibility(View.VISIBLE);
        thicknessSelector.setVisibility(View.GONE);
        ink.setEraserOff();
        drawBg.setBackgroundColor(bgColor);
        eraserBg.setBackgroundColor(0);
        thicknessBg.setBackgroundColor(0);
    }

    public void eraserBtnClick(View view) {
        ink.setMinStrokeWidth(15f);
        ink.setMaxStrokeWidth(15f);
        ink.setEraserOn();
        drawBg.setBackgroundColor(0);
        eraserBg.setBackgroundColor(bgColor);
        thicknessBg.setBackgroundColor(0);
    }

    public void thicknessBtnClick(View view) {
        colorSelector.setVisibility(View.GONE);
        thicknessSelector.setVisibility(View.VISIBLE);
        ink.setEraserOff();
        drawBg.setBackgroundColor(bgColor);
        eraserBg.setBackgroundColor(0);
        thicknessBg.setBackgroundColor(0);
    }

    public void ClearBtnClick(View view) {
        ink.clear();
    }
    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        return bmOverlay;
    }
}
