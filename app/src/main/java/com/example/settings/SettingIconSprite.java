package com.example.settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SettingIconSprite extends MySprite{
    private int length;
    public boolean isTurnedOn;

    public SettingIconSprite(Context context, float top, float left, int length) {
        super(context, top, left, length, length);
        this.length = length;
        this.isTurnedOn = false;
    }

    public int getLength() {
        return this.length;
    }
}
