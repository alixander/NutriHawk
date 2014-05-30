package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.UUID;

import org.joda.time.LocalDate;

import android.content.Context;
import android.util.Log;

public class Information {
	private static final String TAG = "NutrientInformation";
	private static final String FILENAME = "nutrients.json";
	private NutrientInformationJSONSerializer mSerializer;
	
	private ArrayList<Nutrient> mNutrients;
	public static HashMap<String, VitaminSet> sourcesOfVitamins = new HashMap();
	public static HashMap<String, MineralSet> sourcesOfMinerals = new HashMap();
	private static Information sInformation;
	private Context mAppContext;
	
	private Information(Context appContext) {
		mAppContext = appContext;
		mSerializer = new NutrientInformationJSONSerializer(mAppContext, FILENAME);
		populateFoods();
		try {
			mNutrients = mSerializer.loadNutrients();
			// In case user clears data manually or data is lost
			if (mNutrients.size() == 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			mNutrients = new ArrayList<Nutrient>();
			mNutrients.add(new Nutrient("Vitamin A"));
			mNutrients.add(new Nutrient("Vitamin B1"));
			mNutrients.add(new Nutrient("Vitamin B2"));
			mNutrients.add(new Nutrient("Vitamin B3"));
			mNutrients.add(new Nutrient("Vitamin B5"));
			mNutrients.add(new Nutrient("Vitamin B6"));
			mNutrients.add(new Nutrient("Vitamin B7"));
			mNutrients.add(new Nutrient("Vitamin B9"));
			mNutrients.add(new Nutrient("Vitamin B12"));
			mNutrients.add(new Nutrient("Vitamin C"));
			mNutrients.add(new Nutrient("Vitamin D"));
			mNutrients.add(new Nutrient("Vitamin E"));
			mNutrients.add(new Nutrient("Vitamin K"));
			
			mNutrients.add(new Nutrient("Potassium"));
			mNutrients.add(new Nutrient("Sodium"));
			mNutrients.add(new Nutrient("Calcium"));
			mNutrients.add(new Nutrient("Phosphorus"));
			mNutrients.add(new Nutrient("Magnesium"));
			mNutrients.add(new Nutrient("Zinc"));
			mNutrients.add(new Nutrient("Iron"));
			mNutrients.add(new Nutrient("Manganese"));
			mNutrients.add(new Nutrient("Copper"));
			mNutrients.add(new Nutrient("Iodine"));
			mNutrients.add(new Nutrient("Selenium"));
			mNutrients.add(new Nutrient("Molybdenum"));
		}
	}
	
	public void populateFoods() {
		populateFruits();
	}
	
	public void populateFruits() {
		sourcesOfVitamins.put("APPLE", new VitaminSet(2, 1, 2, 1, 1, 3, (int)(3.7/550), 1, 0, 8, 0, 1, 2));
		sourcesOfMinerals.put("APPLE", new MineralSet(1, 1, 2, 1, 4, 0, 0, 2, 2, 0, 0, 0, 0));
		
		sourcesOfVitamins.put("ORANGE", new VitaminSet(8, 10, 4, 3, 5, 5, (int)(13.9/550), 14, 0, 160, 0, 2, 0));
		sourcesOfMinerals.put("ORANGE", new MineralSet(7, 1, 4, 3, 9, 0, 1, 4, 2, 1, 0, 0, 0));	
		
		sourcesOfVitamins.put("BANANA", new VitaminSet(3, 5, 10, 7, 8, 41, (int)(22/550), 11, 0, 33, 0, 1, 1));
		sourcesOfMinerals.put("BANANA", new MineralSet(1, 3, 15, 5, 23, 0, 2, 9, 30, 3, 0, 0, 0));
		
		sourcesOfVitamins.put("BLACKBERRY", new VitaminSet(6, 2, 2, 5, 4, 2, (int)(12.2/550), 9, 0, 50, 0, 8, 36));
		sourcesOfMinerals.put("BLACKBERRY", new MineralSet(4, 5, 7, 3, 7, 0, 5, 12, 47, 1, 0, 0, 0));
		
		sourcesOfVitamins.put("STRAWBERRY", new VitaminSet(0, 2, 2, 3, 2, 4, (int)(8.7/550), 9, 0, 149, 0, 2, 4));
		sourcesOfMinerals.put("STRAWBERRY", new MineralSet(2, 3, 5, 4, 7, 0, 1, 4, 29, 1, 0, 0, 0));
		
		sourcesOfVitamins.put("WATERMELON", new VitaminSet(18, 3, 2, 1, 3, 3, (int)(6.3/550), 1, 0, 21, 0, 0, 0));
		sourcesOfMinerals.put("WATERMELON", new MineralSet(1, 2, 4, 2, 5, 0, 1, 3, 3, 1, 0, 0, 0));
	}
	
	public void populateVeggies() {
		sourcesOfVitamins.put("ALFALFA", new VitaminSet(1, 2, 2, 1, 2, 1, (int)(4.8/550), 3, 0, 5, 0, 0, 13));
		sourcesOfMinerals.put("ALFALFA", new MineralSet(1, 2, 2, 2, 1, 0, 2, 3, 3, 0, 0, 0, 0));
	}
	
	public void sortNutrientsByDate() {
		Collections.sort(mNutrients, new Comparator<Nutrient>() {
			@Override
			public int compare(Nutrient n1, Nutrient n2) {
				if (n1.getDatesIntook().size() != 0 && n2.getDatesIntook().size() != 0) { //had both nutrients
					LocalDate n1Date = n1.getDatesIntook().get(n1.getDatesIntook().size()-1);
					LocalDate n2Date = n2.getDatesIntook().get(n2.getDatesIntook().size()-1);
					if (n1Date.getDayOfYear() > n2Date.getDayOfYear()) {
						return 1;
					} else {
						return -1;
					}
				} else if (n1.getDatesIntook().size() != 0 && n2.getDatesIntook().size() == 0) { // never had second nutrient
					return 1;
				} else if (n1.getDatesIntook().size() == 0 && n2.getDatesIntook().size() != 0) { // never had first nutrient
					return -1;
				} else { // never had both
					return 0;
				}
			}
		});
	}
	
	public static Information get(Context c) {
		if (sInformation == null) {
			sInformation = new Information(c.getApplicationContext());
		}
		return sInformation;
	}
	
	public boolean saveNutrients() {
		try {
			mSerializer.saveNutrients(mNutrients);
			Log.d(TAG, "nutrients saved");
			return true;
		} catch (Exception e) {
			Log.e(TAG, "Error saving ", e);
			return false;
		}
	}
	
	public static HashMap<String, VitaminSet> getVitaminSources() {
		return sourcesOfVitamins;
	}
	
	public static HashMap<String, MineralSet> getMineralSources() {
		return sourcesOfMinerals;
	}
	
	public void addNutrient(Nutrient n) {
		mNutrients.add(n);
		sortNutrientsByDate();
	}
	
	public void setNutrients(ArrayList<Nutrient> n) {
		mNutrients = n;
		sortNutrientsByDate();
	}
	
	public ArrayList<Nutrient> getNutrients() {
		return mNutrients;
	}
	
	public Nutrient getNutrient(UUID id) {
		for (Nutrient n : mNutrients) {
			if (n.getId().equals(id)) {
				return n;
			}
		}
		return null;
	}
}
