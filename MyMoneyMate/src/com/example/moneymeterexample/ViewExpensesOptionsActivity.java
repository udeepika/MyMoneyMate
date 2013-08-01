/* *************************************************************************************
* MyMoneyMate - Is an Open Source Android application to keep a record of your expenses.
* Copyright © 2013 Deepika Punyamurtula
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
 
***************************************************************************************** */
package com.example.moneymeterexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ViewExpensesOptionsActivity extends Activity implements OnClickListener{

	Button view_all, view_by_date, view_by_cat, view_by_month, view_custom,view_chart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_expenses_options);
		Button view_all = (Button)findViewById(R.id.ViewAll_btn);
		view_all.setOnClickListener(this);
		Button view_by_date = (Button) findViewById(R.id.ViewByDate_btn);
		view_by_date.setOnClickListener(this);
		Button view_by_cat = (Button)findViewById(R.id.ViewByCat_btn);
		view_by_cat.setOnClickListener(this);
		Button view_chart = (Button)findViewById(R.id.view_chart);
		view_chart.setOnClickListener(this);
		Button view_custom = (Button) findViewById(R.id.CustomView_btn);
		view_custom.setOnClickListener(this);

	} ;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_expenses_options, menu);
		return true;
	}

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ViewAll_btn:
			Intent viewIntent = new Intent(ViewExpensesOptionsActivity.this,ViewExpenseActivity.class);
			startActivity(viewIntent);
			//onDestroy();}
			break;

		case R.id.ViewByCat_btn:
			Intent viewbyCatIntent = new Intent(ViewExpensesOptionsActivity.this,ViewByCategoryActivity.class);
			startActivity(viewbyCatIntent);
			break;

		case R.id.ViewByDate_btn:
			Intent viewbyDateIntent = new Intent(ViewExpensesOptionsActivity.this,ViewByDateActivity.class);
			startActivity(viewbyDateIntent);
			break;

		case R.id.CustomView_btn:
			Intent viewCustomIntent = new Intent(ViewExpensesOptionsActivity.this,CustomViewActivity.class);
			startActivity(viewCustomIntent);
			break;

		case R.id.view_chart:
			Intent viewChartIntent = new Intent(ViewExpensesOptionsActivity.this,AChartEnginePieChartActivity.class);
			startActivity(viewChartIntent);
			break;
		}
	}

	public void onDestroy() {
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	}   	

	public void onBackPressed(){

		Intent mainActivity = new Intent(ViewExpensesOptionsActivity.this,MainActivity.class);
		startActivity(mainActivity);
	}
}
