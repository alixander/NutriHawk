package com.alixander.nutrihawk;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewBoldText extends TextView {
	public static final String FONT_LOCATION = "fonts/mensch-bold.tff";
	private static Typeface sTypeface;
	
	public TextViewBoldText(Context context) {
		super(context);
		init(context);
	}
	
	public TextViewBoldText(Context context, AttributeSet attrs, int defStyle) {
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
				setText("mensch-bold");
			}
			return;
		}
	}
}
