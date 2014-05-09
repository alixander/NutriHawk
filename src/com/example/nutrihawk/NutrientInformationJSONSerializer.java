package com.example.nutrihawk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class NutrientInformationJSONSerializer {
	
	private Context mContext;
	private String mFilename;
	
	public NutrientInformationJSONSerializer(Context c, String f) {
		mContext = c;
		mFilename = f;
	}
	
	public void saveNutrients(ArrayList<Nutrient> nutrients) throws JSONException, IOException {
		JSONArray array = new JSONArray();
		for (Nutrient n : nutrients) {
			array.put(n.toJSON());
		}
		Writer writer = null;
		try {
			OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	public ArrayList<Nutrient> loadNutrients() throws IOException, JSONException {
		ArrayList<Nutrient> nutrients = new ArrayList<Nutrient>();
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for (int i = 0; i < array.length(); i++) {
				Log.d("OKAY", "ok");
				nutrients.add(new Nutrient(array.getJSONObject(i)));
			}
		} catch (FileNotFoundException e) {
			
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return nutrients;
	}
}
