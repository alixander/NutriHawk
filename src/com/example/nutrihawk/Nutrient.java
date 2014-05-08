package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import org.joda.time.LocalDate;

public class Nutrient {
	private UUID mId;
	private String mName;
	private ArrayList<String> sources;
	private ArrayList<LocalDate> datesIntook;
	private ArrayList<Integer> amountEachDay;
	
	
	public Nutrient(String name) {
		mId = UUID.randomUUID();
		mName = name;
		sources = new ArrayList<String>();
		datesIntook = new ArrayList<LocalDate>();
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


	public ArrayList<LocalDate> getDatesIntook() {
		return datesIntook;
	}


	public void addDatesIntook(LocalDate l) {
		this.datesIntook.add(l);
	}
}
