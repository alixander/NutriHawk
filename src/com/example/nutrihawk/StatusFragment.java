package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class StatusFragment extends ListFragment {
	private ArrayList<Nutrient> mNutrients;
	private ArrayList<Nutrient> copy;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.status_title);
		mNutrients = Information.get(getActivity()).getNutrients();
		copy = new ArrayList<Nutrient>(mNutrients);
		Information.get(getActivity()).sortNutrientsByDate();
		StatusItemAdapter adapter = new StatusItemAdapter(mNutrients);
		setListAdapter(adapter);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		for (Nutrient n : copy) {
			if (!mNutrients.contains(n)) {
				mNutrients.add(n);
			}
		}
		((StatusItemAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_status, parent, false);
		
		Button show_all_button = (Button) v.findViewById(R.id.show_all_button);
		show_all_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (Nutrient n : copy) {
					if (!mNutrients.contains(n)) {
						mNutrients.add(n);
					}
				}
				Information.get(getActivity()).sortNutrientsByDate();
				((StatusItemAdapter)getListAdapter()).notifyDataSetChanged();
		    }
		});
		
		Button show_vitamins_button = (Button)v.findViewById(R.id.show_vitamins_only_button);
		show_vitamins_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ArrayList<Nutrient> temp = new ArrayList<Nutrient>();
				for (Nutrient n : copy) {
					if (n.toString().contains("Vitamin")) {
						temp.add(n);
					}
				}
				mNutrients.clear();
				for (Nutrient n : temp) {
					mNutrients.add(n);
				}
				Information.get(getActivity()).sortNutrientsByDate();
				((StatusItemAdapter)getListAdapter()).notifyDataSetChanged();
			}
		});
		
		Button show_minerals_button = (Button)v.findViewById(R.id.show_minerals_only_button);
		show_minerals_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ArrayList<Nutrient> temp = new ArrayList<Nutrient>();
				for (Nutrient n : copy) {
					if (!n.toString().contains("Vitamin")) {
						temp.add(n);
					}
				}
				mNutrients.clear();
				for (Nutrient n : temp) {
					mNutrients.add(n);
				}
				Information.get(getActivity()).sortNutrientsByDate();
				((StatusItemAdapter)getListAdapter()).notifyDataSetChanged();
			}
		});
		
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
			} else if (lastIntake.getDayOfYear() == (new LocalDate()).getDayOfYear()) {
				output = "Last Intake: Today";
			} else {
				output = "Last Intake: " + Days.daysBetween(lastIntake, new LocalDate()).getDays() + " days ago";
			}
			daysTextView.setText(output);
			
			return convertView;
		}
	}
}
