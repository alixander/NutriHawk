package com.example.nutrihawk;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewRobotoThin extends TextView {
	public static final String FONT_LOCATION = "fonts/roboto_thin.tff";
	private static Typeface sTypeface;
	
	public TextViewRobotoThin(Context context) {
		super(context);
		init(context);
	}
	
	public TextViewRobotoThin(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public static Typeface getTypeface(Context context) {
		if (sTypeface == null) {
			sTypeface = Typeface.createFromAsset(context.getAssets(), FONT_LOCATION);
		}
		return sTypeface;
	}
	
	private void init(Context context) {
		if (isInEditMode()) {
			if (TextUtils.isEmpty(getText())) {
				setText("Roboto thin");
			}
			return;
		}
	}
}
