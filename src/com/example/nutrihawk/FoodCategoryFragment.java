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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class FoodCategoryFragment extends Fragment implements OnClickListener{
	public static final String EXTRA_FOOD_CATEGORY_ID = "com.example.nutrihawk.food_category_id";
	private String foodCategoryName;
	private HashMap<String, VitaminSet> vitaminSources;
	private HashMap<String, MineralSet> mineralSources;
	private ArrayList<Button> all_buttons;
	private ArrayList<String> foods;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		all_buttons = new ArrayList<Button>();
		foods = new ArrayList<String>();
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
			populateFruitsButtons(v);
		}
		
		TableLayout table = (TableLayout)v.findViewById(R.id.food_category_table);
		TableRow row = new TableRow(getActivity());
		for (int i = 0; i < all_buttons.size(); i++) {
			if (i % 3 == 0) {
				row = new TableRow(getActivity());
				table.addView(row);
			}
			row.addView(all_buttons.get(i));
		}
		
		return v;
	}
	
	private void populateFruitsButtons(View v) {
		String[] fruits = {"alfalfa", "apple", "avocado", "banana", "blackberries", "blueberries", "boysenberries", "cantaloupe", "cherries", "cranberries", "dates", "grapefruit", "grapes", "kiwi", "lemon", "limes", "lychees", "mangos", "nectarines", "olives", "oranges", "papayas", "passionfruit", "peaches", "pears", "pineapple", "plums", "pomegranates", "raisins", "raspberries", "starfruit", "strawberries", "tomatoes", "watermelon"};
		for (int i = 0; i < fruits.length; i++) {
			foods.add(fruits[i]);
			Button newButton = new Button(getActivity());
			newButton.setText(fruits[i]);
			newButton.setId(i);
			newButton.setOnClickListener(this);
			all_buttons.add(newButton);
		}
	}
	
@Override
public void onClick(View v) {
	Button b = (Button)v;
	String newFood = b.getText().toString().toUpperCase();
	
	 
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
