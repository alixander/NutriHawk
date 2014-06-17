package com.alixander.nutrihawk;

import java.util.ArrayList;

import org.joda.time.LocalDate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, parent, false);
		
		ActionBar ab = getActivity().getActionBar();
		SpannableString s = new SpannableString("NUTRIHAWK");
	    s.setSpan(new TypeSpan(getActivity(), "mensch-bold.ttf"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle(s);
		
		TextView most_in_need_section = (TextView)v.findViewById(R.id.most_in_need);
		ArrayList<Nutrient> nutrients = Information.get(getActivity()).getNutrients();
		ArrayList<LocalDate> validDates = new ArrayList<LocalDate>(); //dates in past week
		LocalDate currentDate = new LocalDate();
		for (int i = 0; i < 7; i++) {
			validDates.add(0, currentDate.minusDays(i));
		}
		Nutrient current_least = nutrients.get(0);
		int least_amount = 10000;
		for (Nutrient n : nutrients) {
			int current_amount = 0;
			ArrayList<LocalDate> dates = n.getDatesIntook();
			ArrayList<Integer> days = new ArrayList();
			for (int i = 0; i < dates.size(); i++) {
				days.add(dates.get(i).getDayOfYear());
			}
			for (int i = 0; i < 7; i++) {
				if (days.contains(validDates.get(i).getDayOfYear())) {
					current_amount += n.getAmount().get(days.indexOf(validDates.get(i).getDayOfYear()));
				}
			}
			if (current_amount <= least_amount) {
				least_amount = current_amount;
				current_least = n;
			}
		}
		most_in_need_section.setText(current_least.getName());
		
		Button status_button = (Button) v.findViewById(R.id.check_status_button);
		status_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), StatusActivity.class);
				startActivity(i);
		    }
		});
		
		Button add_button = (Button)v.findViewById(R.id.add_food_button);
		add_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AddNutrientActivity.class);
				startActivity(i);
			}
		});
		return v;
	}
}