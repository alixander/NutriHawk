package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Nutrient {
	private UUID mId;
	private String mName;
	private ArrayList<String> sources;
	private ArrayList<Date> datesIntook;
	
	
	public Nutrient(String name) {
		mId = UUID.randomUUID();
		mName = name;
		sources = new ArrayList<String>();
		datesIntook = new ArrayList<Date>();
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


	public void setSources(ArrayList<String> sources) {
		this.sources = sources;
	}


	public ArrayList<Date> getDatesIntook() {
		return datesIntook;
	}


	public void setDatesIntook(ArrayList<Date> datesIntook) {
		this.datesIntook = datesIntook;
	}
}
