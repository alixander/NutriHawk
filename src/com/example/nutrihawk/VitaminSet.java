package com.example.nutrihawk;

import java.util.HashMap;

public class VitaminSet {
	private HashMap<String, Integer> vitaminAmount = new HashMap();
	
	public VitaminSet(int a, int b1, int b2, int b3, int b5, int b6, int b9, int b12, int c, int d, int e, int k) {
		vitaminAmount.put("Vitamin A", (Integer)a);
		vitaminAmount.put("Vitamin B1", (Integer)b1);
		vitaminAmount.put("Vitamin B2", (Integer)b2);
		vitaminAmount.put("Vitamin B3", (Integer)b3);
		vitaminAmount.put("Vitamin B5", (Integer)b5);
		vitaminAmount.put("Vitamin B6", (Integer)b6);
		vitaminAmount.put("Vitamin B9", (Integer)b9);
		vitaminAmount.put("Vitamin B12", (Integer)b12);
		vitaminAmount.put("Vitamin C", (Integer)c);
		vitaminAmount.put("Vitamin D", (Integer)d);
		vitaminAmount.put("Vitamin E", (Integer)e);
		vitaminAmount.put("Vitamin K", (Integer)k);
	}
	
	public HashMap<String, Integer> getVitaminAmount() {
		return vitaminAmount;
	}
}
