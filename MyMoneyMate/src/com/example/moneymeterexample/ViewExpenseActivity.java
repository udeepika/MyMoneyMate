/* *************************************************************************************
Copyright � 2013 Deepika Punyamurtula

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
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import android.app.ListActivity;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//import android.widget.TableLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class ViewExpenseActivity extends ListActivity implements OnClickListener{
	
	static final ArrayList<HashMap<String,String>> list = 
		    new ArrayList<HashMap<String,String>>(); 
	//DataBaseHelper db;
	ListView lv;
	TextView txt_id;
	Button back_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_expense);
		back_btn = (Button)findViewById(R.id.back_btn);
		back_btn.setOnClickListener(this);
		System.out.println("Fine till here ");
		SimpleAdapter sd = new SimpleAdapter(this, list, R.layout.table_row, new String[]{"date", "category", "amount","select"},
		new int[] {R.id.DATE_CELL,R.id.CATEGORY_CELL,R.id.AMOUNT_CELL,R.id.SELECT_ICON}); 
		
		fillListView();
		setListAdapter(sd);
		System.out.println("Fine after db...");
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_expense, menu);
		return true;
	}

	
	
	private void fillListView() {
		DataBaseHelper db;
		
		// TODO Auto-generated method stub
		
		db = new DataBaseHelper(this);
		ArrayList<ExpenseEntry> exp_list = new ArrayList<ExpenseEntry>();
		exp_list = db.getExpenses();
		for(int i =0;i<exp_list.size();i++){
			//Button b = new Button(this);
			HashMap<String,String> temp = new HashMap<String,String>();
			
			temp.put("_id",Integer.toString(exp_list.get(i).getId()));
			temp.put("date",exp_list.get(i).getDate().toString());
			temp.put("category",exp_list.get(i).category.toString());
			temp.put("amount", Integer.toString(exp_list.get(i).getAmount()));
			temp.put("notes", exp_list.get(i).getNotes().toString());
		    
		    list.add(temp); 
		    
		    
		}
		
		db.close();
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case (R.id.back_btn):
			Intent addIntent = new Intent(ViewExpenseActivity.this,MainActivity.class);
			startActivity(addIntent);
			finish();
			
			break;
		default:
			break;
				
		}
		
	}  
	
	public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }   
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    System.out.println("The item clicked is ");
	    Intent edit_intent = new Intent(getApplicationContext(),AddExpense.class);
	    edit_intent.putExtra("IS_ADD",false);
	    edit_intent.putExtra("amount", list.get(position).get("amount"));
	    System.out.println("The amount is "+list.get(position).get("amount") );
	    edit_intent.putExtra("date", list.get(position).get("date"));
	    edit_intent.putExtra("category", list.get(position).get("category"));
	    edit_intent.putExtra("notes", list.get(position).get("notes"));
	    edit_intent.putExtra("_id", Integer.parseInt(list.get(position).get("_id").toString()));
	    startActivity(edit_intent);
	}

}
