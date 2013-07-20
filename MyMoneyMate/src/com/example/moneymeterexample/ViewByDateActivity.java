package com.example.moneymeterexample;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ViewByDateActivity extends Activity implements OnClickListener, android.view.View.OnClickListener {
	Button show_cal;
	private int mYear;
	private int mMonth;
	private int mDay;
	EditText date;
	Button view_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_by_date);
		date = (EditText)findViewById(R.id.date_txt);
		view_btn = (Button)findViewById(R.id.view_for_date_btn);
		view_btn.setOnClickListener(this);
		show_cal = (Button) findViewById(R.id.show_calendar);
		show_cal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(AddExpense.DATE_DIALOG_ID);
			}
		});
		
		final Calendar cal = Calendar.getInstance();
		mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
 
        /** Display the current date in the TextView */
        updateDisplay();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_by_date, menu);
		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
		
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
	         if(mMonth <9){
	            		
	            if(mDay < 9){
	                	date.setText(
	                	new StringBuilder()
	                    // Appending 0 to month and day for the  format MM/DD/YYYY
	                    .append(mYear).append("/").append(0).append(mMonth + 1).append("/")
	                    .append(0).append(mDay)
	                    .append(" ")); 
	            			}
	            
	            else     	{
	            		date.setText(
	            		// Appending 0 to month for the format MM/DD/YYYY
	            		new StringBuilder()
	                    .append(mYear).append("/").append(0).append(mMonth + 1).append("/")
	                    .append(mDay)
	                    .append(" "));
	            		
	            	}
	            }
	            	else{
	            		date.setText(
	                            new StringBuilder()
	                                    // Month is 0 based so add 1
	                                    .append(mYear).append("/").append(mMonth + 1).append("/")
	                                    .append(mDay)
	                                    .append(" "));
	            		
	            	}
	            	
	            }
	   private void displayToast() {
	            Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(date.getText()),  Toast.LENGTH_SHORT).show();
	                     
	            }
	            
	   protected Dialog onCreateDialog(int id) {
		   switch (id) {
			case AddExpense.DATE_DIALOG_ID:
					return new DatePickerDialog(this,
	                    pDateSetListener,
	                    mYear, mMonth, mDay);
		   }
		   
		   return null;
		   
	   }
	   
	   public void onClick(View v) {
		   if(v.getId()==R.id.view_for_date_btn){
			   Intent view_intent = new Intent(ViewByDateActivity.this,ViewExpenseActivity.class );
			   //view_intent.putExtra("FROM", "date");
			   view_intent.putExtra("date_val",date.getText().toString());
			   view_intent.putExtra("view_by", 1);
			   startActivity(view_intent);
			   
		   }
}

}