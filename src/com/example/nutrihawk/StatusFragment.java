package com.example.nutrihawk;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class StatusFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.status_title);
		mNutrients = Information.get(getActivity().getNutrients());
		
		ArrayAdapter<Nutrient> adapter = new ArrayAdapter<Nutrient>(getActivity(),
				android.R.layout.simple_list_item_1, mNutrients);
	}
}
