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


import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.view.Menu;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
public class AddExpense extends Activity implements OnClickListener,OnItemSelectedListener{
	
	private Button addExpense_btn,clear_button;
	Button show_cal;
	EditText amt,date,notes;
	Spinner category;
	String category_val;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
	static final int NEW_CATEGORY_ID = 100;
	public static boolean IS_ADD = true;
	static ArrayList<String> cat_list ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		
		boolean IS_ADD = getIntent().getBooleanExtra("IS_ADD", true);
		String amount_val = getIntent().getStringExtra("amount");
    	String date_val = getIntent().getStringExtra("date");
    	String category_val = getIntent().getStringExtra("category");
    	String notes_val = getIntent().getStringExtra("notes");
    	
		addExpense_btn = (Button) findViewById(R.id.add_btn);
		clear_button = (Button) findViewById(R.id.clear_btn);
		show_cal = (Button) findViewById(R.id.show_calendar);
		addExpense_btn.setOnClickListener(this);
		clear_button.setOnClickListener(this);
		amt = (EditText) findViewById(R.id.amt_val);
		date = (EditText) findViewById(R.id.amt_date);
		category = (Spinner) findViewById(R.id.amt_cat);
		category.setOnItemSelectedListener(this);
		notes = (EditText) findViewById(R.id.notes_txt);
		//ArrayList<String> cat_list = new ArrayList<String>();
		show_cal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		
		
        loadCategorySpinnerValues();
        
        /* If this activity is reached from the listView of Expenses */
        if (!IS_ADD){
        	addExpense_btn.setText("Edit");
        	clear_button.setText("Delete");
        	System.out.println( notes_val + "" + category_val +"" + date_val);
        	amt.setText(amount_val);
        	date.setText(date_val);
        	String date[] = date_val.split("/");
        	mMonth = Integer.parseInt(date[0])-1;
        	mDay = Integer.parseInt(date[1]);
        	mYear = Integer.parseInt(date[2].trim());
        	notes.setText(notes_val); 
        	System.out.println(cat_list.indexOf(category_val.toString()));
        	category.setSelection(cat_list.indexOf(category_val.toString()));
        	
        }
        
        
        else{
        	final Calendar cal = Calendar.getInstance();
    		mYear = cal.get(Calendar.YEAR);
            mMonth = cal.get(Calendar.MONTH);
            mDay = cal.get(Calendar.DAY_OF_MONTH);
     
            /** Display the current date in the TextView */
            updateDisplay();
        }
		}
	

	// Reference : http://www.verious.com/article/how-to-create-date-picker-dialog-for-selecting-a-date-in-android/
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
         if(mMonth <9){
            		
            if(mDay < 9){
                	date.setText(
                	new StringBuilder()
                    // Appending 0 to month and day for the  format MM/DD/YYYY
                    .append(0).append(mMonth + 1).append("/")
                    .append(0).append(mDay).append("/")
                    .append(mYear).append(" ")); 
            			}
            
            else     	{
            		date.setText(
            		// Appending 0 to month for the format MM/DD/YYYY
            		new StringBuilder()
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
            
            
            
            
    private void loadCategorySpinnerValues(){
    	DataBaseHelper db;
    	db = new DataBaseHelper(this);
    	
    	cat_list = db.getCategories();
    	
    	cat_list.add("New...");
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cat_list);
    	dataAdapter
        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	category.setAdapter(dataAdapter);
    	category.setPrompt("Choose a category");
    	
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.add_expense, menu);
	return true;
	}
	

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.add_btn:
			if(IS_ADD){
			if(amt.getText().toString().equals("")||date.getText().toString().equals("")){
				
				Toast.makeText(AddExpense.this, "PLease add values...", Toast.LENGTH_LONG).show();
			}
			else{
				DataBaseHelper db = new DataBaseHelper(getApplicationContext());
				db.getWritableDatabase();
				ExpenseEntry ex = new ExpenseEntry();
				ex.amount = Integer.parseInt(amt.getText().toString());
				ex.category = category.getSelectedItem().toString();
				ex.date = date.getText().toString();
				ex.notes = notes.getText().toString();
				Log.i("amount,date,category" ,  ex.amount + "" + ex.date + "" + ex.category + " " + ex.notes);
				db.addExpenseEntry(ex);
				Toast.makeText(AddExpense.this, "Record added successfully!!", Toast.LENGTH_LONG).show();
				
			}
			}
			else{}
			break;
		case R.id.clear_btn:
			if(IS_ADD){
				amt.setText("");
				date.setText("");
				category.setSelection(0);
				notes.setText("");
			}
			else{}
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
				
		case NEW_CATEGORY_ID:
			AlertDialog.Builder new_cat_dialog = new AlertDialog.Builder(this);
			new_cat_dialog.setMessage("Enter the name of new category");
			new_cat_dialog.setTitle("New Category");
			final EditText new_cat_txt = new EditText(this);
			new_cat_dialog.setView(new_cat_txt);
			new_cat_dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String cat = new_cat_txt.getText().toString();
					
					String last = cat_list.remove((cat_list.size())-1);
					cat_list.add(cat);
					cat_list.add(last);
					((BaseAdapter) category.getAdapter()).notifyDataSetChanged();
					System.out.println("Item selected is :"+category.getSelectedItem());
				}
			});
			new_cat_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
					category.setSelection(0);
				}
			});
			
			AlertDialog alert = new_cat_dialog.create();
			alert.show();
			
					}
		
			
		return null;
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	// TODO Auto-generated method stub
	String category_val = parent.getItemAtPosition(position).toString();
	if(category_val.equals("New...")){
		System.out.println("Category value is New...");
		showDialog(NEW_CATEGORY_ID);
	}
		
	
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
		
		}
    }
