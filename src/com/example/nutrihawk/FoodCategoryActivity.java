package com.example.nutrihawk;

import android.support.v4.app.Fragment;

public class FoodCategoryActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new FoodCategoryFragment();
	}

}
