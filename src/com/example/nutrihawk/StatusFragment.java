package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.Calendar;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_status, parent, false);
		return v;
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		Nutrient n = ((StatusItemAdapter)getListAdapter()).getItem(position);
		
		Intent i = new Intent(getActivity(), SpecificNutrientActivity.class);
		i.putExtra(SpecificNutrientFragment.EXTRA_NUTRIENT_ID, n.getId());
		startActivity(i);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((StatusItemAdapter)getListAdapter()).notifyDataSetChanged();
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
			LocalDate lastIntake = n.getDatesIntook().get(n.getDatesIntook().size()-1);
			String output;
			if (lastIntake.equals(new LocalDate(1, 1, 1))) {
				output = "Last Intake: Never";
			} else if (lastIntake.equals(new LocalDate())){
				output = "Last Intake: Today";
			} else {
				output = "Last Intake: " + Days.daysBetween(lastIntake, new LocalDate());
			}
			daysTextView.setText(output);
			
			return convertView;
		}
	}
}
