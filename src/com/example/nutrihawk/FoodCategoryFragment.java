package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.LocalDate;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
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
				
		ActionBar ab = getActivity().getActionBar();
		SpannableString s = new SpannableString(foodCategoryName);
	    s.setSpan(new TypeSpan(getActivity(), "mensch-bold.ttf"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle(s);
		
		if (foodCategoryName.equals("FRUITS")) {
			String[] food_list = {"apple", "avocado", "banana", "blackberries", "blueberries", "boysenberries", "cantaloupe", "cherries", "cranberries", "dates", "grapefruit", "grapes", "kiwi", "lemon", "limes", "lychees", "mangos", "nectarines", "olives", "oranges", "papayas", "passionfruit", "peaches", "pears", "pineapple", "plums", "pomegranates", "raisins", "raspberries", "starfruit", "strawberries", "tomatoes", "watermelon"};
			populateButtons(v, food_list);
		} else if (foodCategoryName.equals("VEGGIES")) {
			String[] food_list = {"artichokes", "asparagus", "bamboo", "beets", "bak choy", "broccoli", "brussels sprouts", "butternut squash", "cabbage", "carrots", "cauliflower", "celeriac", "celery", "chinese brocolli", "chinese cabbage", "corn", "cucumber", "daikon", "eggplant", "fennel", "french beans", "green peppers", "yambean (jicama)", "kale", "leeks", "lima beans", "mushrooms", "okra", "onions", "parsnips", "peas", "potatoes", "pumpkin", "radishes", "rapini", "spinach", "seaweed", "zucchini", "winter squash", "sweet potatoes", "swiss chard", "taro", "turnips"};
			populateButtons(v, food_list);
		} else if (foodCategoryName.equals("NUTS")) {
			String[] food_list = {"almonds", "amaranth", "barley", "buckwheat", "cashews", "chestnuts", "coconut", "hazelnuts", "macadamias", "millet", "oats", "peanuts", "pecans", "pine nuts", "pistachios", "pumpkin seeds", "quinoa", "brown rice", "wild rice", "rye", "sesame seeds", "spelt", "sunflower seeds", "walnuts", "white rice"};
			populateButtons(v, food_list);
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
	
	private void populateButtons(View v, String[] food_list) {
		for (int i = 0; i < food_list.length; i++) {
			foods.add(food_list[i]);
			Button newButton = new Button(getActivity());
			SpannableString s = new SpannableString(SpecificNutrientFragment.capitalizeFirstLetter(food_list[i]));
		    s.setSpan(new TypeSpan(getActivity(), "Bariol_Regular.otf"), 0, s.length(),
		            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			newButton.setText(s);
			newButton.setTextSize(15);
			newButton.setTextColor(getResources().getColor(R.color.green));
			newButton.setId(i);
			newButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.category_button));
			LayoutParams params = new LayoutParams(
			        LayoutParams.WRAP_CONTENT,      
			        LayoutParams.WRAP_CONTENT
			);
			params.setMargins(15, 25, 25, 15);
			ColorFilter filter = new LightingColorFilter(getResources().getColor(R.color.green), getResources().getColor(R.color.green));
			Drawable icon = getResources().getDrawable(R.drawable.apple54);
			icon.setColorFilter(filter);
			newButton.setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null );
			newButton.setPadding(10, 10, 10, 10);
			newButton.setLayoutParams(params);
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
