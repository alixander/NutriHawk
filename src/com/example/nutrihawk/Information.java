package com.example.nutrihawk;

import java.util.ArrayList;
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
		mNutrients = new ArrayList<Nutrient>();
		mSerializer = new NutrientInformationJSONSerializer(mAppContext, FILENAME);
		
		try {
			mNutrients = mSerializer.loadNutrients();
		} catch (Exception e) {
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
			mNutrients.add(new Nutrient("Chlorine"));
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
			
			for (Nutrient n : mNutrients) {
				LocalDate neverDate = new LocalDate(1, 1, 1);
				n.addDatesIntook(neverDate);
			}
			Log.e("WHAT", "ERror ", e);
		}
		
		sourcesOfVitamins.put("APPLE", new VitaminSet(2, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 1, 2));
		sourcesOfMinerals.put("APPLE", new MineralSet(0, 0, 0, 1, 2, 1, 0, 1, 0, 0, 0, 0, 0));
		
		sourcesOfVitamins.put("ORANGE", new VitaminSet(8, 160, 0, 0, 0, 0, 5, 0, 0, 0, 0, 2, 0));
		sourcesOfMinerals.put("ORANGE", new MineralSet(9, 0, 0, 7, 3, 4, 1, 1, 2, 4, 0, 1, 0));
		
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
	}
	
	public void setNutrients(ArrayList<Nutrient> n) {
		mNutrients = n;
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
