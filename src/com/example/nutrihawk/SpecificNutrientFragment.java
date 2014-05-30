package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
		        return (double)mNutrient.getSourcesCount().get(mNutrient.getSources().indexOf(o));
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
}