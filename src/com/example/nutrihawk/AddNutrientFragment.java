package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.LocalDate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNutrientFragment extends Fragment {
	Button mAddNutrientButton;
	Button mFruitButton;
	EditText mNewFood;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Information.get(getActivity()).saveNutrients();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_add_nutrient, parent, false);
		
		mNewFood = (EditText)v.findViewById(R.id.add_nutrient_edit);
		
		mAddNutrientButton = (Button)v.findViewById(R.id.add_nutrient_button);
		mAddNutrientButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String newFood = mNewFood.getText().toString().toUpperCase();
				ArrayList<Nutrient> currentNutrients = Information.get(getActivity()).getNutrients();
				HashMap<String, VitaminSet> vitaminSources = Information.get(getActivity()).getVitaminSources();
				HashMap<String, MineralSet> mineralSources = Information.get(getActivity()).getMineralSources();
				if (!vitaminSources.containsKey(newFood)) {
					Toast.makeText(getActivity(), "Food not found!", Toast.LENGTH_SHORT).show();
				} else {
					VitaminSet foodVitamins = vitaminSources.get(newFood);
					MineralSet foodMinerals = mineralSources.get(newFood);
					LocalDate now = new LocalDate();
					for (Nutrient n : currentNutrients) {
						if (foodVitamins.getVitaminAmount().containsKey(n.toString()) &&
								foodVitamins.getVitaminAmount().get(n.toString()) != 0) {
							n.addAmount(foodVitamins.getVitaminAmount().get(n.toString()), now);
							n.addSource(newFood);
						} else if (foodMinerals.getMineralAmount().containsKey(n.toString().toUpperCase()) &&
								foodMinerals.getMineralAmount().get(n.toString().toUpperCase()) != 0) {
							n.addAmount(foodMinerals.getMineralAmount().get(n.toString().toUpperCase()), now);
							n.addSource(newFood);
						} else {
						}
					}
					Information.get(getActivity()).setNutrients(currentNutrients);
					Toast.makeText(getActivity(), "New Nutrients added!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		mFruitButton = (Button)v.findViewById(R.id.fruits_category_button);
		mFruitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), FoodCategoryActivity.class);
				i.putExtra(FoodCategoryFragment.EXTRA_FOOD_CATEGORY_ID, (String)mFruitButton.getText());
				startActivity(i);
			}
		});
		
		return v;
	}
}
