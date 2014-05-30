package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.LocalDate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FoodCategoryFragment extends Fragment implements OnClickListener{
	public static final String EXTRA_FOOD_CATEGORY_ID = "com.example.nutrihawk.food_category_id";
	private String foodCategoryName;
	private HashMap<String, VitaminSet> vitaminSources;
	private HashMap<String, MineralSet> mineralSources;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		foodCategoryName = getActivity().getIntent().getStringExtra(EXTRA_FOOD_CATEGORY_ID).toUpperCase();
		vitaminSources = Information.get(getActivity()).getVitaminSources();
		mineralSources = Information.get(getActivity()).getMineralSources();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_food_category, parent, false);
		
		TextView foodCategory = (TextView)v.findViewById(R.id.food_category_name);
		foodCategory.setText(foodCategoryName);
		
		if (foodCategoryName.equals("FRUITS")) {
			Button appleButton = (Button)v.findViewById(R.id.food_apple_button);
			appleButton.setOnClickListener(this);
		}
		
		
		
		return v;
	}
	
@Override
public void onClick(View v) {
	String newFood = "nothing";
	switch (v.getId()) {
		case R.id.food_apple_button:
			newFood = "APPLE";
      	break;
	}
	 
	VitaminSet foodVitamins = vitaminSources.get(newFood);
	MineralSet foodMinerals = mineralSources.get(newFood);
	LocalDate now = new LocalDate();
	 
	ArrayList<Nutrient> currentNutrients = Information.get(getActivity()).getNutrients();
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
