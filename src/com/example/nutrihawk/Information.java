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
		sourcesOfVitamins.put("APPLE", new VitaminSet(0, 0, 1, 0, 0, 1, 0, 0, 2, 0, 0, 0));
		sourcesOfMinerals.put("APPLE", new MineralSet(0, 0, 0, 0, 1, 0, 0, 1, 1));
		sourcesOfVitamins.put("AVOCADO", new VitaminSet(1, 1, 3, 3, 5, 4, 7, 0, 5, 0, 3, 9));
		sourcesOfMinerals.put("AVOCADO", new MineralSet(0, 1, 2, 2, 5, 0, 1, 3, 2));
		sourcesOfVitamins.put("BANANA", new VitaminSet(0, 1, 1, 1, 1, 6, 2, 0, 5, 0, 0, 0));
		sourcesOfMinerals.put("BANANA", new MineralSet(0, 0, 2, 1, 3, 0, 0, 1, 4));
		sourcesOfVitamins.put("BLACKBERRIES", new VitaminSet(1, 0, 1, 1, 1, 0, 2, 0, 12, 0, 2, 8));
		sourcesOfMinerals.put("BLACKBERRIES", new MineralSet(1, 1, 2, 1, 2, 0, 1, 3, 11));
		sourcesOfVitamins.put("BLUEBERRIES", new VitaminSet(0, 1, 1, 1, 0, 1, 0, 0, 5, 0, 1, 8));
		sourcesOfMinerals.put("BLUEBERRIES", new MineralSet(0, 1, 0, 0, 1, 0, 0, 1, 6));
		sourcesOfVitamins.put("BOYSENBERRIES", new VitaminSet(0, 1, 1, 1, 1, 1, 5, 0, 2, 0, 1, 3));
		sourcesOfMinerals.put("BOYSENBERRIES", new MineralSet(1, 2, 1, 1, 1, 0, 0, 1, 9));
		sourcesOfVitamins.put("CANTALOUPE", new VitaminSet(22, 1, 0, 1, 0, 1, 2, 0, 20, 0, 0, 1));
		sourcesOfMinerals.put("CANTALOUPE", new MineralSet(0, 0, 1, 0, 3, 0, 0, 1, 1));
		sourcesOfVitamins.put("CHERRIES", new VitaminSet(8, 1, 1, 1, 0, 1, 1, 0, 5, 0, 0, 1));
		sourcesOfMinerals.put("CHERRIES", new MineralSet(1, 1, 1, 0, 2, 0, 0, 2, 2));
		sourcesOfVitamins.put("CRANBERRIES", new VitaminSet(0, 0, 0, 0, 1, 1, 0, 0, 7, 0, 2, 2));
		sourcesOfMinerals.put("CRANBERRIES", new MineralSet(0, 0, 0, 0, 1, 0, 0, 1, 6));
		sourcesOfVitamins.put("DATES", new VitaminSet(1, 1, 1, 3, 3, 4, 1, 0, 0, 0, 0, 1));
		sourcesOfMinerals.put("DATES", new MineralSet(2, 2, 4, 2, 7, 0, 1, 6, 5));
		sourcesOfVitamins.put("GRAPEFRUIT", new VitaminSet(8, 1, 1, 0, 1, 1, 1, 0, 17, 0, 0, 0));
		sourcesOfMinerals.put("GRAPEFRUIT", new MineralSet(1, 0, 1, 1, 1, 0, 0, 1, 0));
		sourcesOfVitamins.put("GRAPES", new VitaminSet(1, 2, 1, 0, 0, 2, 0, 0, 2, 0, 0, 6));
		sourcesOfMinerals.put("GRAPES", new MineralSet(0, 1, 0, 0, 2, 0, 0, 1, 12));
		sourcesOfVitamins.put("KIWI", new VitaminSet(1, 1, 0, 1, 1, 1, 2, 0, 51, 0, 2, 17));
		sourcesOfMinerals.put("KIWI", new MineralSet(1, 1, 1, 1, 3, 0, 0, 2, 2));
		sourcesOfVitamins.put("LEMON", new VitaminSet(0, 1, 0, 0, 1, 1, 1, 0, 29, 0, 0, 0));
		sourcesOfMinerals.put("LEMON", new MineralSet(1, 1, 1, 1, 1, 0, 0, 1, 0));
		sourcesOfVitamins.put("LIMES", new VitaminSet(0, 1, 0, 0, 1, 1, 1, 0, 16, 0, 0, 0));
		sourcesOfMinerals.put("LIMES", new MineralSet(1, 1, 0, 1, 1, 0, 0, 1, 0));
		sourcesOfVitamins.put("LYCHEES", new VitaminSet(0, 0, 1, 1, 0, 2, 1, 0, 39, 0, 0, 0));
		sourcesOfMinerals.put("LYCHEES", new MineralSet(0, 1, 1, 1, 2, 0, 0, 2, 1));
		sourcesOfVitamins.put("MANGOS", new VitaminSet(5, 1, 1, 1, 1, 2, 1, 0, 15, 0, 2, 2));
		sourcesOfMinerals.put("MANGOS", new MineralSet(0, 0, 1, 0, 1, 0, 0, 2, 0));
		sourcesOfVitamins.put("NECTARINES", new VitaminSet(2, 1, 1, 2, 1, 0, 0, 0, 3, 0, 1, 1));
		sourcesOfMinerals.put("NECTARINES", new MineralSet(0, 1, 1, 1, 2, 0, 0, 1, 1));
		sourcesOfVitamins.put("OLIVES", new VitaminSet(3, 0, 0, 0, 0, 1, 0, 0, 0, 0, 6, 1));
		sourcesOfMinerals.put("OLIVES", new MineralSet(2, 1, 1, 0, 0, 21, 0, 2, 0));
		sourcesOfVitamins.put("ORANGES", new VitaminSet(1, 2, 1, 0, 1, 1, 2, 0, 29, 0, 0, 0));
		sourcesOfMinerals.put("ORANGES", new MineralSet(1, 0, 1, 0, 2, 0, 0, 1, 0));
		sourcesOfVitamins.put("PAPAYAS", new VitaminSet(7, 1, 1, 1, 1, 0, 3, 0, 34, 0, 1, 1));
		sourcesOfMinerals.put("PAPAYAS", new MineralSet(1, 0, 1, 0, 2, 0, 0, 0, 0));
		sourcesOfVitamins.put("PASSIONFRUIT", new VitaminSet(8, 0, 3, 2, 0, 2, 1, 0, 17, 0, 0, 0));
		sourcesOfMinerals.put("PASSIONFRUIT", new MineralSet(0, 3, 2, 2, 3, 0, 0, 1, 0));
		sourcesOfVitamins.put("PEACHES", new VitaminSet(2, 1, 1, 1, 1, 0, 0, 0, 4, 0, 1, 1));
		sourcesOfMinerals.put("PEACHES", new MineralSet(0, 0, 1, 1, 2, 0, 0, 1, 1));
		sourcesOfVitamins.put("PEARS", new VitaminSet(0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 2));
		sourcesOfMinerals.put("PEARS", new MineralSet(0, 0, 1, 0, 1, 0, 0, 1, 1));
		sourcesOfVitamins.put("PINEAPPLE", new VitaminSet(0, 2, 1, 1, 1, 2, 1, 0, 26, 0, 0, 0));
		sourcesOfMinerals.put("PINEAPPLE", new MineralSet(0, 1, 1, 0, 1, 0, 0, 2, 15));
		sourcesOfVitamins.put("PLUMS", new VitaminSet(2, 1, 1, 1, 0, 0, 0, 0, 5, 0, 0, 3));
		sourcesOfMinerals.put("PLUMS", new MineralSet(0, 0, 1, 1, 1, 0, 0, 1, 1));
		sourcesOfVitamins.put("POMEGRANATES", new VitaminSet(0, 1, 1, 0, 1, 1, 3, 0, 6, 0, 1, 7));
		sourcesOfMinerals.put("POMEGRANATES", new MineralSet(0, 1, 1, 1, 2, 0, 1, 3, 2));
		sourcesOfVitamins.put("RAISINS", new VitaminSet(0, 2, 2, 1, 0, 3, 0, 0, 1, 0, 0, 1));
		sourcesOfMinerals.put("RAISINS", new MineralSet(2, 3, 3, 3, 7, 0, 0, 5, 5));
		sourcesOfVitamins.put("RASPBERRIES", new VitaminSet(0, 1, 1, 1, 1, 1, 2, 0, 14, 0, 1, 3));
		sourcesOfMinerals.put("RASPBERRIES", new MineralSet(1, 1, 2, 1, 1, 0, 1, 1, 11));
		sourcesOfVitamins.put("STARFRUIT", new VitaminSet(0, 0, 0, 1, 1, 0, 1, 0, 19, 0, 0, 0));
		sourcesOfMinerals.put("STARFRUIT", new MineralSet(0, 0, 1, 0, 1, 0, 0, 2, 1));
		sourcesOfVitamins.put("STRAWBERRIES", new VitaminSet(0, 1, 0, 1, 0, 1, 2, 0, 32, 0, 0, 1));
		sourcesOfMinerals.put("STRAWBERRIES", new MineralSet(1, 1, 1, 1, 1, 0, 0, 1, 6));
		sourcesOfVitamins.put("TOMATOES", new VitaminSet(5, 1, 0, 1, 0, 1, 1, 0, 7, 0, 1, 3));
		sourcesOfMinerals.put("TOMATOES", new MineralSet(0, 0, 1, 1, 2, 0, 0, 1, 2));
		sourcesOfVitamins.put("WATERMELON", new VitaminSet(4, 1, 0, 0, 1, 1, 0, 0, 4, 0, 0, 0));
		sourcesOfMinerals.put("WATERMELON", new MineralSet(0, 0, 1, 0, 1, 0, 0, 1, 1));

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
