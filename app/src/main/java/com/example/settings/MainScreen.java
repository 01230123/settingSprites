package com.example.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainScreen extends View {
    private SettingIconSprite settingIconSprite;
    private SettingMenuSprite settingMenuSprite;
    private TypeSwitchSprite typeSwitchSprite;

    private int screenWidth, screenHeight;

    private float xDown, yDown;
    private float singleClickArea = 200;

    public MainScreen(Context context, int width, int height) {
        super(context);
        this.screenWidth = width;
        this.screenHeight = height;

        createSettingIcon();
        createSettingMenu();
        createSwitchTypeButton();
        resetDownClickPosition();
    }

    private void createSettingIcon() {
        int length = screenWidth / 6;
        settingIconSprite = new SettingIconSprite(
                getContext(),
                0,
                screenWidth - length,
                length
        );

        settingIconSprite.addBmp(new int[] {
                R.drawable.setting_icon
        });
    }

    private void createSettingMenu() {
        int width = (int)(screenWidth / 1.2);
        int height = screenHeight - settingIconSprite.getLength() * 2;
        settingMenuSprite = new SettingMenuSprite(
                getContext(),
                (float)(screenHeight - height) / 2,
                (float)(screenWidth - width) / 2,
                width, height
        );

        settingMenuSprite.setMenuBmp(R.drawable.menu_background);
        settingMenuSprite.setMenuTextBmp(new int[] {
                R.drawable.setting_text
        });
        settingMenuSprite.setModeButtonBmp(new int[]{
                R.drawable.mode_1,
                R.drawable.mode_2
        });
        settingMenuSprite.setSoundButtonBmp(new int[]{
                R.drawable.sound_1,
                R.drawable.sound_2
        });
        settingMenuSprite.setCreditButtonBmp(new int[]{
                R.drawable.credit_1
        });
    }

    private void createSwitchTypeButton() {
        typeSwitchSprite = new TypeSwitchSprite(
                getContext(),
                screenWidth,
                screenHeight,
                2
        );

        typeSwitchSprite.addBmp(new int[]{
                R.drawable.type_0,
                R.drawable.type_1,
                R.drawable.type_2
        });

        typeSwitchSprite.addTypeIcon(new int[]{
                R.drawable.type_icon_1_1, R.drawable.type_icon_1_2,
                R.drawable.type_icon_2_1, R.drawable.type_icon_2_2
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        typeSwitchSprite.draw(canvas);
        settingIconSprite.draw(canvas);
        if (settingIconSprite.isTurnedOn)
        {
            settingMenuSprite.draw(canvas);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int eventAction = event.getActionMasked();
        Log.d("@@@@", "Action: " + eventAction);
        switch (eventAction)
        {
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                handleTypeSelect();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isSingleClick(x, y))
                    resetDownClickPosition();
                break;
            case MotionEvent.ACTION_UP:
                if (isSingleClick(x, y))
                    handleSingleClick(x, y);
                else
                {
                    typeSwitchSprite.update(0);
                    invalidate();
                }
                break;
        }
        return true;
    }

    private void resetDownClickPosition() {
        xDown = -1000;
        yDown = -1000;
    }

    private void handleTypeSelect() {
        if (typeSwitchSprite.isSelected(xDown, yDown))
        {
            typeSwitchSprite.handleSelected(xDown, yDown);
            invalidate();
        }
    }

    private void handleSingleClick(float x, float y) {
        if (settingIconSprite.isTurnedOn)
            handleSettingMenuClick(x, y);
        else if (settingIconSprite.isSelected(x, y))
            settingIconSprite.isTurnedOn = true;
        else if (typeSwitchSprite.isSelected(x, y))
            typeSwitchSprite.handleButtonClick(x, y);
        else
            return;
        invalidate();
    }

    private void handleSettingMenuClick(float x, float y) {
        if (!settingMenuSprite.isSelected(x, y))
            settingIconSprite.isTurnedOn = false;
        else
            settingMenuSprite.handleButtonsClick(x, y);
    }

    private boolean isSingleClick(float x, float y) {
        return Math.abs(x - xDown) <= singleClickArea && Math.abs(y - yDown) <= singleClickArea;
    }
}
