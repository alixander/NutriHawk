package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.UUID;

import android.content.Context;

public class Information {
	private ArrayList<Nutrient> mNutrients;
	public static HashMap<String, VitaminSet> sourcesOfVitamins = new HashMap();
	public static HashMap<String, MineralSet> sourcesOfMinerals = new HashMap();
	private static Information sInformation;
	private Context mAppContext;
	
	private Information(Context appContext) {
		mAppContext = appContext;
		mNutrients = new ArrayList<Nutrient>();
		sourcesOfVitamins.put("APPLE", new VitaminSet(2, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 1, 2));
		sourcesOfMinerals.put("APPLE", new MineralSet(0, 0, 0, 1, 2, 1, 0, 1, 0, 0, 0, 0, 0));
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
			Calendar neverDate = new GregorianCalendar(1, 1, 1);
			n.addDatesIntook(neverDate);
		}
	}
	
	public static Information get(Context c) {
		if (sInformation == null) {
			sInformation = new Information(c.getApplicationContext());
		}
		return sInformation;
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
