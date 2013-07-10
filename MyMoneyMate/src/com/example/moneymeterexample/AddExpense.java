/* *************************************************************************************
Copyright © 2013 Deepika Punyamurtula

This program is free software: you can redistribute it and/or modify it under 
the terms of the GNU General Public License as published by the Free Software Foundation, 
either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. 
If not, see http://www.gnu.org/licenses/.

Author - Deepika Punyamurtula
email: udeepika@pdx.edu

MyMoneyMate - An android application to keep a record of your expenses.
***************************************************************************************** */

package com.example.moneymeterexample;


import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.view.Menu;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
public class AddExpense extends Activity implements OnClickListener{
	
	private Button addExpense_btn,clear_button;
	Button show_cal;
	EditText amt,date,category,notes;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		
		addExpense_btn = (Button) findViewById(R.id.add_btn);
		clear_button = (Button) findViewById(R.id.clear_btn);
		show_cal = (Button) findViewById(R.id.show_calendar);
		addExpense_btn.setOnClickListener(this);
		clear_button.setOnClickListener(this);
		
		amt = (EditText) findViewById(R.id.amt_val);
		date = (EditText) findViewById(R.id.amt_date);
		category = (EditText) findViewById(R.id.amt_cat);
		notes = (EditText) findViewById(R.id.notes_txt);
		
		show_cal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		
		final Calendar cal = Calendar.getInstance();
       mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
 
        /** Display the current date in the TextView */
        updateDisplay();
		}
	
	/*private void selectDate(){
		this.date.setText(new StringBuilder().append(mMonth+1).append("/").append(mDay).append("/")
				.append(mYear).append(" "));
	} */

	
	private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
 
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    if (mMonth < 9){
                    	mMonth = Integer.parseInt("0" + (Integer.toString(mMonth)));
                    }
                    mDay = dayOfMonth;
                    if (mDay <9){
                    	//mDay = Integer.parseInt("0"+(Integer.toString(mDay)));
                    	
                    	
                    }
                    updateDisplay();
                    displayToast();
                }
            };
            private void updateDisplay() {
            	if(mMonth <9){
            		
            		if(mDay < 9){
                date.setText(
                    new StringBuilder()
                            // Month is 0 based so add 1
                            .append(0).append(mMonth + 1).append("/")
                            .append(0).append(mDay).append("/")
                            .append(mYear).append(" ")); 
            		
            }
            	else{
            		date.setText(
                            new StringBuilder()
                                    // Month is 0 based so add 1
                                    .append(0).append(mMonth + 1).append("/")
                                    .append(mDay).append("/")
                                    .append(mYear).append(" "));
            		
            	}
            }
            	else{
            		date.setText(
                            new StringBuilder()
                                    // Month is 0 based so add 1
                                    .append(mMonth + 1).append("/")
                                    .append(mDay).append("/")
                                    .append(mYear).append(" "));
            		
            	}
            	
            }
            private void displayToast() {
                Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(date.getText()),  Toast.LENGTH_SHORT).show();
                     
            }
            
            
            
            
            
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
		return true;
	}
	
/*	private DatePickerDialog.OnDateSetListener mDateSetListener =
		    new DatePickerDialog.OnDateSetListener() {
		        

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					mYear = year;
		            mMonth = monthOfYear;
		            mDay = dayOfMonth;
		            selectDate();
					
				}
		    };
	
		    protected Dialog onCreateDialog(int id) {
		    	   switch (id) {
		    	   case DATE_DIALOG_ID:
		    	      return new DatePickerDialog(this,
		    	                mDateSetListener,
		    	                mYear, mMonth, mDay);
		    	   }
		    	   return null;
		    	} */
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.add_btn:
			if(amt.getText().toString().equals("")||date.getText().toString().equals("")){
				//Toast.makeText(AddExpense.class, "Please add the required fields..", Toast.LENGTH_LONG).show() ;
				Toast.makeText(AddExpense.this, "PLease add values...", Toast.LENGTH_LONG).show();
			}
			else{
				DataBaseHelper db = new DataBaseHelper(getApplicationContext());
				db.getWritableDatabase();
				ExpenseEntry ex = new ExpenseEntry();
				ex.amount = Integer.parseInt(amt.getText().toString());
				ex.category = category.getText().toString();
				ex.date = date.getText().toString();
				Log.i("amount,date,category" ,  ex.amount + "" + ex.date + "" + ex.category);
				db.addExpenseEntry(ex);
				Toast.makeText(AddExpense.this, "Record added successfully...", Toast.LENGTH_LONG).show();
				
			}
			break;
		case R.id.clear_btn:
				amt.setText("");
				date.setText("");
				category.setText("");
				notes.setText("");
				break;
			default:
				break;
		}
		
	}
		
	

protected Dialog onCreateDialog(int id) {
    switch (id) {
    case DATE_DIALOG_ID:
        return new DatePickerDialog(this,
                    pDateSetListener,
                    mYear, mMonth, mDay);
    }
    return null;
}
}
