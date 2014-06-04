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
	public static final int THRESHOLD = 50;
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
		}
	}
	
	public void populateFoods() {
		populateFruits();
	}
	
	public void populateFruits() {
		sourcesOfVitamins.put("ALFALFA", new VitaminSet(1, 2, 2, 1, 2, 1, 3, 0, 5, 0, 0, 13));
		sourcesOfMinerals.put("ALFALFA", new MineralSet(1, 2, 2, 2, 1, 0, 2, 3, 3));
		sourcesOfVitamins.put("APPLE", new VitaminSet(1, 1, 2, 1, 1, 2, 0, 0, 7, 0, 0, 1));
		sourcesOfMinerals.put("APPLE", new MineralSet(1, 0, 1, 1, 3, 0, 0, 2, 2));
		sourcesOfVitamins.put("AVOCADO", new VitaminSet(4, 7, 11, 13, 21, 19, 30, 0, 25, 0, 16, 39));
		sourcesOfMinerals.put("AVOCADO", new MineralSet(2, 5, 11, 8, 21, 0, 6, 14, 11));
		sourcesOfVitamins.put("BANANA", new VitaminSet(3, 5, 10, 7, 8, 41, 11, 0, 33, 0, 1, 1));
		sourcesOfMinerals.put("BANANA", new MineralSet(1, 3, 15, 5, 23, 0, 2, 9, 30));
		sourcesOfVitamins.put("BLACKBERRIES", new VitaminSet(6, 2, 2, 5, 4, 2, 9, 0, 50, 0, 8, 36));
		sourcesOfMinerals.put("BLACKBERRIES", new MineralSet(4, 5, 7, 3, 7, 0, 5, 12, 47));
		sourcesOfVitamins.put("BLUEBERRIES", new VitaminSet(2, 4, 4, 3, 2, 4, 2, 0, 24, 0, 4, 36));
		sourcesOfMinerals.put("BLUEBERRIES", new MineralSet(1, 2, 2, 2, 3, 0, 2, 4, 25));
		sourcesOfVitamins.put("BOYSENBERRIES", new VitaminSet(2, 5, 3, 5, 3, 4, 21, 0, 7, 0, 6, 13));
		sourcesOfMinerals.put("BOYSENBERRIES", new MineralSet(4, 6, 5, 4, 5, 0, 2, 5, 36));
		sourcesOfVitamins.put("CANTALOUPE", new VitaminSet(120, 5, 2, 6, 2, 6, 9, 0, 108, 0, 0, 6));
		sourcesOfMinerals.put("CANTALOUPE", new MineralSet(2, 2, 5, 3, 14, 1, 2, 4, 4));
		sourcesOfVitamins.put("CHERRIES", new VitaminSet(40, 3, 4, 3, 2, 3, 3, 0, 26, 0, 1, 4));
		sourcesOfMinerals.put("CHERRIES", new MineralSet(2, 3, 3, 2, 8, 0, 1, 8, 9));
		sourcesOfVitamins.put("CRANBERRIES", new VitaminSet(1, 1, 1, 1, 3, 3, 0, 0, 24, 0, 7, 7));
		sourcesOfMinerals.put("CRANBERRIES", new MineralSet(1, 2, 2, 1, 3, 0, 1, 3, 20));
		sourcesOfVitamins.put("DATES", new VitaminSet(1, 1, 1, 2, 2, 3, 1, 0, 0, 0, 0, 1));
		sourcesOfMinerals.put("DATES", new MineralSet(2, 1, 3, 1, 5, 0, 1, 4, 4));
		sourcesOfVitamins.put("GRAPEFRUIT", new VitaminSet(53, 7, 4, 2, 6, 6, 7, 0, 120, 0, 1, 0));
		sourcesOfMinerals.put("GRAPEFRUIT", new MineralSet(5, 1, 5, 4, 9, 0, 1, 4, 3));
		sourcesOfVitamins.put("GRAPES", new VitaminSet(2, 6, 3, 1, 0, 5, 1, 0, 6, 0, 1, 17));
		sourcesOfMinerals.put("GRAPES", new MineralSet(1, 1, 1, 1, 5, 0, 0, 2, 33));
		sourcesOfVitamins.put("KIWI", new VitaminSet(3, 3, 3, 3, 3, 6, 11, 0, 273, 0, 13, 89));
		sourcesOfMinerals.put("KIWI", new MineralSet(6, 3, 8, 6, 16, 0, 2, 12, 9));
		sourcesOfVitamins.put("LEMON", new VitaminSet(1, 6, 2, 1, 4, 8, 6, 0, 187, 0, 2, 0));
		sourcesOfMinerals.put("LEMON", new MineralSet(6, 7, 4, 3, 8, 0, 1, 4, 3));
		sourcesOfVitamins.put("LIMES", new VitaminSet(1, 1, 1, 1, 1, 1, 1, 0, 32, 0, 1, 1));
		sourcesOfMinerals.put("LIMES", new MineralSet(2, 2, 1, 1, 2, 0, 0, 2, 0));
		sourcesOfVitamins.put("LYCHEES", new VitaminSet(0, 1, 7, 6, 0, 10, 7, 0, 226, 0, 1, 1));
		sourcesOfMinerals.put("LYCHEES", new MineralSet(1, 3, 5, 6, 9, 0, 1, 14, 5));
		sourcesOfVitamins.put("MANGOS", new VitaminSet(25, 6, 6, 5, 3, 11, 6, 0, 76, 0, 9, 9));
		sourcesOfMinerals.put("MANGOS", new MineralSet(2, 1, 4, 2, 7, 0, 0, 9, 2));
		sourcesOfVitamins.put("NECTARINES", new VitaminSet(9, 3, 2, 8, 3, 2, 2, 0, 13, 0, 6, 4));
		sourcesOfMinerals.put("NECTARINES", new MineralSet(1, 2, 3, 4, 8, 0, 2, 6, 4));
		sourcesOfVitamins.put("OLIVES", new VitaminSet(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		sourcesOfMinerals.put("OLIVES", new MineralSet(0, 0, 0, 0, 0, 2, 0, 0, 0));
		sourcesOfVitamins.put("ORANGES", new VitaminSet(8, 10, 4, 3, 5, 5, 14, 0, 160, 0, 2, 0));
		sourcesOfMinerals.put("ORANGES", new MineralSet(7, 1, 4, 3, 9, 0, 1, 4, 2));
		sourcesOfVitamins.put("PAPAYAS", new VitaminSet(31, 3, 3, 2, 3, 1, 13, 0, 144, 0, 5, 5));
		sourcesOfMinerals.put("PAPAYAS", new MineralSet(3, 1, 3, 1, 10, 0, 1, 1, 1));
		sourcesOfVitamins.put("PASSIONFRUIT", new VitaminSet(60, 0, 18, 18, 0, 12, 8, 0, 118, 0, 0, 2));
		sourcesOfMinerals.put("PASSIONFRUIT", new MineralSet(3, 21, 17, 16, 23, 3, 2, 10, 0));
		sourcesOfVitamins.put("PEACHES", new VitaminSet(11, 3, 3, 7, 3, 2, 2, 0, 19, 0, 6, 6));
		sourcesOfMinerals.put("PEACHES", new MineralSet(1, 2, 4, 4, 10, 0, 2, 6, 5));
		sourcesOfVitamins.put("PEARS", new VitaminSet(1, 1, 2, 1, 1, 2, 3, 0, 10, 0, 1, 8));
		sourcesOfMinerals.put("PEARS", new MineralSet(1, 1, 3, 2, 5, 0, 1, 6, 4));
		sourcesOfVitamins.put("PINEAPPLE", new VitaminSet(2, 9, 3, 4, 4, 9, 7, 0, 131, 0, 0, 1));
		sourcesOfMinerals.put("PINEAPPLE", new MineralSet(2, 3, 5, 1, 5, 0, 1, 9, 76));
		sourcesOfVitamins.put("PLUMS", new VitaminSet(11, 3, 3, 3, 2, 2, 2, 0, 26, 0, 2, 13));
		sourcesOfMinerals.put("PLUMS", new MineralSet(1, 2, 3, 3, 7, 0, 1, 5, 4));
		sourcesOfVitamins.put("POMEGRANATES", new VitaminSet(0, 13, 9, 4, 11, 11, 27, 0, 48, 0, 8, 58));
		sourcesOfMinerals.put("POMEGRANATES", new MineralSet(3, 5, 8, 10, 19, 0, 7, 22, 17));
		sourcesOfVitamins.put("RAISINS", new VitaminSet(0, 12, 12, 6, 2, 14, 2, 0, 6, 0, 1, 7));
		sourcesOfMinerals.put("RAISINS", new MineralSet(8, 17, 13, 17, 35, 1, 2, 26, 25));
		sourcesOfVitamins.put("RASPBERRIES", new VitaminSet(1, 3, 3, 4, 4, 3, 6, 0, 54, 0, 5, 12));
		sourcesOfMinerals.put("RASPBERRIES", new MineralSet(3, 5, 7, 4, 5, 0, 3, 6, 41));
		sourcesOfVitamins.put("STARFRUIT", new VitaminSet(2, 1, 1, 2, 5, 1, 4, 0, 76, 0, 1, 0));
		sourcesOfMinerals.put("STARFRUIT", new MineralSet(0, 1, 3, 2, 5, 0, 1, 9, 2));
		sourcesOfVitamins.put("STRAWBERRIES", new VitaminSet(0, 2, 2, 3, 2, 4, 9, 0, 149, 0, 2, 4));
		sourcesOfMinerals.put("STRAWBERRIES", new MineralSet(2, 3, 5, 4, 7, 0, 1, 4, 29));
		sourcesOfVitamins.put("TOMATOES", new VitaminSet(25, 4, 2, 4, 1, 6, 6, 0, 32, 0, 4, 15));
		sourcesOfMinerals.put("TOMATOES", new MineralSet(1, 2, 4, 4, 10, 0, 2, 4, 8));
		sourcesOfVitamins.put("WATERMELON", new VitaminSet(18, 3, 2, 1, 3, 3, 1, 0, 21, 0, 0, 0));
		sourcesOfMinerals.put("WATERMELON", new MineralSet(1, 2, 4, 2, 5, 0, 1, 3, 3));


	}
	
	public void sortNutrientsByDate() {
		Collections.sort(mNutrients, new Comparator<Nutrient>() {
			@Override
			public int compare(Nutrient n1, Nutrient n2) {
				if (n1.hasTaken() && n2.hasTaken()) { //had both nutrients
					LocalDate n1Date = n1.getLastTaken();
					LocalDate n2Date = n2.getLastTaken();
					if (n1Date.getDayOfYear() > n2Date.getDayOfYear()) {
						return 1;
					} else {
						return -1;
					}
				} else if (n1.hasTaken() && !n2.hasTaken()) { // never had second nutrient
					return 1;
				} else if (!n1.hasTaken() && n2.hasTaken()) { // never had first nutrient
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
