package com.example.moneymeterexample;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewByCategoryActivity extends Activity implements OnClickListener {
	public static ArrayList<String> cat_btn_list ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_by_category);
		
		DataBaseHelper db;
    	db = new DataBaseHelper(this);
    	LinearLayout button_layout = (LinearLayout)findViewById(R.id.cat_button_layout);
    	int count = 0;
    	cat_btn_list = db.getCategories();
    	System.out.println(cat_btn_list);
    	for(int i=0;i<cat_btn_list.size();i++){
    		Button cat_name = new Button(this.getApplicationContext());
    		cat_name.setText(cat_btn_list.get(i));
    		cat_name.setId(count++);
    		cat_name.setWidth(150);
    		cat_name.setHeight(30);
    	    String cat = new String(cat_name.getText().toString());
    		cat_name.setOnClickListener(new View.OnClickListener() {
    		
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Button b = (Button) v;
					Intent view_by_cat_intent = new Intent(ViewByCategoryActivity.this,ViewExpenseActivity.class );
					view_by_cat_intent.putExtra("view_by", 2);
					view_by_cat_intent.putExtra("cat_value",b.getText().toString());
					System.out.println("The value of cat is " + b.getText());
					startActivity(view_by_cat_intent);
					
				}
			});
    		
    		button_layout.addView(cat_name);
    		
    	}
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
	

}
