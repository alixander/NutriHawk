package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Nutrient {
	private UUID mId;
	private String mName;
	private ArrayList<String> sources;
	private ArrayList<Calendar> datesIntook;
	private ArrayList<Integer> amountEachDay;
	
	
	public Nutrient(String name) {
		mId = UUID.randomUUID();
		mName = name;
		sources = new ArrayList<String>();
		datesIntook = new ArrayList<Calendar>();
		amountEachDay = new ArrayList<Integer>();
	}
	
	@Override
	public String toString() {
		return mName;
	}


	public UUID getId() {
		return mId;
	}


	public void setId(UUID id) {
		mId = id;
	}


	public String getName() {
		return mName;
	}


	public void setName(String name) {
		mName = name;
	}


	public ArrayList<String> getSources() {
		return sources;
	}


	public void addSource(String source) {
		this.sources.add(source);
	}
	
	public void addAmount(int amount) {
		amountEachDay.add((Integer)amount);
	}


	public ArrayList<Calendar> getDatesIntook() {
		return datesIntook;
	}


	public void addDatesIntook(Calendar c) {
		this.datesIntook.add(c);
	}
}
