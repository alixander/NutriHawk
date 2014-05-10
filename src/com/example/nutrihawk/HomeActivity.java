package com.example.nutrihawk;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import android.content.Context;
import android.support.v4.app.Fragment;

public class HomeActivity extends SingleFragmentActivity {
	
	@Override
	protected Fragment createFragment() {
		return new HomeFragment();
	}
}
