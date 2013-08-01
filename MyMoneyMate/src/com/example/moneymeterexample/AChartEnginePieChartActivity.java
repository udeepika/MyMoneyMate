/* *************************************************************************************
* MyMoneyMate - Is an Open Source Android application to keep a record of your expenses.
* Copyright � 2013 Deepika Punyamurtula
* This program is free software: you can redistribute it and/or modify it under 
* the terms of the GNU General Public License as published by the Free Software Foundation, 
* either version 3 of the License, or (at your option) any later version.

* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
* See the GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along with this program.
* If not, see http://www.gnu.org/licenses/.
* Please see the file "License" in this distribution for license terms. 
* Below is the link to the License file:
* https://github.com/udeepika/MyMoneyMate/blob/master/License.txt
*
* Author - Deepika Punyamurtula
* email: udeepika@pdx.edu
* Link to repository- https://github.com/udeepika/MyMoneyMate
* Reference : http://v4all123.blogspot.com/2013/03/piechart-using-achartengine.html 
***************************************************************************************** */


package com.example.moneymeterexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.achartengine.ChartFactory;  
import org.achartengine.GraphicalView;  
import org.achartengine.model.CategorySeries;  
import org.achartengine.model.SeriesSelection;  
import org.achartengine.renderer.DefaultRenderer;  
import org.achartengine.renderer.SimpleSeriesRenderer;  
import android.graphics.Color;  
import android.view.View;  
import android.view.ViewGroup.LayoutParams;  
import android.widget.LinearLayout;  
import android.widget.Toast;  
  
public class AChartEnginePieChartActivity extends Activity {   
  
	private CategorySeries mSeries = new CategorySeries("");  
	private HashMap<String,Float> chart_values;// = new HashMap<String,Float>();
	private static int[] COLORS = {Color.BLUE,Color.RED,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.GRAY,Color.YELLOW,
		Color.LTGRAY,Color.DKGRAY,Color.WHITE,Color.rgb(0, 0, 0)};  
	private DefaultRenderer mRenderer = new DefaultRenderer();  
	private static boolean is_custom = false; 
	private GraphicalView mChartView;  
  
	@Override  
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.activity_achart_engine_pie_chart); 
		DataBaseHelper db;
		db = new DataBaseHelper(this);
		is_custom = getIntent().getBooleanExtra("is_custom", false);
		if(is_custom)
		{
			String from = getIntent().getStringExtra("from_date");
			String to = getIntent().getStringExtra("to_date");
			chart_values=db.getChartBetweenDates(from,to);	
			System.out.println("Reached between dates");
		}
		else
			chart_values=db.getExpenseforChart(); 
		int i = 0;
		Iterator values = chart_values.entrySet().iterator();
		float[] VALUES=new float[chart_values.size()]; 
		ArrayList<String> CATEGORY_LIST = new ArrayList<String>();
		
		while(values.hasNext())
		{
			Map.Entry entry = (Map.Entry)values.next();
			VALUES[i]=(Float) entry.getValue();
			CATEGORY_LIST.add((String) entry.getKey());
			i++;
		} 

		mRenderer.setApplyBackgroundColor(true);  
		mRenderer.setBackgroundColor(Color.WHITE);  
		mRenderer.setChartTitleTextSize(20);  
		mRenderer.setLabelsTextSize(17);  
		mRenderer.setLegendTextSize(17); 
		mRenderer.setLabelsColor(Color.RED);
		mRenderer.setChartTitle("Expenses");
		mRenderer.setChartTitleTextSize(25);
		mRenderer.setMargins(new int[] { 20, 30, 15, 0 });  
		mRenderer.setZoomButtonsVisible(true);  
		mRenderer.setStartAngle(90);  
  
		for (int i1 = 0; i1 < VALUES.length; i1++) 
		{  
			mSeries.add(CATEGORY_LIST.get(i1) + " " + VALUES[i1], VALUES[i1]);  
			SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();  
			renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);  
			mRenderer.addSeriesRenderer(renderer);  
		}  
  
		if (mChartView != null) {  
			mChartView.repaint();  
		}  
  
	}  
  
	@Override  
	protected void onResume() {  
		super.onResume();  
		if (mChartView == null) {  
			LinearLayout layout = (LinearLayout) findViewById(R.id.chart);  
			mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);  
			mRenderer.setClickEnabled(true);  
			mRenderer.setSelectableBuffer(10);  
			mChartView.setOnClickListener(new View.OnClickListener() {  
				@Override  
				public void onClick(View v) {  
					SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();  

					if (seriesSelection == null)   
						Toast.makeText(AChartEnginePieChartActivity.this,"No chart element was clicked",Toast.LENGTH_SHORT).show();  
					else  
						Toast.makeText(AChartEnginePieChartActivity.this,"Chart element data point index "+ (seriesSelection.getPointIndex()+1) + " was clicked" + " point value="+ seriesSelection.getValue(), Toast.LENGTH_SHORT).show();  

				}  
			});  
  
			mChartView.setOnLongClickListener(new View.OnLongClickListener() {  
				@Override  
				public boolean onLongClick(View v) {  
					SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();  
					if (seriesSelection == null) {  
						Toast.makeText(AChartEnginePieChartActivity.this,"No chart element was long pressed", Toast.LENGTH_SHORT);  
						return false;   
					} else {  
						Toast.makeText(AChartEnginePieChartActivity.this,"Chart element data point index "+ seriesSelection.getPointIndex()+ " was long pressed",Toast.LENGTH_SHORT);  
						return true;         
					}  
				}  
			});  
			layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));  
		}  
		else   
			mChartView.repaint();  

	} 

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.achart_engine_pie_chart, menu);
		return true;
	}
}  