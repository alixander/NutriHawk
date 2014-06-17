package com.alixander.nutrihawk;

import java.util.UUID;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FoodSourcesFragment extends Fragment {
	private Nutrient mNutrient;
	private GraphicalView mChart;

    private CategorySeries mDataset = new CategorySeries("Sources of Nutrient");

    private DefaultRenderer mRenderer = new DefaultRenderer();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID nutrientId = (UUID)getActivity().getIntent().getSerializableExtra(SpecificNutrientFragment.EXTRA_NUTRIENT_ID);
		mNutrient = Information.get(getActivity()).getNutrient(nutrientId);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_food_sources, parent, false);
		
		TextView mNutrientNameField = (TextView)v.findViewById(R.id.sources_for_nutrient);
		mNutrientNameField.setText("Sources for: \n" + mNutrient.getName());
		
		LinearLayout chart_holder = (LinearLayout) v.findViewById(R.id.chart);
		if (mNutrient.getSources().size() == 0) {
			TextView no_data = new TextView(getActivity());
			no_data.setText("No data found");
			chart_holder.addView(no_data);
		} else {
	        if (mChart == null) {
	            addSampleData();
	            mChart = ChartFactory.getPieChartView(getActivity(), mDataset, mRenderer);
	            chart_holder.addView(mChart);
	        } else {
	            mChart.repaint();
	        }
		}
		return v;
	}
	
	private void addSampleData() {
		for (int i = 0; i < mNutrient.getSources().size(); i++) {
			mDataset.add(mNutrient.getSources().get(i), 1);
		}
    	int []colors = new int[]{Color.MAGENTA, Color.WHITE, Color.GREEN, Color.CYAN, Color.GRAY, Color.BLUE, Color.RED, Color.YELLOW};
    	for (int i = 0; i < mNutrient.getSources().size(); i++) {
    		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[i]);
            r.setDisplayBoundingPoints(true);
            r.setDisplayChartValuesDistance(15);
            r.setDisplayChartValues(true);
            r.setChartValuesTextSize(30);
            mRenderer.addSeriesRenderer(r);
    	}
    }
}
