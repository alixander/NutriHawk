package com.example.nutrihawk;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class SpecificNutrientActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		UUID nutrientId = (UUID)getIntent().getSerializableExtra(SpecificNutrientFragment.EXTRA_NUTRIENT_ID);
		return SpecificNutrientFragment.newInstance(nutrientId);
	}

}
