package com.example.nutrihawk;

import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class SpecificNutrientFragment extends Fragment {
	public static final String EXTRA_NUTRIENT_ID = "com.example.nutrihawk.nutrient_id";
	private Nutrient mNutrient;
	private TextView mNutrientNameField;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID nutrientId = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_NUTRIENT_ID);
		mNutrient = Information.get(getActivity()).getNutrient(nutrientId);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_specificnutrient, parent, false);
		
		mNutrientNameField = (TextView)v.findViewById(R.id.nutrient_name);
		mNutrientNameField.setText(mNutrient.getName());
		
		Button check_sources_button = (Button)v.findViewById(R.id.check_sources_button);
		check_sources_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), FoodSourcesActivity.class);
				i.putExtra(EXTRA_NUTRIENT_ID, mNutrient.getId());
				startActivity(i);
			}
		});
		
		int numOfPoints = mNutrient.getDatesIntook().size();
		LinearLayout graph_holder = (LinearLayout) v.findViewById(R.id.graph_holder);
		if (numOfPoints != 0) {
			GraphViewData[] data = new GraphViewData[numOfPoints];
			for (int i = 0; i < numOfPoints; i++) {
				data[i] = new GraphViewData(i, mNutrient.getAmount().get(i));
			}
			GraphViewSeries series = new GraphViewSeries(data);

			GraphView graphView = new LineGraphView(
			    getActivity()
			    , "Nutrient Intake Chart"
			);
			graphView.addSeries(series);
			graphView.setScalable(true);
			graphView.getGraphViewStyle().setVerticalLabelsWidth(150);
			String[] horizontalLabels = new String[numOfPoints];
			for (int i = 0; i < numOfPoints; i++) {
				horizontalLabels[i] = mNutrient.getDatesIntook().get(i).toString().substring(5);
			}
			graphView.setHorizontalLabels(horizontalLabels);
			graphView.setVerticalLabels(new String[] {"ample", "enough", "low"});
			graphView.setScrollable(true);
			 
			graph_holder.addView(graphView);
		} else {
			TextView no_data = new TextView(getActivity());
			no_data.setText("No data found");
			graph_holder.addView(no_data);
		}
	
		return v;
	}
}
