package com.example.nutrihawk;

import android.support.v4.app.Fragment;

public class HomeActivity extends SingleFragmentActivity {
	
	@Override
	protected Fragment CreateFragment() {
		return new HomeFragment();
	}
}
