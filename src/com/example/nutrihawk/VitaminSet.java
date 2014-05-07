package com.example.nutrihawk;

import java.util.HashMap;

public class VitaminSet {
	private HashMap<String, Integer> vitaminAmount = new HashMap();
	
	public VitaminSet(int a, int b1, int b2, int b3, int b5, int b6, int b7, int b9, int b12, int c, int d, int e, int k) {
		vitaminAmount.put("A", (Integer)a);
		vitaminAmount.put("B1", (Integer)b1);
		vitaminAmount.put("B2", (Integer)b2);
		vitaminAmount.put("B3", (Integer)b3);
		vitaminAmount.put("B5", (Integer)b5);
		vitaminAmount.put("B6", (Integer)b6);
		vitaminAmount.put("B7", (Integer)b7);
		vitaminAmount.put("B9", (Integer)b9);
		vitaminAmount.put("B12", (Integer)b12);
		vitaminAmount.put("C", (Integer)c);
		vitaminAmount.put("D", (Integer)d);
		vitaminAmount.put("E", (Integer)e);
		vitaminAmount.put("K", (Integer)k);
	}
	
	public HashMap<String, Integer> getVitaminAmount() {
		return vitaminAmount;
	}
}
