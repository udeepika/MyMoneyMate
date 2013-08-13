/* *************************************************************************************
* Copyright © 2013 Deepika Punyamurtula
* MyMoneyMate - Is an Open Source Android application to keep a record of your expenses.
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
*
***************************************************************************************** */


package com.example.moneymeterexample;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ViewByDateActivity extends Activity implements OnClickListener, android.view.View.OnClickListener {
	Button show_cal,view_chart;
	private int mYear;
	private int mMonth;
	private int mDay;
	EditText date;
	Button view_btn;
	public static final int VIEW_BY_DATE_ID = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_by_date);
		date = (EditText)findViewById(R.id.date_txt);
		view_btn = (Button)findViewById(R.id.view_for_date_btn);
		view_btn.setOnClickListener(this);
		view_chart = (Button) findViewById(R.id.view_chart);
		view_chart.setOnClickListener(this);
		show_cal = (Button) findViewById(R.id.show_calendar);
		show_cal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(AddExpenseActivity.DATE_DIALOG_ID);
			}
		});

		final Calendar cal = Calendar.getInstance();
		mYear = cal.get(Calendar.YEAR);
		mMonth = cal.get(Calendar.MONTH);
		mDay = cal.get(Calendar.DAY_OF_MONTH);

		/** Display the current date in the TextView */
		updateDisplay();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_by_date, menu);
		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub


	}

	private DatePickerDialog.OnDateSetListener pDateSetListener =
			new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year,
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;

			updateDisplay();
			displayToast();
		}
	};
	private void updateDisplay() {
		if(mMonth < 9){

			if(mDay <= 9){
				date.setText(
						new StringBuilder()
						// Appending 0 to month and day for the  format YYYY-MM-DD
						.append(mYear).append("-").append(0).append(mMonth + 1).append("-")
						.append(0).append(mDay)
						.append(" ")); 
			}

			else     	{
				date.setText(
						// Appending 0 to month for the format YYYY-MM-DD
						new StringBuilder()
						.append(mYear).append("-").append(0).append(mMonth + 1).append("-")
						.append(mDay)
						.append(" "));

			}
		}
		else{
			date.setText(
					new StringBuilder()
					// Month is 0 based so add 1
					.append(mYear).append("-").append(mMonth + 1).append("-")
					.append(mDay)
					.append(" "));

		}

	}
	private void displayToast() {
		Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(date.getText()),  Toast.LENGTH_SHORT).show();

	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case AddExpenseActivity.DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					pDateSetListener,
					mYear, mMonth, mDay);
		}

		return null;

	}

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.view_for_date_btn:
			Intent view_intent = new Intent(ViewByDateActivity.this,ViewExpenseActivity.class );
			view_intent.putExtra("date_val",date.getText().toString());
			view_intent.putExtra("view_by", VIEW_BY_DATE_ID);
			startActivity(view_intent);
			break;
		
		case R.id.view_chart:
			Intent viewchartIntent = new Intent(ViewByDateActivity.this,AChartEnginePieChartActivity.class);
			viewchartIntent.putExtra("date", date.getText().toString());
			viewchartIntent.putExtra("is_date", true);
			startActivity(viewchartIntent);
		}
	}
	

}