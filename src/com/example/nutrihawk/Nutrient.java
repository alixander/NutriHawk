package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.UUID;

import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Nutrient {
	private static final String JSON_ID = "id";
	private static final String JSON_NAME = "name";
	private static final String JSON_SOURCES = "sources";
	private static final String JSON_DATES = "dates";
	private static final String JSON_AMOUNTS = "amounts";
	
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
	
	public Nutrient(JSONObject json) throws JSONException {
		mId = UUID.fromString(json.getString(JSON_ID));
		mName = json.getString(JSON_NAME);
		sources = new ArrayList<String>();
		datesIntook = new ArrayList<LocalDate>();
		amountEachDay = new ArrayList<Integer>();
		
		JSONObject tempSources = json.getJSONObject(JSON_SOURCES);
		for (int i = 0; i < tempSources.length(); i++) {
			sources.add(tempSources.getString(""+i));
		}
		
		JSONObject tempDates = json.getJSONObject(JSON_DATES);
		for (int i = 0; i < tempDates.length(); i++) {
			Log.d("YO", tempDates.getString(""+i));
			datesIntook.add(new LocalDate(tempDates.getString(""+i)));
		}
		
		JSONObject tempAmounts = json.getJSONObject(JSON_AMOUNTS);
		for (int i = 0; i < tempAmounts.length(); i++) {
			amountEachDay.add(tempAmounts.getInt(""+i));
		}
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_NAME, mName);

		JSONObject json_sources = new JSONObject();
		for (int i = 0; i < sources.size(); i++) {
			json_sources.put(""+i, sources.get(i));
		}
		json.put(JSON_SOURCES, json_sources);
		
		JSONObject json_dates = new JSONObject();
		for (int i = 0; i < datesIntook.size(); i++) {
			json_dates.put(""+i, ""+datesIntook.get(i).getYear()+datesIntook.get(i).getMonthOfYear()+datesIntook.get(i).getDayOfMonth());
		}
		json.put(JSON_DATES, json_dates);
		
		JSONObject json_amounts = new JSONObject();
		for (int i = 0; i < amountEachDay.size(); i++) {
			json_amounts.put(""+i, amountEachDay.get(i));
		}
		json.put(JSON_AMOUNTS, json_amounts);
		
		return json;
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
	
	public void addAmount(int amount, LocalDate date) {
		if (amountEachDay.size() > 1 && date.getDayOfYear() == datesIntook.get(datesIntook.size()-1).getDayOfYear()) {
			amountEachDay.set(amountEachDay.size()-1, amountEachDay.get(amountEachDay.size()-1) + amount);
		} else {
			amountEachDay.add((Integer)amount);
			datesIntook.add(date);
		}
	}
	
	public ArrayList<Integer> getAmount() {
		return amountEachDay;
	}


	public ArrayList<LocalDate> getDatesIntook() {
		return datesIntook;
	}


	public void addDatesIntook(LocalDate l) {
		// Only the "never" date
		if (datesIntook.size() == 1) {
			datesIntook.clear();
		}
		this.datesIntook.add(l);
	}
}
