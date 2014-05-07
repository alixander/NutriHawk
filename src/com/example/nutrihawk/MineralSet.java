package com.example.nutrihawk;

import java.util.HashMap;

public class MineralSet {
	private HashMap<String, Integer> mineralAmount = new HashMap();
	
	public MineralSet(int newPotassium, int newChlorine, int newSodium, int newCalcium, 
			int newPhosphorus, int newMagnesium, int newZinc, int newIron, int newManganese, 
			int newCopper, int newIodine, int newSelenium, int newMolybdenum) {
		mineralAmount.put("potassium".toUpperCase(), (Integer)newPotassium);
		mineralAmount.put("chlorine".toUpperCase(), (Integer)newChlorine);
		mineralAmount.put("sodium".toUpperCase(), (Integer)newSodium);
		mineralAmount.put("calcium".toUpperCase(), (Integer)newCalcium);
		mineralAmount.put("phosphorus".toUpperCase(), (Integer)newPhosphorus);
		mineralAmount.put("magnesium".toUpperCase(), (Integer)newMagnesium);
		mineralAmount.put("zinc".toUpperCase(), (Integer)newZinc);
		mineralAmount.put("iron".toUpperCase(), (Integer)newIron);
		mineralAmount.put("manganese".toUpperCase(), (Integer)newManganese);
		mineralAmount.put("copper".toUpperCase(), (Integer)newCopper);
		mineralAmount.put("iodine".toUpperCase(), (Integer)newIodine);
		mineralAmount.put("selenium".toUpperCase(), (Integer)newSelenium);
		mineralAmount.put("molybdenum".toUpperCase(), (Integer)newMolybdenum);
	}

	public HashMap<String, Integer> getMineralAmount() {
		return mineralAmount;
	}
}
