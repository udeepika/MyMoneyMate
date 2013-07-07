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

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddExpense extends Activity implements OnClickListener{
	
	private Button addExpense_btn;
	EditText amt,date,category;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		
		addExpense_btn = (Button) findViewById(R.id.add_btn);
		addExpense_btn.setOnClickListener(this);
		amt = (EditText) findViewById(R.id.amt_val);
		date = (EditText) findViewById(R.id.amt_date);
		category = (EditText) findViewById(R.id.amt_cat);
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
			default:
				break;
		}
		
	}
		
	}


