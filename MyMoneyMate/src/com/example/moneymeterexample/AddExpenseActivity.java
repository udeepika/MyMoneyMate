/* ******************************************************************************************************
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
* References: http://www.verious.com/article/how-to-create-date-picker-dialog-for-selecting-a-date-in-android/
*			  http://www.dcpagesapps.com/developer-resources/android/21-android-tutorial-spinners?start=2
* 			  http://www.androidhive.info/2012/06/android-populating-spinner-data-from-sqlite-database/
*             http://developer.android.com/reference/android/content/Intent.html
*             http://marakana.com/forums/android/examples/65.html
			
********************************************************************************************************** */


/*This activity is used to add or edit expenses.  */

package com.example.moneymeterexample;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.view.Menu;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
public class AddExpenseActivity extends Activity implements OnClickListener,OnItemSelectedListener{

	private Button addExpense_btn,clear_button,back_button;
	Button show_cal;
	EditText amt,date,notes;
	Spinner category;
	String category_val;
	TextView title_text;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
	static final int NEW_CATEGORY_ID = 100;
	static final int DELETE_CONFIRM_ID = 200;
	public static boolean IS_ADD = true;
	static ArrayList<String> cat_list ;
	public  int row_id,view_by;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		IS_ADD = getIntent().getBooleanExtra("IS_ADD", true);
		
		String amount_val = getIntent().getStringExtra("amount");
		int view_by = getIntent().getIntExtra("view_by", 0);
		String date_val = getIntent().getStringExtra("date");
		String category_val = getIntent().getStringExtra("category");
		String notes_val = getIntent().getStringExtra("notes");
		int row_id =getIntent().getIntExtra("_id", 0);
		
		addExpense_btn = (Button) findViewById(R.id.add_btn);
		clear_button = (Button) findViewById(R.id.clear_btn);
		addExpense_btn.setOnClickListener(this);
		clear_button.setOnClickListener(this);
		title_text = (TextView)findViewById(R.id.textView4);
		amt = (EditText) findViewById(R.id.amt_val);
		date = (EditText) findViewById(R.id.amt_date);
		category = (Spinner) findViewById(R.id.amt_cat);
		category.setOnItemSelectedListener(this);
		notes = (EditText) findViewById(R.id.notes_txt);
		show_cal = (Button) findViewById(R.id.show_calendar);
		show_cal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		
		loadCategorySpinnerValues();
		
		/* If this activity is reached from the listView of Expenses, used to edit/delete the expenses */
		if (!IS_ADD){
			title_text.setText("Edit Expense");
			addExpense_btn.setText("Save");
			clear_button.setText("Delete");
			System.out.println( notes_val + "" + category_val +"" + date_val);
			amt.setText(amount_val);
			date.setText(date_val);
			String date[] = date_val.split("-");
			mYear = Integer.parseInt(date[0]);
			mMonth = Integer.parseInt(date[1])-1;
			mDay = Integer.parseInt(date[2].trim());
			notes.setText(notes_val); 
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
						.append(mYear).append("-")
						.append(0).append(mMonth + 1).append("-")
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
					// Month is 0 based so add 1 for the format YYYY-MM-DD
					.append(mYear).append("-").append(mMonth + 1).append("-")
					.append(mDay)
					.append(" "));

		}

	}
	private void displayToast() {
		Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(date.getText()),  Toast.LENGTH_SHORT).show();

	}



	/* Get the category column values into the Spinner */
	private void loadCategorySpinnerValues(){
		DataBaseHelper db;
		db = new DataBaseHelper(this);
		cat_list = db.getCategories();
		if(cat_list.size()==0)
			cat_list.add("No Selection");
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

		IS_ADD = getIntent().getBooleanExtra("IS_ADD", true);
		int row_id = getIntent().getIntExtra("_id", 0);
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.add_btn:
			/* if used as add button */
			if(IS_ADD){

				if(amt.getText().toString().equals("")||date.getText().toString().equals("")||category.getSelectedItem().equals("No Selection")){

					Toast.makeText(AddExpenseActivity.this, "Please add values...", Toast.LENGTH_LONG).show();
					if(category.getSelectedItem().equals("No Selection"))
						Toast.makeText(AddExpenseActivity.this, "Please select a category..", Toast.LENGTH_LONG).show();
				}
				else{
					DataBaseHelper db = new DataBaseHelper(getApplicationContext());			
					db.getWritableDatabase();
					int rowid = db.getTotalRecords()+1;
					ExpenseEntry ex = new ExpenseEntry();
					ex.amount = Float.parseFloat((amt.getText().toString().replace(",", "")));
					ex.category = category.getSelectedItem().toString();
					ex.date = date.getText().toString();
					ex.notes = notes.getText().toString();
					ex._id = rowid;
					Log.i("amount,date,category" ,  ex.amount + "" + ex.date + "" + ex.category + " " + ex.notes+ ex._id);
					db.addExpenseEntry(ex);
					Toast.makeText(AddExpenseActivity.this, "Record added successfully!!", Toast.LENGTH_LONG).show();
					db.close();
				}
			}
			/* if used as edit button */
			else{
				DataBaseHelper db = new DataBaseHelper(getApplicationContext());
				db.getWritableDatabase();
				ExpenseEntry ex = new ExpenseEntry();
				ex.amount = Float.parseFloat(amt.getText().toString().replace(",", ""));
				ex.category = category.getSelectedItem().toString();
				ex.date = date.getText().toString();
				ex.notes = notes.getText().toString();
				ex._id = row_id;
				Log.i("amount,date,category" ,  ex.amount + "" + ex.date + "" + ex.category + " " + ex.notes+ "" + ex._id);
				if (db.editExpenseEntry(ex) > 0)
					Toast.makeText(AddExpenseActivity.this, "Record updated successfully!!", Toast.LENGTH_LONG).show();
				db.close();
			}
			break;


		case R.id.clear_btn:
			if(IS_ADD){
				amt.setText("");
				date.setText("");
				//category.setSelection(0);
				((BaseAdapter) category.getAdapter()).notifyDataSetChanged();
				notes.setText("");
			}
			
			/*Delete confirmation Dialog */
			else{
				
				showDialog(DELETE_CONFIRM_ID);

			}
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

				}
			});
			new_cat_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();

				}
			});

			return new_cat_dialog.create();


		case DELETE_CONFIRM_ID:
			AlertDialog.Builder delete_dialog = new AlertDialog.Builder(this);
			delete_dialog.setMessage("Are you sure you want to delete this entry?");
			delete_dialog.setTitle("Delete Confirmation");
			delete_dialog.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				@Override	
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					deleteRecord();
					amt.setText("");
					date.setText("");
					cat_list.remove(category.getSelectedItem().toString());
					((BaseAdapter) category.getAdapter()).notifyDataSetChanged();
					notes.setText("");
					
					
				}
			});
			delete_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
					
				}
			});


			return delete_dialog.create();

		}
		return null;
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

		String category_val = parent.getItemAtPosition(position).toString();
		if(category_val.equals("New..."))
		{

			showDialog(NEW_CATEGORY_ID);

		}	
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public void deleteRecord(){
		row_id = getIntent().getIntExtra("_id", 0);
		DataBaseHelper db = new DataBaseHelper(getApplicationContext());
		db.getWritableDatabase();
		ExpenseEntry ex = new ExpenseEntry();
		ex.amount = Float.parseFloat((amt.getText().toString().replace(",", "")));
		ex.category = category.getSelectedItem().toString();
		ex.date = date.getText().toString();
		ex.notes = notes.getText().toString();
		ex._id = row_id;
		Log.i("amount,date,category" ,  ex.amount + "" + ex.date + "" + ex.category + " " + ex.notes+ "" + ex._id);
		if(db.deleteRecord(ex))
			Toast.makeText(AddExpenseActivity.this, "Record deleted successfully!!", Toast.LENGTH_LONG).show(); 
		db.close(); 
	}

	
	
	
}
