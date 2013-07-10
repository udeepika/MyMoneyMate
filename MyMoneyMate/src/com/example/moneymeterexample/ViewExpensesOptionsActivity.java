package com.example.moneymeterexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ViewExpensesOptionsActivity extends Activity{
	
	RadioGroup view_options;
	RadioButton button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_expenses_options);
		RadioGroup view_options = (RadioGroup)findViewById(R.id.radioViewOptions);
		OnClickListener radio_listener = new OnClickListener(){
			public void onClick(View v) {
		        onRadioButtonClick(v);
		    }
		};
	} ;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_expenses_options, menu);
		return true;
	}
	
	public void onRadioButtonClick(View v) {
	    RadioButton button = (RadioButton) v;
	    if(v.getId()==R.id.radioViewAll){
	    	
	    Intent viewIntent = new Intent(ViewExpensesOptionsActivity.this,ViewExpenseActivity.class);
		startActivity(viewIntent);
		onDestroy();}
	    else{
	    	
	    	
	    }
	    
		
	}
	
	public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }   	

}
