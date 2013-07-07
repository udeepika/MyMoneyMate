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


