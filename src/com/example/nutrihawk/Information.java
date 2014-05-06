package com.example.nutrihawk;

import java.util.ArrayList;
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
	}
	
	public static Information get(Context c) {
		if (sInformation == null) {
			sInformation = new Information(c.getApplicationContext());
		}
		return sInformation;
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
