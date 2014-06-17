package com.alixander.nutrihawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.joda.time.LocalDate;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodCategoryFragment extends ListFragment {
	public static final String EXTRA_FOOD_CATEGORY_ID = "com.example.nutrihawk.food_category_id";
	private String foodCategoryName;
	private HashMap<String, VitaminSet> vitaminSources;
	private HashMap<String, MineralSet> mineralSources;
	private ArrayList<Button> all_buttons;
	private ArrayList<String> foods;
	private ArrayList<String> logged_foods;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setHasOptionsMenu(true);
		all_buttons = new ArrayList<Button>();
		logged_foods = new ArrayList<String>();
		foods = new ArrayList<String>();
		foodCategoryName = getActivity().getIntent().getStringExtra(EXTRA_FOOD_CATEGORY_ID).toUpperCase();
		vitaminSources = Information.get(getActivity()).getVitaminSources();
		mineralSources = Information.get(getActivity()).getMineralSources();
		populate();
		StatusItemAdapter adapter = new StatusItemAdapter(foods);
		setListAdapter(adapter);
	}
	
//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		super.onCreateOptionsMenu(menu, inflater);
//		inflater.inflate(R.menu.food_menu, menu);
//	}
	
	private void populate() {
		if (foodCategoryName.equals("FRUITS")) {
			String[] food_list = {"apple", "avocado", "banana", "blackberries", "blueberries", "boysenberries", "cantaloupe", "cherries", "cranberries", "dates", "grapefruit", "grapes", "kiwi", "lemon", "limes", "lychees", "mangos", "nectarines", "olives", "oranges", "papayas", "passionfruit", "peaches", "pears", "pineapple", "plums", "pomegranates", "raisins", "raspberries", "starfruit", "strawberries", "tomatoes", "watermelon"};
			populateButtons(food_list);
		} else if (foodCategoryName.equals("VEGGIES")) {
			String[] food_list = {"artichokes", "asparagus", "bamboo", "beets", "bok choy", "broccoli", "brussels sprouts", "butternut squash", "cabbage", "carrots", "cauliflower", "celeriac", "celery", "chinese brocolli", "chinese cabbage", "corn", "cucumber", "daikon", "eggplant", "fennel", "french beans", "green peppers", "yambean (jicama)", "kale", "leeks", "lima beans", "mushrooms", "okra", "onions", "parsnips", "peas", "potatoes", "pumpkin", "radishes", "rapini", "spinach", "seaweed", "zucchini", "winter squash", "sweet potatoes", "swiss chard", "taro", "turnips"};
			populateButtons(food_list);
		} else if (foodCategoryName.equals("NUTS")) {
			String[] food_list = {"almonds", "cashews", "chestnuts", "coconut", "hazelnuts", "macadamias", "peanuts", "pecans", "pine nuts", "pistachios", "pumpkin seeds", "sesame seeds", "sunflower seeds", "walnuts"};
			populateButtons(food_list);
		} else if (foodCategoryName.equals("GRAINS")) {
			String[] food_list = {"amaranth", "barley", "buckwheat", "millet", "oats", "quinoa", "brown rice", "wild rice", "rye", "spelt", "white rice"};
			populateButtons(food_list);
		} else if (foodCategoryName.equals("MEATS")) {
			String[] food_list = {"beef kidney", "beef liver", "beef lung", "beef pancreas", "beef spleen", "beef tongue", "bologna", "beef tenderloin", "cubed steak", "ground beef", "beef ribs", "beef briskets", "beef sirloin", "roast beef", "chicken breast", "chicken drumstick", "chicken leg", "light meat chicken", "chicken thigh", "chicken wing", "chicken nuggets", "dark meat chicken", "carp fish", "cod fish", "catfish", "bass fish", "anchovy", "caviar", "eel", "halibut", "haddock", "herring", "mackerel", "pike", "pollock", "salmon", "sardine", "sea bass", "seatrout", "swordfish", "trout", "tuna", "perch", "lamb", "bacon", "sausage", "ham", "pork patties", "pork (composite)", "ground pork", "turkey (dark)", "turkey breast", "turkey (light)", "turkey bacon", "veal", "duck breast", "duck leg", "ground turkey"};
			populateButtons(food_list);
		} else if (foodCategoryName.equals("DAIRY")) {
			String[] food_list = {"milk", "butter", "cheddar cheese", "cottage cheese", "cream cheese", "gouda cheese", "goat cheese", "feta cheese", "mozzarella cheese", "parmesan cheese", "provolone cheese", "swiss cheese", "yogurt", "frozen yogurt", "sour cream"};
			populateButtons(food_list);
		} else if (foodCategoryName.equals("OTHER")) {
			String[] food_list = {"soymilk", "soybeans", "eggs"};
			populateButtons(food_list);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_food_category, parent, false);
				
		ActionBar ab = getActivity().getActionBar();
		SpannableString s = new SpannableString(foodCategoryName);
	    s.setSpan(new TypeSpan(getActivity(), "mensch-bold.ttf"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle(s);
		
		Button log_button = (Button) v.findViewById(R.id.log_items_button);
		final Activity activity = getActivity();
		log_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (logged_foods.size() != 0) {
					logFoods();
					Toast.makeText(getActivity(), "Nutrients logged!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getActivity(), "Not Saved", Toast.LENGTH_SHORT).show();
				}
				activity.finish();
		    }
		});
		
		return v;
	}
	
	private void logFoods() {
		LocalDate now = new LocalDate();
		
		ArrayList<Nutrient> currentNutrients = Information.get(getActivity()).getNutrients();
		for (String newFood : logged_foods) {
			VitaminSet foodVitamins = vitaminSources.get(newFood);
			MineralSet foodMinerals = mineralSources.get(newFood);
			for (Nutrient n : currentNutrients) {
				if (foodVitamins.getVitaminAmount().containsKey(n.toString().toUpperCase()) &&
					foodVitamins.getVitaminAmount().get(n.toString().toUpperCase()) != 0) {
						n.addAmount(foodVitamins.getVitaminAmount().get(n.toString().toUpperCase()), now);
						n.addSource(newFood);
				} else if (foodMinerals.getMineralAmount().containsKey(n.toString().toUpperCase()) &&
						foodMinerals.getMineralAmount().get(n.toString().toUpperCase()) != 0) {
							n.addAmount(foodMinerals.getMineralAmount().get(n.toString().toUpperCase()), now);
							n.addSource(newFood);
				} else {
				}
			}
		}
		Information.get(getActivity()).setNutrients(currentNutrients);
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		String newFood = ((StatusItemAdapter)getListAdapter()).getItem(position).toUpperCase();
		
		CheckBox food_checkbox = (CheckBox)v.findViewById(R.id.food_checked);
		if (!food_checkbox.isChecked()) {
			food_checkbox.setChecked(true);
			logged_foods.add(newFood);
		} else {
			food_checkbox.setChecked(false);
			logged_foods.remove(newFood);
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((StatusItemAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	private class StatusItemAdapter extends ArrayAdapter<String> {
		
		public StatusItemAdapter(ArrayList<String> foods) {
			super(getActivity(), 0, foods);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_foods, null);
			}
			
			final String food = SpecificNutrientFragment.capitalizeAllFirstLetters(getItem(position));
			
			TextView food_name_section = (TextView)convertView.findViewById(R.id.food_name);
			food_name_section.setText(food);
			
			CheckBox food_checked = (CheckBox)convertView.findViewById(R.id.food_checked);
			food_checked.setChecked(logged_foods.contains(food.toUpperCase()));
			food_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

		       @Override
		       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		    	   if (isChecked) {
		    		   logged_foods.add(food.toUpperCase());
		    	   } else {
		    		   logged_foods.remove(food.toUpperCase());
		    	   }
		       }
		   });
			
			return convertView;
		}
	}
	
	private void populateButtons(String[] food_list) {
		for (int i = 0; i < food_list.length; i++) {
			foods.add(food_list[i]);
		}
		Collections.sort(foods);
	}
}
