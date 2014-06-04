package com.example.nutrihawk;

import java.util.HashMap;

public class VitaminSet {
	private HashMap<String, Integer> vitaminAmount;
	
	public VitaminSet(int a, int b1, int b2, int b3, int b5, int b6, int b9, int b12, int c, int d, int e, int k) {
		vitaminAmount = new HashMap<String, Integer>();
		
		vitaminAmount.put("Vitamin A".toUpperCase(), (Integer)a);
		vitaminAmount.put("Vitamin B1".toUpperCase(), (Integer)b1);
		vitaminAmount.put("Vitamin B2".toUpperCase(), (Integer)b2);
		vitaminAmount.put("Vitamin B3".toUpperCase(), (Integer)b3);
		vitaminAmount.put("Vitamin B5".toUpperCase(), (Integer)b5);
		vitaminAmount.put("Vitamin B6".toUpperCase(), (Integer)b6);
		vitaminAmount.put("Vitamin B9".toUpperCase(), (Integer)b9);
		vitaminAmount.put("Vitamin B12".toUpperCase(), (Integer)b12);
		vitaminAmount.put("Vitamin C".toUpperCase(), (Integer)c);
		vitaminAmount.put("Vitamin D".toUpperCase(), (Integer)d);
		vitaminAmount.put("Vitamin E".toUpperCase(), (Integer)e);
		vitaminAmount.put("Vitamin K".toUpperCase(), (Integer)k);
	}
	
	public HashMap<String, Integer> getVitaminAmount() {
		return vitaminAmount;
	}
}
