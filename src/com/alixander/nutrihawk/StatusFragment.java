package com.alixander.nutrihawk;

import java.util.ArrayList;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_item_settings:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
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
		
		ActionBar ab = getActivity().getActionBar();
		SpannableString s = new SpannableString("CURRENT STATUS");
	    s.setSpan(new TypeSpan(getActivity(), "mensch-bold.ttf"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle(s);
		
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
//		Intent i = new Intent(getActivity(), NutrientPagerActivity.class);
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
			String output;
			if (!n.hasTaken()) {
				output = "Never";
			} else {
				LocalDate lastIntake = n.getLastTaken();
				if (lastIntake.getDayOfYear() == (LocalDate.now().getDayOfYear())) {
					output = "Today";
				} else if (Math.abs(lastIntake.getDayOfYear() - LocalDate.now().getDayOfYear()) == 1) {
					output = "Yesterday";
				} else {
					output = Days.daysBetween(lastIntake, new LocalDate()).getDays() + " days ago";
				}
			}
			daysTextView.setText(output);
			
			return convertView;
		}
	}
}
