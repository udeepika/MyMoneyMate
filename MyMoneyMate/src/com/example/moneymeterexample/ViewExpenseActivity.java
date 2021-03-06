/* ********************************************************************************************************************************
* Copyright � 2013 Deepika Punyamurtula
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
* References: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/
* 			  http://stackoverflow.com/questions/9280965/arrayadapter-requires-the-resource-id-to-be-a-textview-xml-problems
* 			  http://devtut.wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/
* 			  http://www.technotalkative.com/android-multi-column-listview/
* 			  http://www.ezzylearning.com/tutorial.aspx?tid=1763429
* 			  http://saigeethamn.blogspot.com/2010/04/custom-listview-android-developer.html
* 			  http://www.heikkitoivonen.net/blog/2009/02/15/multicolumn-listview-in-android/
* 			  http://stackoverflow.com/questions/2538787/how-to-display-an-output-of-float-data-with-2-decimal-places-in-java
********************************************************************************************************************************** */
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

import java.text.DecimalFormat;
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
import java.util.*;

public class ViewExpenseActivity extends ListActivity implements OnClickListener{

	static final ArrayList<HashMap<String,String>> list = 
			new ArrayList<HashMap<String,String>>(); 
	ListView lv;
	TextView txt_id,total;
	Button home_btn;
	int view_by;
	DecimalFormat format;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_expense);
		format = new DecimalFormat();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		view_by = getIntent().getIntExtra("view_by", 0);
		
		SimpleAdapter sd = new SimpleAdapter(this, list, R.layout.table_row, new String[]{"date", "category", "amount","select"},
				new int[] {R.id.DATE_CELL,R.id.CATEGORY_CELL,R.id.AMOUNT_CELL,R.id.SELECT_ICON}); 
		total = (TextView)findViewById(R.id.tvValue);
		
		switch(view_by){
		case ViewByDateActivity.VIEW_BY_DATE_ID:
			fillListViewByDate();
			break;
		case ViewByCategoryActivity.VIEW_BY_CAT_ID:
			fillListViewByCat();
			break;
		case CustomViewActivity.VIEW_CUSTOM_ID:
			fillListViewCustom();
			break;
		default:
			fillListView();
			break;
		}

		setListAdapter(sd);
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



	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		default:
			break;
		}
	}  

	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		System.out.println("The item clicked is ");
		Intent edit_intent = new Intent(getApplicationContext(),AddExpenseActivity.class);
		edit_intent.putExtra("IS_ADD",false);
		edit_intent.putExtra("view_by", view_by);
		edit_intent.putExtra("amount", list.get(position).get("amount"));
		System.out.println("The amount is "+list.get(position).get("amount") );
		edit_intent.putExtra("date", list.get(position).get("date"));
		edit_intent.putExtra("category", list.get(position).get("category"));
		edit_intent.putExtra("notes", list.get(position).get("notes"));
		edit_intent.putExtra("_id", Integer.parseInt(list.get(position).get("_id").toString()));
		startActivity(edit_intent);
	}

	/* Fill Listview with expenses for a given date */
	private void fillListViewByDate(){
		DataBaseHelper db;
		db = new DataBaseHelper(this);
		ArrayList<ExpenseEntry> exp_list = new ArrayList<ExpenseEntry>();

		String date = getIntent().getExtras().getString("date_val");
		exp_list = db.getExpensesByDate(date);
		float sum = 0;

		for(int i =0;i<exp_list.size();i++){
			Button b = new Button(this);
			HashMap<String,String> temp = new HashMap<String,String>();
			temp.put("_id",Integer.toString(exp_list.get(i).getId()));
			temp.put("date",exp_list.get(i).getDate().toString());
			temp.put("category",exp_list.get(i).category.toString());
			temp.put("amount", format.format(exp_list.get(i).getAmount()));
			temp.put("notes", exp_list.get(i).getNotes().toString());
			sum+=exp_list.get(i).getAmount();
			list.add(temp); 
		}
		total.setText(String.valueOf(format.format(sum)));
		db.close();

	}
	

	/* Fill ListView with Expenses for a given category */
	private void fillListViewByCat(){
		DataBaseHelper db;
		db = new DataBaseHelper(this);
		ArrayList<ExpenseEntry> exp_list = new ArrayList<ExpenseEntry>();
		String cat_value = getIntent().getExtras().getString("cat_value");
		exp_list = db.getExpenseByCategory(cat_value);
		float sum = 0;
		for(int i =0;i<exp_list.size();i++){
			Button b = new Button(this);
			HashMap<String,String> temp = new HashMap<String,String>();
			temp.put("_id",Integer.toString(exp_list.get(i).getId()));
			temp.put("date",exp_list.get(i).getDate().toString());
			temp.put("category",exp_list.get(i).category.toString());
			temp.put("amount", format.format(exp_list.get(i).getAmount()));
			temp.put("notes", exp_list.get(i).getNotes().toString());
			sum+=exp_list.get(i).getAmount();
			list.add(temp); 
		}

		total.setText(String.valueOf(format.format(sum)));
		db.close();

	}
	
	
	/* Fill ListView with expenses for a given category and between 2 dates */
	private void fillListViewCustom(){
		DataBaseHelper db;
		db = new DataBaseHelper(this);
		ArrayList<ExpenseEntry> exp_list = new ArrayList<ExpenseEntry>();
		String cat_value = getIntent().getExtras().getString("cat_value");
		String from_date = getIntent().getExtras().getString("from_date");
		String to_date = getIntent().getExtras().getString("to_date");
		exp_list = db.getCustomExpense(cat_value,from_date,to_date);
		float sum = 0;

		for(int i =0;i<exp_list.size();i++){
			Button b = new Button(this);
			HashMap<String,String> temp = new HashMap<String,String>();
			temp.put("_id",Integer.toString(exp_list.get(i).getId()));
			temp.put("date",exp_list.get(i).getDate().toString());
			temp.put("category",exp_list.get(i).category.toString());
			temp.put("amount", format.format(exp_list.get(i).getAmount()));
			temp.put("notes", exp_list.get(i).getNotes().toString());
			sum+=exp_list.get(i).getAmount();
			list.add(temp); 


		}

		total.setText(String.valueOf(format.format(sum)));
		db.close();

	}

	/*Fill ListView with all expenses from the Database */
	private void fillListView() {
		DataBaseHelper db;
		// TODO Auto-generated method stub
		db = new DataBaseHelper(this);
		ArrayList<ExpenseEntry> exp_list = new ArrayList<ExpenseEntry>();
		float sum = 0;
		System.out.println("Entered all");
		exp_list = db.getExpenses();

		for(int i =0;i<exp_list.size();i++){
			Button b = new Button(this);
			HashMap<String,String> temp = new HashMap<String,String>();
			temp.put("_id",Integer.toString(exp_list.get(i).getId()));
			temp.put("date",exp_list.get(i).getDate().toString());
			temp.put("category",exp_list.get(i).category.toString());
			temp.put("amount", format.format(exp_list.get(i).getAmount()));
			temp.put("notes", exp_list.get(i).getNotes().toString());
			sum+=exp_list.get(i).getAmount();
			list.add(temp); 
		}		
		total.setText(String.valueOf(format.format(sum)));
		db.close();
	}

	public void onBackPressed(){
		
		Intent optionsActivityIntent = new Intent(ViewExpenseActivity.this,ViewExpensesOptionsActivity.class);
		
		startActivity(optionsActivityIntent);
		onDestroy();

	} 
	
	public void onDestroy() {
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	} 

}
