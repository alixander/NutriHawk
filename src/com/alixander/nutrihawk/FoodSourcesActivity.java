package com.alixander.nutrihawk;

import android.support.v4.app.Fragment;

public class FoodSourcesActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new FoodSourcesFragment();
	}

}
