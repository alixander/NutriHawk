package com.example.nutrihawk;

import java.util.HashMap;

public class MineralSet {
	private HashMap<String, Integer> mineralAmount = new HashMap();
	
	public MineralSet(int newCalcium, int newIron, int newMagnesium, int newPhosphorus, int newPotassium, int newSodium,
			int newZinc, int newCopper, int newManganese) {
		
		mineralAmount.put("calcium".toUpperCase(), (Integer)newCalcium);
		mineralAmount.put("iron".toUpperCase(), (Integer)newIron);
		mineralAmount.put("magnesium".toUpperCase(), (Integer)newMagnesium);
		mineralAmount.put("phosphorus".toUpperCase(), (Integer)newPhosphorus);
		mineralAmount.put("potassium".toUpperCase(), (Integer)newPotassium);
		mineralAmount.put("sodium".toUpperCase(), (Integer)newSodium);
		mineralAmount.put("zinc".toUpperCase(), (Integer)newZinc);
		mineralAmount.put("copper".toUpperCase(), (Integer)newCopper);
		mineralAmount.put("manganese".toUpperCase(), (Integer)newManganese);
	}

	public HashMap<String, Integer> getMineralAmount() {
		return mineralAmount;
	}
}
