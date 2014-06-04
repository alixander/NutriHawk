package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.telerik.widget.chart.engine.databinding.DataPointBinding;
import com.telerik.widget.chart.visualization.cartesianChart.RadCartesianChartView;
import com.telerik.widget.chart.visualization.cartesianChart.axes.CategoricalAxis;
import com.telerik.widget.chart.visualization.cartesianChart.axes.LinearAxis;
import com.telerik.widget.chart.visualization.cartesianChart.series.categorical.LineSeries;
import com.telerik.widget.chart.visualization.pieChart.PieSeries;
import com.telerik.widget.chart.visualization.pieChart.RadPieChartView;

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
		
		TextView mNutrientDescription = (TextView)v.findViewById(R.id.nutrient_description);
		int[] string_ids = getIDs();
		int nutrient_description_string_id = string_ids[0];
		int nutrient_sources_string_id = string_ids[1];
		int nutrient_deficiencies_string_id = string_ids[2];
		if (nutrient_description_string_id != 0) {
			mNutrientDescription.setText(nutrient_description_string_id);
		}
		
		RadCartesianChartView chartView = new RadCartesianChartView(getActivity().getBaseContext());

		LinearLayout graph_holder = (LinearLayout) v.findViewById(R.id.graph_holder);
		graph_holder.addView(chartView);

		LineSeries lineSeries = new LineSeries(getActivity().getBaseContext());
		lineSeries.setCategoryBinding(new DataPointBinding() {
		    @Override
		    public Object getValue(Object o) {
		        return mNutrient.getDatesIntook().get(mNutrient.getAmount().indexOf(o)).toString("dd/yy");
		    }
		});
		lineSeries.setValueBinding(new DataPointBinding() {
		    @Override
		    public Object getValue(Object o) {
		        return mNutrient.getAmount().get(mNutrient.getAmount().indexOf(o));
		    }
		});
		lineSeries.setData(mNutrient.getAmount());
		chartView.getSeries().add(lineSeries);
		
		CategoricalAxis horizontalAxis = new CategoricalAxis(getActivity().getBaseContext());
		chartView.setHorizontalAxis(horizontalAxis);

		LinearAxis verticalAxis = new LinearAxis(getActivity().getBaseContext());
		chartView.setVerticalAxis(verticalAxis);
		
		LinearLayout pie_holder = (LinearLayout) v.findViewById(R.id.pie_holder);
		RadPieChartView pieChartView = new RadPieChartView(getActivity().getBaseContext());

		PieSeries pieSeries = new PieSeries(getActivity().getBaseContext());
		pieSeries.setValueBinding(new DataPointBinding() {
		    @Override
		    public Object getValue(Object o) throws IllegalArgumentException {
		        double count = (double)mNutrient.getSourcesCount().get(mNutrient.getSources().indexOf(o));
		        String source_name = mNutrient.getSources().get(mNutrient.getSources().indexOf(o));
//		        Log.d("SOURCE", source_name);
		        VitaminSet vitaminSources = Information.get(getActivity()).getVitaminSources().get(source_name.toUpperCase());
				MineralSet mineralSources = Information.get(getActivity()).getMineralSources().get(source_name.toUpperCase());
//				Log.d("MINS", mineralSources.toString());
				int source_amount = getSourceAmount(mNutrient.getName(), vitaminSources, mineralSources);
				Log.d("AMOUNT", "" + source_amount);
		        return count * source_amount;
		    }
		});
		pieSeries.setData(mNutrient.getSources());
		

		pieChartView.getSeries().add(pieSeries);

		pie_holder.addView(pieChartView);
		
		TextView topFive = (TextView)v.findViewById(R.id.top_five_text);
		String topFive_string = "";
		ArrayList<Integer> sourcesCount = mNutrient.getSourcesCount();
		ArrayList<String> sources = mNutrient.getSources();
		double total = 0; //to find ratio of each source amount
		for (int i = 0; i < sourcesCount.size(); i++) {
			total += sourcesCount.get(i);
		}
		ArrayList<String> topFive_sources = new ArrayList<String>();
		ArrayList<Integer> topFive_amounts = new ArrayList<Integer>();
		while (topFive_sources.size() != 5) {
			int currentMostCount = 0;
			String currentMost = "";
			for (int i = 0; i < sources.size(); i++) {
				if (sourcesCount.get(i) > currentMostCount && !topFive_sources.contains(sources.get(i))) {
					currentMostCount = sourcesCount.get(i);
					currentMost = sources.get(i);
				}
			}
			if (currentMostCount == 0) {
				break;
			}
			topFive_sources.add(currentMost);
			topFive_amounts.add(currentMostCount);
		}
		for (int i = 0; i < topFive_sources.size(); i++) { // may not be enough for 5
			double ratio = Math.round((topFive_amounts.get(i)/total)*100.0);
			topFive_string += (topFive_sources.get(i) + " " + ratio + "%\n");
		}
		topFive.setText(topFive_string);
		
		return v;
	}
	
	public int getSourceAmount(String nutrient_name, VitaminSet vitamins, MineralSet minerals) {
		boolean isMineral = true;
		if (nutrient_name.toLowerCase().contains("vitamin")) {
			isMineral = false;
		}
		//implement.
		Log.d("NAME", nutrient_name);
		if (isMineral) {
			Log.d("YA", "" + minerals.getMineralAmount());
			HashMap<String, Integer> mineral_amounts = minerals.getMineralAmount();
			Log.d("EY", String.valueOf(mineral_amounts.containsKey(nutrient_name.toUpperCase())));
			return (int)mineral_amounts.get(nutrient_name.toUpperCase());
		}
		HashMap<String, Integer> vitamin_amounts = vitamins.getVitaminAmount();
		return vitamin_amounts.get(nutrient_name.toUpperCase());
	}

	private int[] getIDs() {
		String nutrient_name = mNutrient.getName();
		int description_id = 0;
		int sources_id = 0;
		int deficiency_id = 0;
		if (nutrient_name.equals("Vitamin A")) {
			description_id = R.string.vitamin_A_description;
		} else if (nutrient_name.equals("Vitamin B1")) {
			description_id = R.string.vitamin_B1_description;
		} else if (nutrient_name.equals("Vitamin B2")) {
			description_id = R.string.vitamin_B2_description;
		} else if (nutrient_name.equals("Vitamin B3")) {
			description_id = R.string.vitamin_B3_description;
		} else if (nutrient_name.equals("Vitamin B5")) {
			description_id = R.string.vitamin_B5_description;
		} else if (nutrient_name.equals("Vitamin B6")) {
			description_id = R.string.vitamin_B6_description;
		} else if (nutrient_name.equals("Vitamin B9")) {
			description_id = R.string.vitamin_B9_description;
		} else if (nutrient_name.equals("Vitamin B12")) {
			description_id = R.string.vitamin_B12_description;
		} else if (nutrient_name.equals("Vitamin C")) {
			description_id = R.string.vitamin_C_description;
		} else if (nutrient_name.equals("Vitamin D")) {
			description_id = R.string.vitamin_D_description;
		} else if (nutrient_name.equals("Vitamin E")) {
			description_id = R.string.vitamin_E_description;
		} else if (nutrient_name.equals("Vitamin K")) {
			description_id = R.string.vitamin_K_description;
		} else if (nutrient_name.equals("Calcium")) {
			description_id = R.string.calcium_description;
		} else if (nutrient_name.equals("Iron")) {
			description_id = R.string.iron_description;
		} else if (nutrient_name.equals("Magnesium")) {
			description_id = R.string.magnesium_description;
		} else if (nutrient_name.equals("Phosphorus")) {
			description_id = R.string.phosphorus_description;
		} else if (nutrient_name.equals("Potassium")) {
			description_id = R.string.potassium_description;
		} else if (nutrient_name.equals("Sodium")) {
			description_id = R.string.sodium_description;
		} else if (nutrient_name.equals("Zinc")) {
			description_id = R.string.zinc_description;
		} else if (nutrient_name.equals("Copper")) {
			description_id = R.string.copper_description;
		} else if (nutrient_name.equals("Manganese")) {
			description_id = R.string.manganese_description;
		}
		int[] output = {description_id, sources_id, deficiency_id};
		return output;
	}
}