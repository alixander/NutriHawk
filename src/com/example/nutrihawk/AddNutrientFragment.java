package com.example.nutrihawk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNutrientFragment extends Fragment {
	Button mAddNutrientButton;
	EditText mNewNutrient;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_add_nutrient, parent, false);
		
		mNewNutrient = (EditText)v.findViewById(R.id.add_nutrient_edit);
		
		mAddNutrientButton = (Button)v.findViewById(R.id.add_nutrient_button);
		mAddNutrientButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Nutrient newNutrient = new Nutrient(mNewNutrient.getText().toString());
				Information.get(getActivity()).addNutrient(newNutrient);
				Toast.makeText(getActivity(), "New Nutrient added!", Toast.LENGTH_SHORT).show();
			}
		});
		return v;
	}
}
