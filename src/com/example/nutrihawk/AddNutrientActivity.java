package com.example.nutrihawk;

import android.support.v4.app.Fragment;

public class AddNutrientActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new AddNutrientFragment();
	}

}
