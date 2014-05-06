package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import android.content.Context;

public class Information {
	private ArrayList<Nutrient> mNutrients;
	public static HashMap sourcesOfNutrients = new HashMap();
	private static Information sInformation;
	private Context mAppContext;
	
	private Information(Context appContext) {
		mAppContext = appContext;
		mNutrients = new ArrayList<Nutrient>();
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
