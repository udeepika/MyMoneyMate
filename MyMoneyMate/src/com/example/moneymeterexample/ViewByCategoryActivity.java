/* **************************************************************************************************************************
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
* Reference: http://stackoverflow.com/questions/9280965/arrayadapter-requires-the-resource-id-to-be-a-textview-xml-problems
*************************************************************************************************************************** */


package com.example.moneymeterexample;

import java.util.ArrayList;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class ViewByCategoryActivity extends ListActivity implements OnClickListener {
	public static ArrayList<String> cat_btn_list ;
	public static final int VIEW_BY_CAT_ID = 2;
	ListView lv;
	ArrayAdapter<String> adapter;
	//public static SimpleAdapter catAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_by_category);

		DataBaseHelper db;
		db = new DataBaseHelper(this);
		//LinearLayout button_layout = (LinearLayout)findViewById(R.id.cat_button_layout);

		cat_btn_list = db.getCategories();
		adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.button_row,cat_btn_list);
		setListAdapter(adapter);
		
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent view_by_cat_intent = new Intent(ViewByCategoryActivity.this,ViewExpenseActivity.class );
		view_by_cat_intent.putExtra("view_by", VIEW_BY_CAT_ID);
		view_by_cat_intent.putExtra("cat_value",cat_btn_list.get(position).toString());

		startActivity(view_by_cat_intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_by_category, menu);
		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

	public void onBackPressed(){
		Intent viewOptionsIntent = new Intent(ViewByCategoryActivity.this,ViewExpensesOptionsActivity.class);
		finish();
		startActivity(viewOptionsIntent);
	}
}
