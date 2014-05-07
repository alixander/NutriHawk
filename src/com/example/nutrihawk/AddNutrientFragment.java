package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNutrientFragment extends Fragment {
	Button mAddNutrientButton;
	EditText mNewFood;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
					for (Nutrient n : currentNutrients) {
						if (foodVitamins.getVitaminAmount().containsKey(n.toString().toUpperCase())) {
							n.addAmount(foodVitamins.getVitaminAmount().get(n.toString().toUpperCase()));
							n.addSource(newFood);
							n.addDatesIntook(new GregorianCalendar());
						} else if (foodMinerals.getMineralAmount().containsKey(n.toString().toUpperCase())) {
							n.addAmount(foodMinerals.getMineralAmount().get(n.toString().toUpperCase()));
							n.addSource(newFood);
							n.addDatesIntook(new GregorianCalendar());
						} else {
						}
					}
					Information.get(getActivity()).setNutrients(currentNutrients);
					Toast.makeText(getActivity(), "New Nutrients added!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		return v;
	}
}
