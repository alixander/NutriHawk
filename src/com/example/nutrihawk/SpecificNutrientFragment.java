package com.example.nutrihawk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.joda.time.LocalDate;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.telerik.widget.chart.engine.axes.common.AxisPlotMode;
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
		final HashMap<String, Double> food_amounts = new HashMap<String, Double>();
		View v = inflater.inflate(R.layout.fragment_specificnutrient, parent, false);
		
		ActionBar ab = getActivity().getActionBar();
		SpannableString s = new SpannableString(mNutrient.getName().toUpperCase());
	    s.setSpan(new TypeSpan(getActivity(), "mensch-bold.ttf"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ab.setTitle(s);
		
		TextView mNutrientDescription = (TextView)v.findViewById(R.id.nutrient_description);
		int[] string_ids = getIDs();
		int nutrient_description_string_id = string_ids[0];
		int nutrient_sources_string_id = string_ids[1];
		int nutrient_deficiencies_string_id = string_ids[2];
		
		List<String> benefits = Arrays.asList(getString(nutrient_description_string_id).split(",[ ]*"));
		TextView benefits_section = (TextView)v.findViewById(R.id.nutrient_description);
		String benefits_output = "";
		for (String benefit : benefits) {
			benefits_output += ("<b>+</b> " + capitalizeFirstLetter(benefit) + "<br>");
		}
		benefits_section.setText(Html.fromHtml(benefits_output));

		List<String> rich_sources = Arrays.asList(getString(nutrient_sources_string_id).split(",[ ]*"));
		TextView left_column = (TextView)v.findViewById(R.id.rich_sources_left_column);
		TextView right_column = (TextView)v.findViewById(R.id.rich_sources_right_column);
		String left_string = "";
		String right_string = "";
		for (int i = 0; i < rich_sources.size(); i++) {
			String newline = "<b>+</b> " + capitalizeFirstLetter(rich_sources.get(i))+"<br>";
			if (i <= rich_sources.size()/2) {
				left_string += newline;
			} else {
				right_string += newline;
			}
		}
		left_column.setText(Html.fromHtml(left_string));
		right_column.setText(Html.fromHtml(right_string));
		
		TextView deficiency_section = (TextView)v.findViewById(R.id.deficiency_symptoms);
		List<String> deficiencies = Arrays.asList(getString(nutrient_deficiencies_string_id).split(",[ ]*"));
		String output = "";
		for (String deficiency : deficiencies) {
			output += ("<b>+</b> " + capitalizeFirstLetter(deficiency) + "<br>");
		}
		deficiency_section.setText(Html.fromHtml(output));
		
		//Line Chart
		
		final ArrayList<LocalDate> validDates = new ArrayList<LocalDate>(); //dates in past week
		LocalDate currentDate = new LocalDate();
		for (int i = 0; i < 7; i++) {
			validDates.add(0, currentDate.minusDays(i));
		}
		
		RadCartesianChartView chartView = new RadCartesianChartView(getActivity().getBaseContext());

		LinearLayout graph_holder = (LinearLayout) v.findViewById(R.id.graph_holder);

		LineSeries lineSeries = new LineSeries(getActivity().getBaseContext());
		lineSeries.setCategoryBinding(new DataPointBinding() {
		    @Override
		    public Object getValue(Object o) {
		        return validDates.get(validDates.indexOf(o)).toString("MMM dd");
		    }
		});
		lineSeries.setValueBinding(new DataPointBinding() {
		    @Override
		    public Object getValue(Object o) {
		    	for (LocalDate intakeDate : mNutrient.getDatesIntook()) {
		    		if (intakeDate.getDayOfYear() == ((LocalDate)o).getDayOfYear()) {
		    			return mNutrient.getAmount().get(mNutrient.getDatesIntook().indexOf(o));
		    		}
		    	}
		    	return 0;
		    }
		});
		
		lineSeries.setData(validDates);
		lineSeries.setStrokeThickness(15);
		chartView.getSeries().add(lineSeries);
		
		CategoricalAxis horizontalAxis = new CategoricalAxis(getActivity().getBaseContext());
		horizontalAxis.setPlotMode(AxisPlotMode.ON_TICKS_PADDED);
		chartView.setHorizontalAxis(horizontalAxis);

		LinearAxis verticalAxis = new LinearAxis(getActivity().getBaseContext());
		verticalAxis.setMajorStep(20);
		chartView.setVerticalAxis(verticalAxis);
		lineSeries.setStrokeColor(Color.parseColor("#355d3d"));
		graph_holder.addView(chartView);
		
		//Pie Chart
		
		LinearLayout pie_holder = (LinearLayout) v.findViewById(R.id.pie_holder);
		RadPieChartView pieChartView = new RadPieChartView(getActivity().getBaseContext());

		PieSeries pieSeries = new PieSeries(getActivity().getBaseContext());
		pieSeries.setValueBinding(new DataPointBinding() {
		    @Override
		    public Object getValue(Object o) throws IllegalArgumentException {
		        double count = (double)mNutrient.getSourcesCount().get(mNutrient.getSources().indexOf(o));
		        String source_name = mNutrient.getSources().get(mNutrient.getSources().indexOf(o));
		        VitaminSet vitaminSources = Information.get(getActivity()).getVitaminSources().get(source_name.toUpperCase());
				MineralSet mineralSources = Information.get(getActivity()).getMineralSources().get(source_name.toUpperCase());
				int source_amount = getSourceAmount(mNutrient.getName(), vitaminSources, mineralSources);
				food_amounts.put(source_name, count*source_amount);
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
		for (int i = 0; i < sources.size(); i++) {
			total += (food_amounts.get(sources.get(i)));
		}
		ArrayList<String> topFive_sources = new ArrayList<String>();
		ArrayList<Double> topFive_amounts = new ArrayList<Double>();
		while (topFive_sources.size() != 5) {
			double currentMostCount = 0.0;
			String currentMost = "";
			for (int i = 0; i < sources.size(); i++) {
				if (sourcesCount.get(i) * food_amounts.get(sources.get(i)) > currentMostCount && !topFive_sources.contains(sources.get(i))) {
					currentMostCount = sourcesCount.get(i) * food_amounts.get(sources.get(i));
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
			topFive_string += ("<b>+</b> " + capitalizeFirstLetter(topFive_sources.get(i)) + " (" + ratio + "%)<br>");
		}
		topFive.setText(Html.fromHtml(topFive_string));
		
		return v;
	}
	
	public static String capitalizeFirstLetter(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
	public int getSourceAmount(String nutrient_name, VitaminSet vitamins, MineralSet minerals) {
		boolean isMineral = true;
		if (nutrient_name.toLowerCase().contains("vitamin")) {
			isMineral = false;
		}
		if (isMineral) {
			HashMap<String, Integer> mineral_amounts = minerals.getMineralAmount();
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
            sources_id = R.string.vitamin_A_sources;
            deficiency_id = R.string.vitamin_A_deficiency;
        } else if (nutrient_name.equals("Vitamin B1")) {
            description_id = R.string.vitamin_B1_description;
            sources_id = R.string.vitamin_B1_sources;
            deficiency_id = R.string.vitamin_B1_deficiency;
        } else if (nutrient_name.equals("Vitamin B2")) {
            description_id = R.string.vitamin_B2_description;
            sources_id = R.string.vitamin_B2_sources;
            deficiency_id = R.string.vitamin_B2_deficiency;
        } else if (nutrient_name.equals("Vitamin B3")) {
            description_id = R.string.vitamin_B3_description;
            sources_id = R.string.vitamin_B3_sources;
            deficiency_id = R.string.vitamin_B3_deficiency;
        } else if (nutrient_name.equals("Vitamin B5")) {
            description_id = R.string.vitamin_B5_description;
            sources_id = R.string.vitamin_B5_sources;
            deficiency_id = R.string.vitamin_B5_deficiency;
        } else if (nutrient_name.equals("Vitamin B6")) {
            description_id = R.string.vitamin_B6_description;
            sources_id = R.string.vitamin_B6_sources;
            deficiency_id = R.string.vitamin_B6_deficiency;
        } else if (nutrient_name.equals("Vitamin B9")) {
            description_id = R.string.vitamin_B9_description;
            sources_id = R.string.vitamin_B9_sources;
            deficiency_id = R.string.vitamin_B9_deficiency;
        } else if (nutrient_name.equals("Vitamin B12")) {
            description_id = R.string.vitamin_B12_description;
            sources_id = R.string.vitamin_B12_sources;
            deficiency_id = R.string.vitamin_B12_deficiency;
        } else if (nutrient_name.equals("Vitamin C")) {
            description_id = R.string.vitamin_C_description;
            sources_id = R.string.vitamin_C_sources;
            deficiency_id = R.string.vitamin_C_deficiency;
        } else if (nutrient_name.equals("Vitamin D")) {
            description_id = R.string.vitamin_D_description;
            sources_id = R.string.vitamin_D_sources;
            deficiency_id = R.string.vitamin_D_deficiency;
        } else if (nutrient_name.equals("Vitamin E")) {
            description_id = R.string.vitamin_E_description;
            sources_id = R.string.vitamin_E_sources;
            deficiency_id = R.string.vitamin_E_deficiency;
        } else if (nutrient_name.equals("Vitamin K")) {
            description_id = R.string.vitamin_K_description;
            sources_id = R.string.vitamin_K_sources;
            deficiency_id = R.string.vitamin_K_deficiency;
        } else if (nutrient_name.equals("Calcium")) {
            description_id = R.string.calcium_description;
            sources_id = R.string.calcium_sources;
            deficiency_id = R.string.calcium_deficiency;
        } else if (nutrient_name.equals("Iron")) {
            description_id = R.string.iron_description;
            sources_id = R.string.iron_sources;
            deficiency_id = R.string.iron_deficiency;
        } else if (nutrient_name.equals("Magnesium")) {
            description_id = R.string.magnesium_description;
            sources_id = R.string.magnesium_sources;
            deficiency_id = R.string.magnesium_deficiency;
        } else if (nutrient_name.equals("Phosphorus")) {
            description_id = R.string.phosphorus_description;
            sources_id = R.string.phosphorus_sources;
            deficiency_id = R.string.phosphorus_deficiency;
        } else if (nutrient_name.equals("Potassium")) {
            description_id = R.string.potassium_description;
            sources_id = R.string.potassium_sources;
            deficiency_id = R.string.potassium_deficiency;
        } else if (nutrient_name.equals("Sodium")) {
            description_id = R.string.sodium_description;
            sources_id = R.string.sodium_sources;
            deficiency_id = R.string.sodium_deficiency;
        } else if (nutrient_name.equals("Zinc")) {
            description_id = R.string.zinc_description;
            sources_id = R.string.zinc_sources;
            deficiency_id = R.string.zinc_deficiency;
        } else if (nutrient_name.equals("Copper")) {
            description_id = R.string.copper_description;
            sources_id = R.string.copper_sources;
            deficiency_id = R.string.copper_deficiency;
        } else if (nutrient_name.equals("Manganese")) {
            description_id = R.string.manganese_description;
            sources_id = R.string.manganese_sources;
            deficiency_id = R.string.manganese_deficiency;
        }
		int[] output = {description_id, sources_id, deficiency_id};
		return output;
	}
}