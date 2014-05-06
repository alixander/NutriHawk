package com.example.nutrihawk;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StatusFragment extends ListFragment {
	private ArrayList<Nutrient> mNutrients;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.status_title);
		mNutrients = Information.get(getActivity()).getNutrients();
		
		StatusItemAdapter adapter = new StatusItemAdapter(mNutrients);
		setListAdapter(adapter);
	}
	
	private class StatusItemAdapter extends ArrayAdapter<Nutrient> {
		
		public StatusItemAdapter(ArrayList<Nutrient> nutrients) {
			super(getActivity(), 0, nutrients);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_status, null);
			}
			
			Nutrient n = getItem(position);
			
			TextView nameTextView = (TextView)convertView.findViewById(R.id.status_list_item_nameTextView);
			nameTextView.setText(n.getName());
			TextView daysTextView = (TextView)convertView.findViewById(R.id.status_list_item_lastIntookView);
			daysTextView.setText("Last Intake: " + n.getDatesIntook().get(n.getDatesIntook().size()-1));
			
			return convertView;
		}
	}
}
