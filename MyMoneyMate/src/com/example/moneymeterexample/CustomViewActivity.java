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
References:
***************************************************************************************** */
package com.example.moneymeterexample;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class CustomViewActivity extends Activity implements OnClickListener,OnItemSelectedListener {
	
	static EditText from_date,to_date;
	Spinner category;
	Button from_btn,to_btn,view_btn,view_chart_btn;
	private int fromYear,toYear;
	private int fromMonth,toMonth;
	private int fromDay,toDay; 
	public static final int VIEW_CUSTOM_ID =3;
	static final  int FROM_DATE_ID=4;
	static final int TO_DATE_ID=5;
	static ArrayList<String> category_list;
	static String category_val;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_view);
		from_date = (EditText)findViewById(R.id.from_txt);
		to_date = (EditText)findViewById(R.id.to_txt);
		from_btn = (Button) findViewById(R.id.cal1);
		from_btn.setOnClickListener(this);
		to_btn = (Button) findViewById(R.id.cal2);
		to_btn.setOnClickListener(this);
		view_btn = (Button) findViewById(R.id.custom_view_btn);
		view_btn.setOnClickListener(this);
		category = (Spinner)findViewById(R.id.spinner1);
		category.setOnItemSelectedListener(this);
		loadCategorySpinnerValues();
		final Calendar cal_from = Calendar.getInstance();
		final Calendar cal_to = Calendar.getInstance();
		fromYear = cal_from.get(Calendar.YEAR);
		toYear = cal_to.get(Calendar.YEAR);
        fromMonth = cal_from.get(Calendar.MONTH);
        toMonth = cal_to.get(Calendar.MONTH);
        fromDay = cal_from.get(Calendar.DAY_OF_MONTH);
        toDay = cal_to.get(Calendar.DAY_OF_MONTH);
        view_chart_btn = (Button)findViewById(R.id.pie_chart_btn);
        view_chart_btn.setOnClickListener(this);
        view_chart_btn.setClickable(false);
        /** Display the current date in the TextView */
        updateFromDisplay();
        updateToDisplay();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom_view, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.cal1:
			showDialog(FROM_DATE_ID);
			break;
		case R.id.cal2:
			
			showDialog(TO_DATE_ID);
			break;
		case R.id.custom_view_btn:
			Intent viewExpenseIntent = new Intent(CustomViewActivity.this,ViewExpenseActivity.class);
			viewExpenseIntent.putExtra("view_by", VIEW_CUSTOM_ID);
			viewExpenseIntent.putExtra("cat_value", category_val);
			viewExpenseIntent.putExtra("from_date", from_date.getText().toString());
			viewExpenseIntent.putExtra("to_date", to_date.getText().toString());
			startActivity(viewExpenseIntent);
			break;
			
		case R.id.pie_chart_btn:
			Intent view_chart_intent = new Intent(CustomViewActivity.this,AChartEnginePieChartActivity.class);
			view_chart_intent.putExtra("from_date", from_date.getText().toString());
			view_chart_intent.putExtra("to_date", to_date.getText().toString());
			view_chart_intent.putExtra("is_custom", true);
			startActivity(view_chart_intent);
		}
		
	}
	
	
	private DatePickerDialog.OnDateSetListener fromDateSetListener =
			new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year,
				int monthOfYear, int dayOfMonth) {
			fromYear = year;
			fromMonth = monthOfYear;
			fromDay = dayOfMonth;

			updateFromDisplay();

		}
	};
	            
	            
	private DatePickerDialog.OnDateSetListener toDateSetListener =
			new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year,
				int monthOfYear, int dayOfMonth) {
			toYear = year;
			toMonth = monthOfYear;
			toDay = dayOfMonth;

			updateToDisplay();

		}
	};
	private void updateFromDisplay() {

		if(fromMonth <9){

			if(fromDay < 9){
				from_date.setText(
						new StringBuilder()
						// Appending 0 to month and day for the  format MM/DD/YYYY
						.append(fromYear).append("-")
						.append(0).append(fromMonth + 1).append("-")
						.append(0).append(fromDay)
						.append(" ")); 
			}

			else     	{
				from_date.setText(
						// Appending 0 to month for the format MM/DD/YYYY
						new StringBuilder()
						.append(fromYear).append("-").append(0).append(fromMonth + 1).append("-")
						.append(fromDay)
						.append(" "));

			}
		}
		else{
			from_date.setText(
					new StringBuilder()
					// Month is 0 based so add 1
					.append(fromYear).append("-").append(fromMonth + 1).append("-")
					.append(fromDay)
					.append(" "));

		}

	}


	private void updateToDisplay() {
		if(toMonth <9){

			if(toDay < 9){
				to_date.setText(
						new StringBuilder()
						// Appending 0 to month and day for the  format MM/DD/YYYY
						.append(toYear).append("-")
						.append(0).append(toMonth + 1).append("-")
						.append(0).append(toDay)
						.append(" ")); 
			}

			else     	{
				to_date.setText(
						// Appending 0 to month for the format MM/DD/YYYY
						new StringBuilder()
						.append(toYear).append("-").append(0).append(toMonth + 1).append("-")
						.append(toDay)
						.append(" "));

			}
		}
		else{
			to_date.setText(
					new StringBuilder()
					// Month is 0 based so add 1
					.append(toYear).append("-").append(toMonth + 1).append("-")
					.append(toDay)
					.append(" "));

		}

	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		category_val = parent.getItemAtPosition(position).toString();	
		if(category_val.equals("All")){
			view_chart_btn.setClickable(true);
			view_chart_btn.setVisibility(View.VISIBLE);
			
		}
	}
			

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	private void loadCategorySpinnerValues(){
		DataBaseHelper db;
		db = new DataBaseHelper(this);

		category_list = db.getCategories();
		category_list.add("All");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, category_list);
		dataAdapter
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(dataAdapter);
		category.setPrompt("Choose a category");

	}

	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case FROM_DATE_ID:
			return new DatePickerDialog(this,
					fromDateSetListener,
					fromYear, fromMonth, fromDay);
		case TO_DATE_ID:
			return new DatePickerDialog(this,
					toDateSetListener,
					toYear, toMonth, toDay);
		}
		return null;
	}

	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {

		case FROM_DATE_ID:
			((DatePickerDialog) dialog).updateDate(fromYear, fromMonth, fromDay);
			break;
		case TO_DATE_ID:
			((DatePickerDialog) dialog).updateDate(toYear, toMonth, toDay);
			break;
		}
	} 

	public void onBackPressed(){
		Intent viewOptionsIntent = new Intent(CustomViewActivity.this,ViewExpensesOptionsActivity.class);
		startActivity(viewOptionsIntent);

	}
}