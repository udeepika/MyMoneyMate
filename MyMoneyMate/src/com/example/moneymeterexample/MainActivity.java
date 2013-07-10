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
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity implements OnClickListener{
	private Button button_add,button_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);
		button_add = (Button) findViewById(R.id.btn_add);
		button_view = (Button) findViewById(R.id.btn_view);
		button_view.setOnClickListener(this);
		button_add.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.btn_add:
			Intent addIntent = new Intent(MainActivity.this,AddExpense.class);
			startActivity(addIntent);
			break;
		case R.id.btn_view:
			Intent viewIntent = new Intent(MainActivity.this,ViewExpensesOptionsActivity.class);
			startActivity(viewIntent);
			break;
		default:
			break;
		}
		
	}

}
