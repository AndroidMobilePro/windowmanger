package com.proteam.codingwindowmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class SmartEditText extends EditText {
    private OnRemoveFocusListerner removefocusListerner;

    public void setOnRemoveFocusListener(OnRemoveFocusListerner listener) {
        this.removefocusListerner = listener;
    }

    public SmartEditText(Context context) {
        super(context);
    }

    public SmartEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            removefocusListerner.removeFocus();
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public interface OnRemoveFocusListerner {
        void removeFocus();
    }
}