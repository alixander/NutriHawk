package com.example.nutrihawk;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

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
	
}
