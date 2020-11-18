package com.example.settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SettingMenuSprite extends MySprite{
    private int itemWidth;
    private int itemHeight;

    private MySprite menuBmp;
    private MySprite menuTextBmp;
    private MySprite modeButtonBmp;
    private MySprite soundButtonBmp;
    private MySprite creditButtonBmp;

    public SettingMenuSprite(Context context, float top, float left, int width, int height)
    {
        super(context, top, left, width, height);

        itemWidth = (int)(getWidth() / 1.5);
        itemHeight = (int)(getHeight() / 5);
    }

    public void setMenuBmp(int bmpId)
    {
        addBmp(new int[]{bmpId});
//        menuBmp = new MySprite(getContext(), getTop(), getLeft(), getWidth(), getHeight());
//        menuBmp.addBmp(new int[]{bmpId});
    }

    public void setMenuTextBmp(int[] bmpId)
    {
        menuTextBmp = new MenuItemSprite(getContext(), itemWidth, itemHeight);
        menuTextBmp.addBmp(bmpId);
        menuTextBmp.setTop(getTop() + (float)itemHeight / 5);
        menuTextBmp.setLeft(getLeft() + ((float)(getWidth() - menuTextBmp.getWidth()) / 2));
    }

    public void setModeButtonBmp(int[] bmpId)
    {
        modeButtonBmp = new MenuItemSprite(getContext(), itemWidth, itemHeight);
        modeButtonBmp.addBmp(bmpId);
        modeButtonBmp.setTop(getTop() + itemHeight + 2 * (float)itemHeight / 5);
        modeButtonBmp.setLeft(getLeft() + ((float)(getWidth() - modeButtonBmp.getWidth()) / 2));
    }

    public void setSoundButtonBmp(int[] bmpId)
    {
        soundButtonBmp = new MenuItemSprite(getContext(), itemWidth, itemHeight);
        soundButtonBmp.addBmp(bmpId);
        soundButtonBmp.setTop(getTop() + itemHeight * 2 + 3 * (float)itemHeight / 5);
        soundButtonBmp.setLeft(getLeft() + ((float)(getWidth() - soundButtonBmp.getWidth()) / 2));
    }

    public void setCreditButtonBmp(int[] bmpId)
    {
        creditButtonBmp = new MenuItemSprite(getContext(), itemWidth, itemHeight);
        creditButtonBmp.addBmp(bmpId);
        creditButtonBmp.setTop(getTop() + itemHeight * 3 + 4 * (float)itemHeight / 5);
        creditButtonBmp.setLeft(getLeft() + ((float)(getWidth() - creditButtonBmp.getWidth()) / 2));
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawARGB(150, 0, 0, 0);
        super.draw(canvas);
//        menuBmp.draw(canvas);
        menuTextBmp.draw(canvas);
        modeButtonBmp.draw(canvas);
        soundButtonBmp.draw(canvas);
        creditButtonBmp.draw(canvas);
    }

    public int getCurrentMode()
    {
        return modeButtonBmp.getBmpPos();
    }

    public int isMute()
    {
        return (soundButtonBmp.getBmpPos() + 1) % 2;
    }

    public void handleButtonsClick(float x, float y) {
        if (modeButtonBmp.isSelected(x, y))
            modeButtonBmp.update();
        else if (soundButtonBmp.isSelected(x, y))
            soundButtonBmp.update();
    }
}
