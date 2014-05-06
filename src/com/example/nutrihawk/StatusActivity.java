package com.example.nutrihawk;

import android.support.v4.app.Fragment;

public class StatusActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new StatusFragment();
	}

}
