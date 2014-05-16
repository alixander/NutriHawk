package com.example.nutrihawk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FoodCategoryFragment extends Fragment {
	public static final String EXTRA_FOOD_CATEGORY_ID = "com.example.nutrihawk.food_category_id";
	private String foodCategoryName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		foodCategoryName = getActivity().getIntent().getStringExtra(EXTRA_FOOD_CATEGORY_ID);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_food_category, parent, false);
		
		TextView foodCategory = (TextView)v.findViewById(R.id.food_category_name);
		foodCategory.setText(foodCategoryName);
		
		return v;
	}
}
