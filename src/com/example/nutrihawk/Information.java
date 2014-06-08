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
		populateVeggies();
		populateGrains();
		populateNuts();
	}
	
	public void populateFruits() {
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
	
	private void populateVeggies() {
		sourcesOfVitamins.put("ALFALFA", new VitaminSet(1, 2, 2, 1, 2, 1, 3, 0, 5, 0, 0, 13));
		sourcesOfMinerals.put("ALFALFA", new MineralSet(1, 2, 2, 2, 1, 0, 2, 3, 3));
		sourcesOfVitamins.put("AMARANTH", new VitaminSet(16, 1, 3, 1, 0, 3, 6, 0, 20, 0, 0, 399));
		sourcesOfMinerals.put("AMARANTH", new MineralSet(6, 4, 4, 1, 5, 0, 2, 2, 12));
		sourcesOfVitamins.put("ARTICHOKES", new VitaminSet(0, 6, 5, 7, 4, 7, 22, 0, 25, 0, 1, 24));
		sourcesOfMinerals.put("ARTICHOKES", new MineralSet(6, 9, 19, 12, 14, 5, 4, 15, 16));
		sourcesOfVitamins.put("ASPARAGUS", new VitaminSet(20, 13, 11, 7, 4, 6, 17, 0, 13, 0, 8, 70));
		sourcesOfMinerals.put("ASPARAGUS", new MineralSet(3, 16, 5, 7, 8, 0, 5, 13, 11));
		sourcesOfVitamins.put("BAMBOO", new VitaminSet(1, 15, 6, 5, 2, 18, 3, 0, 10, 0, 8, 0));
		sourcesOfMinerals.put("BAMBOO", new MineralSet(2, 4, 1, 9, 23, 0, 11, 14, 20));
		sourcesOfVitamins.put("BEETS", new VitaminSet(1, 3, 3, 2, 2, 5, 37, 0, 11, 0, 0, 0));
		sourcesOfMinerals.put("BEETS", new MineralSet(2, 6, 8, 5, 13, 4, 3, 5, 22));
		sourcesOfVitamins.put("BOK CHOY", new VitaminSet(63, 2, 3, 2, 1, 7, 12, 0, 52, 0, 0, 40));
		sourcesOfMinerals.put("BOK CHOY", new MineralSet(7, 3, 3, 3, 5, 2, 1, 1, 6));
		sourcesOfVitamins.put("BROCCOLI", new VitaminSet(11, 4, 6, 3, 5, 8, 14, 0, 135, 0, 4, 116));
		sourcesOfMinerals.put("BROCCOLI", new MineralSet(4, 4, 5, 6, 8, 1, 2, 2, 10));
		sourcesOfVitamins.put("BRUSSELS SPROUTS", new VitaminSet(13, 8, 5, 3, 3, 10, 13, 0, 125, 0, 4, 195));
		sourcesOfMinerals.put("BRUSSELS SPROUTS", new MineralSet(4, 7, 5, 6, 10, 1, 2, 3, 15));
		sourcesOfVitamins.put("BUTTERNUT SQUASH", new VitaminSet(298, 9, 2, 8, 6, 11, 9, 0, 49, 0, 10, 2));
		sourcesOfMinerals.put("BUTTERNUT SQUASH", new MineralSet(7, 5, 12, 5, 14, 0, 1, 5, 14));
		sourcesOfVitamins.put("CABBAGE", new VitaminSet(2, 4, 2, 1, 2, 6, 10, 0, 54, 0, 1, 85));
		sourcesOfMinerals.put("CABBAGE", new MineralSet(4, 2, 3, 2, 4, 1, 1, 1, 7));
		sourcesOfVitamins.put("CARROTS", new VitaminSet(428, 6, 4, 6, 3, 9, 6, 0, 13, 0, 4, 21));
		sourcesOfMinerals.put("CARROTS", new MineralSet(4, 2, 4, 4, 12, 4, 2, 3, 9));
		sourcesOfVitamins.put("CAULIFLOWER", new VitaminSet(0, 4, 4, 3, 7, 11, 14, 0, 77, 0, 0, 20));
		sourcesOfMinerals.put("CAULIFLOWER", new MineralSet(2, 2, 4, 4, 9, 1, 2, 2, 8));
		sourcesOfVitamins.put("CELERIAC", new VitaminSet(0, 5, 6, 5, 5, 13, 3, 0, 21, 0, 3, 80));
		sourcesOfMinerals.put("CELERIAC", new MineralSet(7, 6, 8, 18, 13, 7, 3, 5, 12));
		sourcesOfVitamins.put("CELERY", new VitaminSet(10, 2, 4, 2, 3, 4, 10, 0, 6, 0, 1, 40));
		sourcesOfMinerals.put("CELERY", new MineralSet(4, 1, 3, 3, 8, 4, 1, 2, 6));
		sourcesOfVitamins.put("CHINESE BROCOLLI", new VitaminSet(29, 6, 8, 2, 1, 3, 22, 0, 41, 0, 2, 93));
		sourcesOfMinerals.put("CHINESE BROCOLLI", new MineralSet(9, 3, 4, 4, 7, 0, 2, 3, 12));
		sourcesOfVitamins.put("CHINESE CABBAGE", new VitaminSet(5, 2, 2, 2, 1, 9, 15, 0, 34, 0, 0, 41));
		sourcesOfMinerals.put("CHINESE CABBAGE", new MineralSet(6, 1, 2, 2, 5, 0, 1, 1, 7));
		sourcesOfVitamins.put("CORN", new VitaminSet(6, 21, 5, 13, 12, 4, 18, 0, 17, 0, 1, 1));
		sourcesOfMinerals.put("CORN", new MineralSet(0, 4, 14, 14, 12, 1, 5, 4, 12));
		sourcesOfVitamins.put("CUCUMBER", new VitaminSet(1, 1, 1, 0, 1, 1, 1, 0, 2, 0, 0, 11));
		sourcesOfMinerals.put("CUCUMBER", new MineralSet(1, 1, 2, 1, 2, 0, 1, 1, 2));
		sourcesOfVitamins.put("DAIKON", new VitaminSet(0, 5, 4, 3, 5, 8, 24, 0, 124, 0, 0, 1));
		sourcesOfMinerals.put("DAIKON", new MineralSet(9, 8, 14, 8, 22, 3, 3, 19, 6));
		sourcesOfVitamins.put("EGGPLANT", new VitaminSet(0, 2, 2, 3, 2, 3, 5, 0, 3, 0, 1, 4));
		sourcesOfMinerals.put("EGGPLANT", new MineralSet(1, 1, 3, 2, 5, 0, 1, 3, 10));
		sourcesOfVitamins.put("FENNEL", new VitaminSet(2, 1, 2, 3, 2, 2, 6, 0, 17, 0, 0, 0));
		sourcesOfMinerals.put("FENNEL", new MineralSet(4, 4, 4, 4, 10, 2, 1, 3, 8));
		sourcesOfVitamins.put("FRENCH BEANS", new VitaminSet(0, 66, 24, 19, 15, 37, 184, 0, 14, 0, 0, 0));
		sourcesOfMinerals.put("FRENCH BEANS", new MineralSet(34, 35, 86, 56, 69, 1, 23, 40, 110));
		sourcesOfVitamins.put("GREEN PEPPERS", new VitaminSet(11, 6, 2, 4, 1, 17, 4, 0, 200, 0, 3, 14));
		sourcesOfMinerals.put("GREEN PEPPERS", new MineralSet(1, 3, 4, 3, 7, 0, 1, 5, 9));
		sourcesOfVitamins.put("YAMBEAN (JICAMA)", new VitaminSet(1, 2, 2, 1, 2, 3, 4, 0, 40, 0, 3, 0));
		sourcesOfMinerals.put("YAMBEAN (JICAMA)", new MineralSet(1, 4, 4, 2, 5, 0, 1, 3, 4));
		sourcesOfVitamins.put("KALE", new VitaminSet(206, 5, 5, 3, 1, 9, 5, 0, 134, 0, 0, 684));
		sourcesOfMinerals.put("KALE", new MineralSet(9, 6, 6, 4, 9, 1, 2, 10, 26));
		sourcesOfVitamins.put("LEEKS", new VitaminSet(30, 4, 2, 2, 1, 10, 14, 0, 18, 0, 4, 52));
		sourcesOfMinerals.put("LEEKS", new MineralSet(5, 10, 6, 3, 5, 1, 1, 5, 21));
		sourcesOfVitamins.put("LIMA BEANS", new VitaminSet(0, 60, 21, 14, 24, 46, 176, 0, 0, 0, 6, 13));
		sourcesOfMinerals.put("LIMA BEANS", new MineralSet(14, 74, 100, 69, 88, 1, 34, 66, 149));
		sourcesOfVitamins.put("MUSHROOMS", new VitaminSet(0, 4, 17, 13, 10, 4, 3, 0, 2, 3, 0, 0));
		sourcesOfMinerals.put("MUSHROOMS", new MineralSet(0, 2, 2, 6, 6, 0, 2, 11, 2));
		sourcesOfVitamins.put("OKRA", new VitaminSet(7, 13, 4, 5, 2, 11, 22, 0, 35, 0, 2, 66));
		sourcesOfMinerals.put("OKRA", new MineralSet(8, 4, 14, 6, 9, 0, 4, 5, 50));
		sourcesOfVitamins.put("ONIONS", new VitaminSet(0, 5, 3, 1, 2, 10, 8, 0, 20, 0, 0, 1));
		sourcesOfMinerals.put("ONIONS", new MineralSet(4, 2, 4, 5, 7, 0, 2, 3, 10));
		sourcesOfVitamins.put("PARSNIPS", new VitaminSet(0, 8, 4, 5, 8, 6, 22, 0, 38, 0, 10, 37));
		sourcesOfMinerals.put("PARSNIPS", new MineralSet(5, 4, 10, 9, 14, 1, 5, 8, 37));
		sourcesOfVitamins.put("PEAS", new VitaminSet(22, 26, 11, 15, 2, 12, 24, 0, 97, 0, 1, 45));
		sourcesOfMinerals.put("PEAS", new MineralSet(4, 12, 12, 16, 10, 0, 12, 13, 30));
		sourcesOfVitamins.put("POTATOES", new VitaminSet(0, 1, 1, 2, 1, 5, 2, 0, 7, 0, 0, 0));
		sourcesOfMinerals.put("POTATOES", new MineralSet(1, 7, 2, 1, 4, 0, 1, 8, 11));
		sourcesOfVitamins.put("PUMPKIN", new VitaminSet(171, 4, 8, 3, 3, 4, 5, 0, 17, 0, 6, 2));
		sourcesOfMinerals.put("PUMPKIN", new MineralSet(2, 5, 3, 5, 11, 0, 2, 7, 7));
		sourcesOfVitamins.put("RADISHES", new VitaminSet(0, 1, 3, 1, 2, 4, 7, 0, 29, 0, 0, 2));
		sourcesOfMinerals.put("RADISHES", new MineralSet(3, 2, 3, 2, 8, 2, 2, 3, 4));
		sourcesOfVitamins.put("RAPINI", new VitaminSet(21, 4, 3, 2, 1, 3, 8, 0, 13, 0, 3, 112));
		sourcesOfMinerals.put("RAPINI", new MineralSet(4, 5, 2, 3, 2, 1, 2, 1, 8));
		sourcesOfVitamins.put("SPINACH", new VitaminSet(56, 2, 3, 1, 0, 3, 15, 0, 14, 0, 3, 181));
		sourcesOfMinerals.put("SPINACH", new MineralSet(3, 5, 6, 1, 5, 1, 1, 2, 13));
		sourcesOfVitamins.put("SEAWEED", new VitaminSet(0, 4, 6, 2, 1, 0, 1, 0, 0, 0, 0, 0));
		sourcesOfMinerals.put("SEAWEED", new MineralSet(0, 4, 1, 0, 1, 1, 0, 8, 3));
		sourcesOfVitamins.put("ZUCCHINI", new VitaminSet(5, 4, 9, 3, 2, 12, 8, 0, 32, 0, 1, 4));
		sourcesOfMinerals.put("ZUCCHINI", new MineralSet(2, 2, 5, 4, 8, 0, 2, 3, 10));
		sourcesOfVitamins.put("WINTER SQUASH", new VitaminSet(32, 2, 4, 3, 2, 9, 7, 0, 24, 0, 1, 2));
		sourcesOfMinerals.put("WINTER SQUASH", new MineralSet(3, 4, 4, 3, 12, 0, 2, 4, 9));
		sourcesOfVitamins.put("SWEET POTATOES", new VitaminSet(769, 14, 12, 15, 18, 29, 3, 0, 65, 0, 7, 6));
		sourcesOfMinerals.put("SWEET POTATOES", new MineralSet(8, 8, 14, 11, 27, 3, 4, 16, 50));
		sourcesOfVitamins.put("SWISS CHARD", new VitaminSet(44, 1, 2, 1, 1, 2, 1, 0, 18, 0, 3, 374));
		sourcesOfMinerals.put("SWISS CHARD", new MineralSet(2, 4, 7, 2, 4, 3, 1, 3, 7));
		sourcesOfVitamins.put("TARO", new VitaminSet(2, 7, 2, 3, 3, 15, 6, 0, 8, 0, 12, 1));
		sourcesOfMinerals.put("TARO", new MineralSet(4, 3, 9, 9, 18, 0, 2, 9, 20));
		sourcesOfVitamins.put("TURNIPS", new VitaminSet(0, 3, 2, 3, 3, 6, 5, 0, 46, 0, 0, 0));
		sourcesOfMinerals.put("TURNIPS", new MineralSet(4, 2, 4, 4, 7, 4, 2, 6, 9));
	}
	
	public void populateGrains() {
		sourcesOfVitamins.put("AMARANTH", new VitaminSet(0, 2, 3, 3, 0, 14, 14, 0, 0, 0, 2, 0));
		sourcesOfMinerals.put("AMARANTH", new MineralSet(12, 29, 40, 36, 9, 1, 14, 18, 105));
		sourcesOfVitamins.put("BARLEY", new VitaminSet(0, 9, 6, 16, 2, 9, 6, 0, 0, 0, 0, 2));
		sourcesOfMinerals.put("BARLEY", new MineralSet(2, 12, 9, 8, 4, 0, 9, 8, 20));
		sourcesOfVitamins.put("BUCKWHEAT", new VitaminSet(0, 11, 42, 60, 21, 18, 13, 0, 0, 0, 0, 0));
		sourcesOfMinerals.put("BUCKWHEAT", new MineralSet(3, 21, 98, 59, 22, 0, 27, 93, 111));
		sourcesOfVitamins.put("MILLET", new VitaminSet(0, 12, 8, 12, 3, 9, 8, 0, 0, 0, 0, 1));
		sourcesOfMinerals.put("MILLET", new MineralSet(1, 6, 19, 17, 3, 0, 11, 14, 24));
		sourcesOfVitamins.put("OATS", new VitaminSet(0, 79, 13, 7, 21, 9, 22, 0, 0, 0, 0, 0));
		sourcesOfMinerals.put("OATS", new MineralSet(8, 41, 69, 82, 19, 0, 41, 49, 383));
		sourcesOfVitamins.put("QUINOA", new VitaminSet(0, 13, 12, 4, 0, 11, 19, 0, 0, 0, 6, 0));
		sourcesOfMinerals.put("QUINOA", new MineralSet(3, 15, 30, 28, 9, 1, 13, 18, 58));
		sourcesOfVitamins.put("BROWN RICE", new VitaminSet(0, 12, 3, 15, 6, 14, 2, 0, 0, 0, 0, 1));
		sourcesOfMinerals.put("BROWN RICE", new MineralSet(2, 5, 21, 16, 2, 0, 8, 10, 88));
		sourcesOfVitamins.put("WILD RICE", new VitaminSet(0, 6, 8, 11, 3, 11, 11, 0, 0, 0, 2, 1));
		sourcesOfMinerals.put("WILD RICE", new MineralSet(0, 5, 13, 13, 5, 0, 15, 10, 23));
		sourcesOfVitamins.put("RYE", new VitaminSet(0, 36, 25, 36, 25, 25, 25, 0, 0, 0, 11, 12));
		sourcesOfMinerals.put("RYE", new MineralSet(6, 25, 51, 63, 13, 0, 42, 38, 226));
		sourcesOfVitamins.put("SPELT", new VitaminSet(0, 13, 3, 25, 0, 8, 6, 0, 0, 0, 3, 0));
		sourcesOfMinerals.put("SPELT", new MineralSet(2, 18, 24, 29, 8, 0, 16, 21, 106));
		sourcesOfVitamins.put("WHITE RICE", new VitaminSet(0, 2, 1, 3, 4, 2, 0, 0, 0, 0, 0, 0));
		sourcesOfMinerals.put("WHITE RICE", new MineralSet(0, 1, 2, 1, 0, 0, 5, 4, 23));
	}
	
	public void populateNuts() {
		sourcesOfVitamins.put("ALMONDS", new VitaminSet(0, 7, 70, 27, 3, 9, 11, 0, 0, 0, 179, 0));
		sourcesOfMinerals.put("ALMONDS", new MineralSet(37, 35, 99, 67, 29, 0, 33, 81, 181));
		sourcesOfVitamins.put("CASHEWS", new VitaminSet(0, 8, 1, 1, 2, 6, 2, 0, 0, 0, 1, 12));
		sourcesOfMinerals.put("CASHEWS", new MineralSet(1, 10, 20, 17, 5, 0, 11, 31, 23));
		sourcesOfVitamins.put("CHESTNUTS", new VitaminSet(0, 3, 1, 2, 2, 6, 5, 0, 18, 0, 0, 0));
		sourcesOfMinerals.put("CHESTNUTS", new MineralSet(1, 2, 6, 3, 4, 0, 2, 5, 24));
		sourcesOfVitamins.put("COCONUT", new VitaminSet(0, 4, 1, 2, 2, 2, 5, 0, 4, 0, 1, 0));
		sourcesOfMinerals.put("COCONUT", new MineralSet(1, 11, 6, 9, 8, 1, 6, 17, 60));
		sourcesOfVitamins.put("HAZELNUTS", new VitaminSet(0, 49, 8, 10, 11, 32, 32, 0, 12, 0, 86, 20));
		sourcesOfMinerals.put("HAZELNUTS", new MineralSet(13, 30, 47, 33, 22, 0, 19, 99, 355));
		sourcesOfVitamins.put("MACADAMIAS", new VitaminSet(0, 107, 13, 17, 10, 18, 4, 0, 3, 0, 4, 0));
		sourcesOfMinerals.put("MACADAMIAS", new MineralSet(11, 27, 44, 25, 14, 0, 12, 51, 277));
		sourcesOfVitamins.put("PEANUTS", new VitaminSet(0, 62, 12, 88, 26, 25, 88, 0, 0, 0, 61, 0));
		sourcesOfMinerals.put("PEANUTS", new MineralSet(13, 37, 61, 55, 29, 1, 32, 84, 141));
		sourcesOfVitamins.put("PECANS", new VitaminSet(1, 48, 8, 6, 9, 11, 6, 0, 2, 0, 8, 5));
		sourcesOfMinerals.put("PECANS", new MineralSet(8, 15, 33, 30, 13, 0, 33, 65, 245));
		sourcesOfVitamins.put("PINE NUTS", new VitaminSet(1, 33, 18, 30, 4, 6, 11, 0, 2, 0, 63, 91));
		sourcesOfMinerals.put("PINE NUTS", new MineralSet(2, 41, 85, 78, 23, 0, 58, 89, 594));
		sourcesOfVitamins.put("PISTACHIOS", new VitaminSet(14, 71, 12, 8, 6, 105, 16, 0, 10, 0, 14, 0));
		sourcesOfMinerals.put("PISTACHIOS", new MineralSet(13, 28, 37, 60, 36, 0, 18, 80, 74));
		sourcesOfVitamins.put("PUMPKIN SEEDS", new VitaminSet(1, 1, 2, 1, 0, 1, 1, 0, 0, 0, 0, 0));
		sourcesOfMinerals.put("PUMPKIN SEEDS", new MineralSet(4, 12, 42, 6, 17, 0, 44, 22, 16));
		sourcesOfVitamins.put("SESAME SEEDS", new VitaminSet(0, 76, 21, 33, 1, 57, 35, 0, 0, 0, 2, 0));
		sourcesOfMinerals.put("SESAME SEEDS", new MineralSet(140, 116, 126, 91, 19, 1, 74, 294, 177));
		sourcesOfVitamins.put("SUNFLOWER SEEDS", new VitaminSet(0, 45, 10, 19, 5, 31, 26, 0, 1, 0, 76, 0));
		sourcesOfMinerals.put("SUNFLOWER SEEDS", new MineralSet(4, 13, 37, 30, 8, 0, 15, 41, 45));
		sourcesOfVitamins.put("WALNUTS", new VitaminSet(0, 27, 10, 7, 7, 31, 29, 0, 3, 0, 4, 4));
		sourcesOfMinerals.put("WALNUTS", new MineralSet(11, 19, 46, 40, 15, 0, 24, 93, 200));
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
