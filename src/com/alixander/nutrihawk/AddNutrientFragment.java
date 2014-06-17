package com.alixander.nutrihawk;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.LocalDate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddNutrientFragment extends Fragment {
	ImageButton mAddNutrientButton;
	Button mFruitsButton;
	Button mVeggiesButton;
	Button mDairyButton;
	Button mGrainsButton;
	Button mMeatsButton;
	Button mNutsButton;
	Button mOtherButton;
	Button mManualButton;
	EditText mNewFood;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(getActivity());
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	public void onPause() {
		super.onPause();
		Information.get(getActivity()).saveNutrients();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_add_nutrient, parent, false);
		
		ActionBar ab = getActivity().getActionBar();
		SpannableString s = new SpannableString("ADD FOOD");
	    s.setSpan(new TypeSpan(getActivity(), "mensch-bold.ttf"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle(s);
		
		mNewFood = (EditText)v.findViewById(R.id.add_nutrient_edit);
		
		mAddNutrientButton = (ImageButton)v.findViewById(R.id.add_nutrient_button);
		mAddNutrientButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String newFood = mNewFood.getText().toString().toUpperCase();
				ArrayList<Nutrient> currentNutrients = Information.get(getActivity()).getNutrients();
				HashMap<String, VitaminSet> vitaminSources = Information.get(getActivity()).getVitaminSources();
				HashMap<String, MineralSet> mineralSources = Information.get(getActivity()).getMineralSources();
				boolean doesContain = false;
				String match = "";
				for (String variant : variantsOfFood(newFood)) {
					if (vitaminSources.containsKey(variant)) {
						doesContain = true;
						match = variant;
					}
				}
				if (!doesContain) {
					Toast.makeText(getActivity(), "Food not found!", Toast.LENGTH_SHORT).show();
				} else {
					VitaminSet foodVitamins = vitaminSources.get(match);
					MineralSet foodMinerals = mineralSources.get(match);
					LocalDate now = new LocalDate();
					for (Nutrient n : currentNutrients) {
						if (foodVitamins.getVitaminAmount().containsKey(n.toString().toUpperCase()) &&
								foodVitamins.getVitaminAmount().get(n.toString().toUpperCase()) != 0) {
							n.addAmount(foodVitamins.getVitaminAmount().get(n.toString().toUpperCase()), now);
							n.addSource(match);
						} else if (foodMinerals.getMineralAmount().containsKey(n.toString().toUpperCase()) &&
								foodMinerals.getMineralAmount().get(n.toString().toUpperCase()) != 0) {
							n.addAmount(foodMinerals.getMineralAmount().get(n.toString().toUpperCase()), now);
							n.addSource(match);
						} else {
						}
					}
					Information.get(getActivity()).setNutrients(currentNutrients);
					Toast.makeText(getActivity(), "New Nutrients logged!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		mManualButton = (Button)v.findViewById(R.id.manual_category_button);
		mManualButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ManualInputActivity.class);
				startActivity(i);
			}
		});
		
		mOtherButton = (Button)v.findViewById(R.id.other_category_button);
		mOtherButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivityWithIntent((String)mOtherButton.getText());
			}
		});
		
		mFruitsButton = (Button)v.findViewById(R.id.fruits_category_button);
		mFruitsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivityWithIntent((String)mFruitsButton.getText());
			}
		});
		
		mVeggiesButton = (Button)v.findViewById(R.id.veggies_category_button);
		mVeggiesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivityWithIntent((String)mVeggiesButton.getText());
			}
		});
		
		mDairyButton = (Button)v.findViewById(R.id.dairy_category_button);
		mDairyButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivityWithIntent((String)mDairyButton.getText());
			}
		});
		
		mGrainsButton = (Button)v.findViewById(R.id.grains_category_button);
		mGrainsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivityWithIntent((String)mGrainsButton.getText());
			}
		});
		
		mMeatsButton = (Button)v.findViewById(R.id.meats_category_button);
		mMeatsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivityWithIntent((String)mMeatsButton.getText());
			}
		});
		
		mNutsButton = (Button)v.findViewById(R.id.nuts_category_button);
		mNutsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivityWithIntent((String)mNutsButton.getText());
			}
		});
		
		return v;
	}
	
	private ArrayList<String> variantsOfFood(String food) {
		ArrayList<String> variants = new ArrayList<String>();
		variants.add(food);
		variants.add(food + "S"); //apple -> apples
		variants.add(food + "ES"); //asparagus -> asparaguses
		variants.add(food.substring(0, food.length()-2)); //opposite of above
		variants.add(food + " CHEESE"); // cheddar -> cheddar cheese
		variants.add(food.substring(0, food.length()-1)); //ryes -> rye
		variants.add(food.substring(0, food.length()-1) + "IES"); //blueberry -> blueberries
		variants.add(food.substring(0, food.length()-3) + "Y"); //blueberries -> blueberry
		return variants;
	}
	
	private void startActivityWithIntent(String extra) {
		Intent i = new Intent(getActivity(), FoodCategoryActivity.class);
		i.putExtra(FoodCategoryFragment.EXTRA_FOOD_CATEGORY_ID, extra);
		startActivity(i);
	}
}
