package com.example.nutrihawk;

import android.support.v4.app.Fragment;

public class SpecificNutrientActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new SpecificNutrientFragment();
	}

}
