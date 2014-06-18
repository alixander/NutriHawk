package com.alixander.nutrihawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.joda.time.LocalDate;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ManualInputFragment extends Fragment{
	
	private final int SEEK_BAR_ID_OFFSET = 100;
	private final int PERCENTAGE_ID_OFFSET = 200;
	private HashMap<Nutrient, Integer> nutrient_values;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		nutrient_values = new HashMap();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_manual_input, parent, false);
		
		ActionBar ab = getActivity().getActionBar();
		SpannableString s = new SpannableString("ADD MANUALLY");
	    s.setSpan(new TypeSpan(getActivity(), "mensch-bold.ttf"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle(s);
		
		LinearLayout content_section = (LinearLayout)v.findViewById(R.id.manual_input_content);
		ArrayList<Nutrient> currentNutrients = Information.get(getActivity()).getNutrients();
		//Sort Alphabetically
		Collections.sort(currentNutrients, new Comparator<Nutrient>() {
			public int compare(Nutrient n1, Nutrient n2) {
				return n1.getName().compareTo(n2.getName());
			}
		});
		for (final Nutrient n : currentNutrients) { //final to be able to refer to it in anonymous class
			nutrient_values.put(n, 0);
			
			//Create the text part
			TextView nutrient_name = new TextView(getActivity());
			nutrient_name.setId(currentNutrients.indexOf(n));
			SpannableString top_text = new SpannableString(n.getName());
			top_text.setSpan(new TypeSpan(getActivity(), "Bariol_Regular.otf"), 0, top_text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			nutrient_name.setText(top_text);
			nutrient_name.setTextColor(getResources().getColor(R.color.green));
			nutrient_name.setTextSize(20);
			nutrient_name.setPadding(50, 0, 0, 0);
			LayoutParams text_params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			text_params.gravity = Gravity.LEFT;
			nutrient_name.setLayoutParams(text_params);
			
			final float scale = getActivity().getResources().getDisplayMetrics().density;
			
			//Create the horizontal layout for seekbar components
			LinearLayout second_row = new LinearLayout(getActivity());
			second_row.setLayoutParams(new LayoutParams((int)(400*scale+0.5f), LayoutParams.WRAP_CONTENT, 6.0f));
			second_row.setOrientation(LinearLayout.HORIZONTAL);
			second_row.setGravity(Gravity.CENTER);
			second_row.setPadding(50, 10, 10, 10);
			
			//Create seekbar component
			SeekBar seek_bar = new SeekBar(getActivity());
			seek_bar.setId(currentNutrients.indexOf(n)+SEEK_BAR_ID_OFFSET);
			seek_bar.setMax(100);
			seek_bar.setProgress(0);
			seek_bar.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 5.0f));
			seek_bar.setThumb(getResources().getDrawable(R.drawable.green_bar));
			seek_bar.setProgressDrawable(getActivity().getResources().getDrawable(R.drawable.progressbar));
			second_row.addView(seek_bar);
			
			//Create percentage component
			final TextView percentage = new TextView(getActivity());
			percentage.setId(currentNutrients.indexOf(n)+PERCENTAGE_ID_OFFSET);
			percentage.setText("0");
			percentage.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/mensch-bold.ttf"));
			percentage.setTextSize(20);
			percentage.setTextColor(getResources().getColor(R.color.green));
			percentage.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
			second_row.addView(percentage);
			
			seek_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				@Override 
				public void onProgressChanged(SeekBar seekBar, int progress, 
						boolean fromUser) { 
					percentage.setText(String.format("%d", progress) + "%");
					nutrient_values.put(n, progress);
				} 

			    @Override 
			    public void onStartTrackingTouch(SeekBar seekBar) {} 
			
		 	    @Override 
			    public void onStopTrackingTouch(SeekBar seekBar) {}
			});
			
			content_section.addView(nutrient_name);
			content_section.addView(second_row);
		}
		
		Button log_button = (Button)v.findViewById(R.id.log_items_button);
		final Activity activity = getActivity();
		log_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				LocalDate now = new LocalDate();
				ArrayList<Nutrient> currentNutrients = Information.get(getActivity()).getNutrients();
				boolean updated = false;
				for (Nutrient n : currentNutrients) {
					if (nutrient_values.get(n) != 0) {
						updated = true;
					}
					n.setManualAmount(n.getManualAmount() + nutrient_values.get(n));
					n.addAmount(nutrient_values.get(n), now);
					n.addSource("MANUAL");
				}
				if (updated) {
					Information.get(getActivity()).setNutrients(currentNutrients);
					Toast.makeText(getActivity(), "New Nutrients logged!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getActivity(), "Not Saved!", Toast.LENGTH_SHORT).show();
				}
				activity.finish();
		    }
		});
		
		return v;
	}
}