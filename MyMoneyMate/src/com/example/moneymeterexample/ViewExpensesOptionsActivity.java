package com.example.moneymeterexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ViewExpensesOptionsActivity extends Activity implements OnClickListener{
	
	Button view_all, view_by_date, view_by_cat, view_by_month, view_custom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_expenses_options);
		Button view_all = (Button)findViewById(R.id.ViewAll_btn);
		view_all.setOnClickListener(this);
		Button view_by_date = (Button) findViewById(R.id.ViewByDate_btn);
		view_by_date.setOnClickListener(this);
		Button view_by_cat = (Button)findViewById(R.id.ViewByCat_btn);
		view_all.setOnClickListener(this);
		Button view_by_month = (Button) findViewById(R.id.ViewByMonth_btn);
		view_by_date.setOnClickListener(this);
		Button view_custom = (Button) findViewById(R.id.CustomView_btn);
		view_by_date.setOnClickListener(this);
		
	} ;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_expenses_options, menu);
		return true;
	}
	
	public void onClick(View v) {
		switch(v.getId()){
	
		
	    
		case R.id.ViewAll_btn:
	    	
	    Intent viewIntent = new Intent(ViewExpensesOptionsActivity.this,ViewExpenseActivity.class);
		startActivity(viewIntent);
		//onDestroy();}
	    break;
	    
	    case R.id.ViewByCat_btn:
	    
	    break;
	    
	    case R.id.ViewByDate_btn:
	    Intent viewbyDateIntent = new Intent(ViewExpensesOptionsActivity.this,ViewByDateActivity.class);
		startActivity(viewbyDateIntent);
	    break;
	    
	    case R.id.ViewByMonth_btn:
	    break;
	    
	    case R.id.CustomView_btn:
	    break;
		}
	}
	
	public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }   	

}
