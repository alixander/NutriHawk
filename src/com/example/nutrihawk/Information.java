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
		populateMeats();
		populateDairy();
		populateOthers();
	}
	
	public void populateOthers() {
		sourcesOfVitamins.put("SOYMILK", new VitaminSet(19, 10, 28, 40, 0, 28, 19, 44, 29, 29, 31, 0));
		sourcesOfMinerals.put("SOYMILK", new MineralSet(34, 7, 0, 0, 10, 5, 4, 15, 0));
		sourcesOfVitamins.put("SOYBEANS", new VitaminSet(6, 31, 16, 11, 2, 5, 50, 0, 51, 0, 0, 0));
		sourcesOfMinerals.put("SOYBEANS", new MineralSet(26, 25, 27, 28, 28, 1, 11, 11, 45));
		sourcesOfVitamins.put("EGGS", new VitaminSet(23, 8, 57, 1, 22, 13, 17, 28, 1, 19, 12, 11));
		sourcesOfMinerals.put("EGGS", new MineralSet(16, 15, 7, 37, 9, 26, 15, 2, 2));
	}
	
	public void populateMeats() {
		sourcesOfVitamins.put("BEEF KIDNEY", new VitaminSet(0, 9, 149, 17, 13, 17, 18, 353, 0, 0, 0, 0));
		sourcesOfMinerals.put("BEEF KIDNEY", new MineralSet(2, 27, 3, 26, 3, 3, 16, 24, 8));
		sourcesOfVitamins.put("BEEF LIVER", new VitaminSet(431, 9, 137, 60, 48, 35, 43, 800, 2, 0, 2, 3));
		sourcesOfMinerals.put("BEEF LIVER", new MineralSet(0, 25, 4, 34, 7, 2, 24, 486, 12));
		sourcesOfVitamins.put("BEEF LUNG", new VitaminSet(2, 7, 25, 38, 19, 3, 6, 131, 165, 0, 0, 0));
		sourcesOfMinerals.put("BEEF LUNG", new MineralSet(3, 91, 8, 54, 15, 13, 33, 33, 2));
		sourcesOfVitamins.put("BEEF PANCREAS", new VitaminSet(0, 27, 63, 44, 94, 20, 2, 614, 75, 0, 0, 0));
		sourcesOfMinerals.put("BEEF PANCREAS", new MineralSet(4, 32, 12, 101, 16, 6, 68, 10, 23));
		sourcesOfVitamins.put("BEEF SPLEEN", new VitaminSet(0, 10, 54, 86, 27, 6, 3, 258, 258, 0, 0, 0));
		sourcesOfMinerals.put("BEEF SPLEEN", new MineralSet(4, 673, 15, 94, 25, 7, 57, 142, 12));
		sourcesOfVitamins.put("BEEF TONGUE", new VitaminSet(0, 1, 15, 15, 6, 7, 1, 44, 2, 0, 1, 1));
		sourcesOfMinerals.put("BEEF TONGUE", new MineralSet(0, 12, 3, 12, 4, 2, 23, 6, 1));
		sourcesOfVitamins.put("BOLOGNA", new VitaminSet(0, 0, 2, 4, 1, 2, 1, 6, 7, 0, 0, 1));
		sourcesOfMinerals.put("BOLOGNA", new MineralSet(1, 2, 1, 5, 1, 13, 17, 1, 1));
		sourcesOfVitamins.put("BEEF TENDERLOIN", new VitaminSet(0, 5, 8, 37, 5, 29, 2, 27, 0, 0, 2, 2));
		sourcesOfMinerals.put("BEEF TENDERLOIN", new MineralSet(2, 10, 6, 21, 10, 2, 33, 4, 0));
		sourcesOfVitamins.put("CUBED STEAK", new VitaminSet(0, 51, 89, 256, 60, 199, 23, 259, 0, 0, 21, 19));
		sourcesOfMinerals.put("CUBED STEAK", new MineralSet(8, 136, 51, 193, 71, 17, 345, 39, 5));
		sourcesOfVitamins.put("GROUND BEEF", new VitaminSet(0, 2, 8, 23, 5, 16, 2, 35, 0, 0, 2, 1));
		sourcesOfMinerals.put("GROUND BEEF", new MineralSet(1, 12, 5, 17, 8, 2, 35, 4, 1));
		sourcesOfVitamins.put("BEEF RIBS", new VitaminSet(0, 13, 28, 37, 9, 38, 4, 129, 0, 0, 0, 0));
		sourcesOfMinerals.put("BEEF RIBS", new MineralSet(3, 32, 13, 46, 24, 7, 90, 11, 2));
		sourcesOfVitamins.put("BEEF BRISKETS", new VitaminSet(0, 1, 4, 7, 2, 5, 1, 12, 0, 0, 1, 1));
		sourcesOfMinerals.put("BEEF BRISKETS", new MineralSet(0, 4, 1, 6, 2, 1, 14, 2, 0));
		sourcesOfVitamins.put("BEEF SIRLOIN", new VitaminSet(0, 12, 17, 78, 16, 76, 8, 38, 0, 0, 4, 3));
		sourcesOfMinerals.put("BEEF SIRLOIN", new MineralSet(6, 22, 14, 51, 25, 6, 65, 9, 1));
		sourcesOfVitamins.put("ROAST BEEF", new VitaminSet(0, 90, 142, 386, 98, 433, 45, 446, 0, 0, 36, 36));
		sourcesOfMinerals.put("ROAST BEEF", new MineralSet(32, 159, 85, 314, 148, 46, 537, 71, 9));
		sourcesOfVitamins.put("CHICKEN BREAST", new VitaminSet(1, 4, 10, 59, 8, 23, 1, 5, 0, 0, 2, 0));
		sourcesOfMinerals.put("CHICKEN BREAST", new MineralSet(2, 7, 8, 23, 7, 4, 9, 3, 1));
		sourcesOfVitamins.put("CHICKEN DRUMSTICK", new VitaminSet(0, 1, 3, 8, 3, 5, 1, 1, 0, 0, 0, 0));
		sourcesOfMinerals.put("CHICKEN DRUMSTICK", new MineralSet(0, 2, 1, 5, 2, 1, 5, 1, 0));
		sourcesOfVitamins.put("CHICKEN LEG", new VitaminSet(2, 7, 19, 44, 17, 26, 3, 7, 0, 0, 2, 6));
		sourcesOfMinerals.put("CHICKEN LEG", new MineralSet(2, 10, 8, 26, 10, 5, 27, 6, 1));
		sourcesOfVitamins.put("LIGHT MEAT CHICKEN", new VitaminSet(1, 6, 10, 87, 14, 42, 1, 8, 0, 0, 2, 1));
		sourcesOfMinerals.put("LIGHT MEAT CHICKEN", new MineralSet(2, 8, 9, 30, 10, 4, 11, 4, 1));
		sourcesOfVitamins.put("CHICKEN THIGH", new VitaminSet(5, 6, 17, 45, 16, 22, 2, 7, 0, 0, 2, 8));
		sourcesOfMinerals.put("CHICKEN THIGH", new MineralSet(2, 10, 8, 24, 9, 5, 22, 5, 1));
		sourcesOfVitamins.put("CHICKEN WING", new VitaminSet(0, 0, 1, 4, 1, 4, 0, 1, 0, 0, 0, 0));
		sourcesOfMinerals.put("CHICKEN WING", new MineralSet(0, 1, 1, 2, 1, 0, 2, 0, 0));
		sourcesOfVitamins.put("CHICKEN NUGGETS", new VitaminSet(0, 2, 1, 6, 3, 2, 0, 1, 0, 0, 2, 4));
		sourcesOfMinerals.put("CHICKEN NUGGETS", new MineralSet(1, 2, 2, 6, 2, 6, 2, 2, 4));
		sourcesOfVitamins.put("DARK MEAT CHICKEN", new VitaminSet(3, 13, 21, 47, 16, 21, 8, 8, 0, 0, 0, 0));
		sourcesOfMinerals.put("DARK MEAT CHICKEN", new MineralSet(4, 13, 8, 24, 9, 21, 23, 7, 5));
		sourcesOfVitamins.put("CARP FISH", new VitaminSet(1, 16, 7, 18, 15, 19, 7, 42, 5, 0, 0, 0));
		sourcesOfMinerals.put("CARP FISH", new MineralSet(9, 15, 16, 90, 21, 4, 22, 6, 4));
		sourcesOfVitamins.put("COD FISH", new VitaminSet(1, 1, 3, 11, 1, 21, 2, 16, 5, 0, 0, 0));
		sourcesOfMinerals.put("COD FISH", new MineralSet(1, 2, 7, 20, 13, 3, 3, 1, 1));
		sourcesOfVitamins.put("CATFISH", new VitaminSet(1, 1, 3, 11, 1, 21, 2, 16, 5, 0, 0, 0));
		sourcesOfMinerals.put("CATFISH", new MineralSet(1, 2, 7, 20, 13, 3, 3, 1, 1));
		sourcesOfVitamins.put("BASS FISH", new VitaminSet(1, 4, 3, 5, 5, 4, 3, 24, 2, 0, 0, 0));
		sourcesOfMinerals.put("BASS FISH", new MineralSet(6, 7, 6, 16, 8, 2, 3, 4, 35));
		sourcesOfVitamins.put("ANCHOVY", new VitaminSet(0, 1, 6, 28, 3, 3, 1, 4, 0, 0, 5, 4));
		sourcesOfMinerals.put("ANCHOVY", new MineralSet(7, 7, 5, 7, 4, 43, 5, 5, 1));
		sourcesOfVitamins.put("CAVIAR", new VitaminSet(3, 2, 6, 0, 6, 3, 2, 53, 0, 9, 2, 0));
		sourcesOfMinerals.put("CAVIAR", new MineralSet(4, 11, 12, 6, 1, 10, 1, 1, 0));
		sourcesOfVitamins.put("EEL", new VitaminSet(21, 3, 1, 6, 1, 1, 1, 14, 1, 0, 0, 0));
		sourcesOfMinerals.put("EEL", new MineralSet(1, 1, 2, 8, 3, 1, 4, 0, 1));
		sourcesOfVitamins.put("HALIBUT", new VitaminSet(6, 7, 9, 57, 6, 32, 6, 36, 0, 0, 0, 0));
		sourcesOfMinerals.put("HALIBUT", new MineralSet(10, 9, 43, 45, 26, 5, 6, 3, 2));
		sourcesOfVitamins.put("HADDOCK", new VitaminSet(0, 1, 1, 7, 0, 6, 1, 8, 0, 0, 1, 0));
		sourcesOfMinerals.put("HADDOCK", new MineralSet(1, 2, 4, 7, 3, 9, 1, 1, 0));
		sourcesOfVitamins.put("HERRING", new VitaminSet(3, 7, 22, 20, 17, 37, 2, 231, 0, 0, 0, 0));
		sourcesOfMinerals.put("HERRING", new MineralSet(15, 12, 15, 42, 22, 6, 7, 7, 4));
		sourcesOfVitamins.put("MACKEREL", new VitaminSet(3, 9, 21, 30, 9, 20, 0, 279, 1, 0, 0, 0));
		sourcesOfMinerals.put("MACKEREL", new MineralSet(1, 8, 21, 24, 10, 3, 6, 4, 1));
		sourcesOfVitamins.put("PIKE", new VitaminSet(3, 7, 7, 22, 13, 10, 7, 59, 10, 0, 0, 0));
		sourcesOfMinerals.put("PIKE", new MineralSet(11, 6, 15, 44, 15, 3, 9, 5, 24));
		sourcesOfVitamins.put("POLLOCK", new VitaminSet(1, 3, 3, 5, 1, 2, 1, 42, 0, 0, 2, 0));
		sourcesOfMinerals.put("POLLOCK", new MineralSet(0, 1, 11, 29, 7, 3, 2, 2, 1));
		sourcesOfVitamins.put("SALMON", new VitaminSet(2, 40, 14, 72, 26, 58, 15, 83, 11, 0, 0, 0));
		sourcesOfMinerals.put("SALMON", new MineralSet(3, 3, 13, 45, 20, 5, 5, 4, 1));
		sourcesOfVitamins.put("SARDINE", new VitaminSet(3, 8, 20, 39, 10, 12, 4, 222, 0, 101, 15, 5));
		sourcesOfMinerals.put("SARDINE", new MineralSet(57, 24, 15, 73, 17, 31, 13, 14, 8));
		sourcesOfVitamins.put("SEA BASS", new VitaminSet(4, 9, 9, 10, 9, 23, 2, 5, 0, 0, 0, 0));
		sourcesOfMinerals.put("SEA BASS", new MineralSet(1, 2, 13, 25, 9, 4, 4, 1, 1));
		sourcesOfVitamins.put("SEATROUT", new VitaminSet(4, 9, 23, 27, 16, 43, 3, 107, 0, 0, 0, 0));
		sourcesOfMinerals.put("SEATROUT", new MineralSet(4, 4, 19, 60, 23, 6, 7, 4, 2));
		sourcesOfVitamins.put("SWORDFISH", new VitaminSet(3, 3, 7, 62, 4, 20, 1, 36, 2, 0, 0, 0));
		sourcesOfMinerals.put("SWORDFISH", new MineralSet(1, 6, 9, 36, 11, 5, 10, 9, 1));
		sourcesOfVitamins.put("TROUT", new VitaminSet(1, 18, 15, 18, 14, 7, 2, 77, 1, 0, 0, 0));
		sourcesOfMinerals.put("TROUT", new MineralSet(3, 7, 4, 19, 8, 2, 4, 7, 34));
		sourcesOfVitamins.put("TUNA", new VitaminSet(1, 28, 3, 51, 7, 44, 0, 9, 1, 0, 0, 0));
		sourcesOfMinerals.put("TUNA", new MineralSet(2, 4, 14, 21, 14, 2, 4, 3, 1));
		sourcesOfVitamins.put("PERCH", new VitaminSet(0, 4, 4, 6, 2, 7, 1, 10, 1, 0, 0, 0));
		sourcesOfMinerals.put("PERCH", new MineralSet(7, 3, 5, 14, 5, 2, 2, 1, 1));
		sourcesOfVitamins.put("LAMB", new VitaminSet(0, 22, 53, 73, 22, 49, 0, 129, 0, 0, 0, 0));
		sourcesOfMinerals.put("LAMB", new MineralSet(5, 29, 15, 53, 23, 9, 84, 19, 2));
		sourcesOfVitamins.put("BACON", new VitaminSet(0, 2, 1, 4, 1, 1, 0, 2, 0, 0, 0, 0));
		sourcesOfMinerals.put("BACON", new MineralSet(0, 1, 1, 4, 1, 8, 2, 1, 0));
		sourcesOfVitamins.put("SAUSAGE", new VitaminSet(0, 76, 20, 39, 10, 22, 1, 37, 4, 0, 0, 0));
		sourcesOfMinerals.put("SAUSAGE", new MineralSet(3, 18, 8, 31, 15, 83, 29, 10, 6));
		sourcesOfVitamins.put("HAM", new VitaminSet(0, 69, 23, 37, 8, 25, 1, 16, 0, 0, 2, 0));
		sourcesOfMinerals.put("HAM", new MineralSet(1, 11, 7, 35, 14, 81, 25, 8, 3));
		sourcesOfVitamins.put("PORK PATTIES", new VitaminSet(0, 14, 7, 10, 2, 5, 0, 7, 0, 0, 1, 0));
		sourcesOfMinerals.put("PORK PATTIES", new MineralSet(1, 5, 1, 6, 4, 27, 8, 3, 1));
		sourcesOfVitamins.put("PORK (COMPOSITE)", new VitaminSet(0, 130, 47, 60, 16, 50, 1, 29, 1, 0, 2, 0));
		sourcesOfMinerals.put("PORK (COMPOSITE)", new MineralSet(5, 14, 15, 55, 25, 6, 46, 7, 2));
		sourcesOfVitamins.put("GROUND PORK", new VitaminSet(0, 11, 4, 5, 3, 2, 0, 11, 1, 0, 0, 0));
		sourcesOfMinerals.put("GROUND PORK", new MineralSet(0, 2, 2, 6, 2, 1, 7, 2, 0));
		sourcesOfVitamins.put("TURKEY (DARK)", new VitaminSet(0, 6, 20, 26, 18, 25, 3, 9, 0, 0, 4, 7));
		sourcesOfMinerals.put("TURKEY (DARK)", new MineralSet(4, 18, 8, 29, 12, 5, 42, 11, 2));
		sourcesOfVitamins.put("TURKEY BREAST", new VitaminSet(0, 4, 9, 36, 7, 27, 2, 7, 0, 0, 0, 0));
		sourcesOfMinerals.put("TURKEY BREAST", new MineralSet(2, 9, 8, 24, 9, 3, 15, 3, 1));
		sourcesOfVitamins.put("TURKEY (LIGHT)", new VitaminSet(0, 5, 11, 44, 9, 33, 2, 8, 0, 0, 1, 0));
		sourcesOfMinerals.put("TURKEY (LIGHT)", new MineralSet(3, 11, 9, 29, 11, 4, 19, 3, 1));
		sourcesOfVitamins.put("TURKEY BACON", new VitaminSet(0, 3, 12, 14, 0, 13, 2, 5, 0, 0, 4, 7));
		sourcesOfMinerals.put("TURKEY BACON", new MineralSet(1, 10, 6, 38, 9, 78, 17, 6, 0));
		sourcesOfVitamins.put("VEAL", new VitaminSet(0, 4, 20, 40, 13, 16, 4, 27, 0, 0, 4, 0));
		sourcesOfMinerals.put("VEAL", new MineralSet(1, 16, 7, 34, 14, 7, 17, 6, 2));
		sourcesOfVitamins.put("DUCK BREAST", new VitaminSet(0, 0, 0, 22, 0, 0, 0, 0, 3, 0, 0, 0));
		sourcesOfMinerals.put("DUCK BREAST", new MineralSet(0, 10, 0, 0, 0, 2, 0, 0, 0));
		sourcesOfVitamins.put("DUCK LEG", new VitaminSet(0, 0, 0, 46, 0, 0, 0, 0, 7, 0, 0, 0));
		sourcesOfMinerals.put("DUCK LEG", new MineralSet(2, 23, 0, 0, 0, 8, 0, 0, 0));
		sourcesOfVitamins.put("GROUND TURKEY", new VitaminSet(0, 3, 8, 20, 7, 16, 1, 5, 0, 0, 1, 1));
		sourcesOfMinerals.put("GROUND TURKEY", new MineralSet(2, 9, 5, 16, 6, 4, 16, 4, 1));
	}
	
	public void populateDairy() {
		sourcesOfVitamins.put("MILK", new VitaminSet(9, 6, 27, 1, 9, 5, 3, 19, 1, 26, 0, 1));
		sourcesOfMinerals.put("MILK", new MineralSet(29, 0, 7, 23, 10, 4, 7, 1, 0));
		sourcesOfVitamins.put("BUTTER", new VitaminSet(10, 0, 1, 0, 0, 0, 0, 1, 0, 0, 2, 2));
		sourcesOfMinerals.put("BUTTER", new MineralSet(1, 2, 0, 1, 1, 0, 0, 0, 0));
		sourcesOfVitamins.put("CHEDDAR CHEESE", new VitaminSet(26, 2, 29, 1, 5, 5, 6, 18, 0, 4, 2, 5));
		sourcesOfMinerals.put("CHEDDAR CHEESE", new MineralSet(95, 5, 9, 68, 4, 34, 27, 2, 1));
		sourcesOfVitamins.put("COTTAGE CHEESE", new VitaminSet(3, 6, 26, 1, 6, 2, 6, 17, 0, 0, 0, 0));
		sourcesOfMinerals.put("COTTAGE CHEESE", new MineralSet(21, 2, 4, 37, 5, 31, 6, 3, 1));
		sourcesOfVitamins.put("CREAM CHEESE", new VitaminSet(0, 1, 4, 0, 2, 1, 2, 4, 0, 0, 0, 0));
		sourcesOfMinerals.put("CREAM CHEESE", new MineralSet(10, 0, 2, 15, 2, 8, 3, 1, 0));
		sourcesOfVitamins.put("GOUDA CHEESE", new VitaminSet(22, 4, 39, 1, 7, 8, 10, 51, 0, 0, 2, 6));
		sourcesOfMinerals.put("GOUDA CHEESE", new MineralSet(139, 3, 14, 108, 7, 68, 51, 4, 1));
		sourcesOfVitamins.put("GOAT CHEESE", new VitaminSet(6, 1, 6, 1, 2, 4, 1, 1, 0, 0, 0, 1));
		sourcesOfMinerals.put("GOAT CHEESE", new MineralSet(4, 3, 1, 7, 0, 4, 2, 10, 1));
		sourcesOfVitamins.put("FETA CHEESE", new VitaminSet(13, 15, 74, 7, 15, 32, 12, 42, 0, 0, 1, 3));
		sourcesOfMinerals.put("FETA CHEESE", new MineralSet(74, 5, 7, 51, 3, 70, 29, 2, 2));
		sourcesOfVitamins.put("MOZZARELLA CHEESE", new VitaminSet(15, 2, 19, 1, 2, 2, 2, 43, 0, 0, 1, 3));
		sourcesOfMinerals.put("MOZZARELLA CHEESE", new MineralSet(57, 3, 6, 40, 2, 29, 22, 1, 2));
		sourcesOfVitamins.put("PARMESAN CHEESE", new VitaminSet(1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0));
		sourcesOfMinerals.put("PARMESAN CHEESE", new MineralSet(6, 0, 1, 4, 0, 4, 1, 0, 0));
		sourcesOfVitamins.put("PROVOLONE CHEESE", new VitaminSet(23, 2, 25, 1, 6, 5, 3, 32, 0, 0, 2, 4));
		sourcesOfMinerals.put("PROVOLONE CHEESE", new MineralSet(100, 4, 9, 65, 5, 48, 28, 2, 1));
		sourcesOfVitamins.put("SWISS CHEESE", new VitaminSet(22, 6, 23, 1, 6, 5, 2, 73, 0, 15, 3, 4));
		sourcesOfMinerals.put("SWISS CHEESE", new MineralSet(104, 1, 13, 75, 3, 11, 38, 3, 0));
		sourcesOfVitamins.put("YOGURT", new VitaminSet(2, 7, 31, 1, 14, 6, 7, 23, 3, 0, 0, 1));
		sourcesOfMinerals.put("YOGURT", new MineralSet(45, 1, 10, 35, 16, 7, 15, 2, 0));
		sourcesOfVitamins.put("FROZEN YOGURT", new VitaminSet(3, 2, 9, 1, 5, 3, 1, 3, 1, 0, 0, 0));
		sourcesOfMinerals.put("FROZEN YOGURT", new MineralSet(10, 1, 3, 9, 4, 3, 2, 1, 0));
		sourcesOfVitamins.put("SOUR CREAM", new VitaminSet(18, 6, 21, 1, 9, 2, 7, 12, 4, 0, 4, 2));
		sourcesOfMinerals.put("SOUR CREAM", new MineralSet(25, 1, 6, 23, 9, 4, 8, 2, 0));
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
