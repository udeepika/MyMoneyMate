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
			Intent viewIntent = new Intent(MainActivity.this,ViewExpenseActivity.class);
			startActivity(viewIntent);
			break;
		default:
			break;
		}
		
	}

}
