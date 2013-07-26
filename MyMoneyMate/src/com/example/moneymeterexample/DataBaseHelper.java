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

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Expense_table_3.db";
	private static final String TABLE_EXPENSES = "Expenses";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_CATEGORY = "category";
	private static final String KEY_AMOUNT = "amount";
	private static final String KEY_DATE = "date";
	private static final String NOTES = "notes";
	Context c;
    private ArrayList<ExpenseEntry> exList = new ArrayList<ExpenseEntry>();
    private ArrayList<String> cat_list = new ArrayList<String>();    
    public DataBaseHelper(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		c = context;
		// TODO Auto-generated constructor stub
	}
    public String getTableName(){
    	return DataBaseHelper.TABLE_EXPENSES;
    }
    
    public String getDBName(){
    	return DataBaseHelper.DATABASE_NAME;
    }
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query = "CREATE TABLE " + TABLE_EXPENSES + "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CATEGORY + " TEXT, " +
				KEY_AMOUNT + " REAL NOT NULL, " + KEY_DATE + " TEXT, " + NOTES + " TEXT);" ;
				db.execSQL(query);
		
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EXPENSES);
		onCreate(db);
		
	}
	
	public void addExpenseEntry(ExpenseEntry e){
		 SQLiteDatabase db = this.getWritableDatabase();
		 ContentValues contentValues = new ContentValues();
		 contentValues.put("amount", e.amount);
		 contentValues.put("date", e.date);
		 contentValues.put("category", e.category);
		 contentValues.put("notes", e.notes);
		 contentValues.put("_id", e._id);
		 db.insert(TABLE_EXPENSES,null,contentValues);
		 db.close();
	}
	
	public int editExpenseEntry(ExpenseEntry e){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		 contentValues.put("amount", e.amount);
		 contentValues.put("date", e.date);
		 contentValues.put("category", e.category);
		 contentValues.put("notes", e.notes);
		 
		 return db.update(TABLE_EXPENSES, contentValues, KEY_ID +"="+ e._id, null) ;
		 //db.update(TABLE_EXPENSES, contentValues, KEY_ID + "=" + e._id,null)>0;
	}
	
	public ArrayList<ExpenseEntry> getExpenses(){
		exList.clear();
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES + " ORDER BY date ", null);
       
        if(cursor.getCount()!=0){
        	if (cursor.moveToFirst()) {
                do {
                	ExpenseEntry ee = new ExpenseEntry();
                	ee._id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                	ee.amount = cursor.getFloat(cursor.getColumnIndex(KEY_AMOUNT));
                	ee.category = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY));
                	ee.date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                	ee.notes = cursor.getString(cursor.getColumnIndex(NOTES));
                	exList.add(ee);
                }while(cursor.moveToNext());
        }
	}
        cursor.close();
        db.close();
        return exList;
}
	public ArrayList<String> getCategories(){
		cat_list.clear();
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT category FROM " + TABLE_EXPENSES, null);
        if(cursor.getCount()!=0){
        	if (cursor.moveToFirst()) {
                do {
                	cat_list.add(cursor.getString(0));
                }while(cursor.moveToNext());
                	
                }
        }
        cursor.close();
        db.close();
		return cat_list;
		
	}
	
	
	public int getTotalRecords(){
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES , null);
        int recCount =  cursor.getCount();
        cursor.close();
        db.close();
        return recCount;
        
	}
	
	public boolean deleteRecord(ExpenseEntry e){
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_EXPENSES, KEY_ID + "=" + e._id , null) >0 ;
	}
	
	ArrayList<ExpenseEntry> getExpensesByDate(String date_val){
		exList.clear();
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES + " WHERE date="+"'"+date_val+"'", null);
        System.out.println("In DB function dat val is "+ date_val);
        if(cursor.getCount()!=0){
        	if (cursor.moveToFirst()) {
                do {
                	ExpenseEntry ee = new ExpenseEntry();
                	ee._id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                	ee.amount = cursor.getFloat(cursor.getColumnIndex(KEY_AMOUNT));
                	ee.category = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY));
                	ee.date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                	ee.notes = cursor.getString(cursor.getColumnIndex(NOTES));
                	exList.add(ee);
                }while(cursor.moveToNext());
        }
	}
        cursor.close();
        db.close();
        return exList;
}
	
	
	public ArrayList<ExpenseEntry> getExpenseByCategory(String cat){
		exList.clear();
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES + " WHERE category="+"'"+cat+"'", null);
       
        if(cursor.getCount()!=0){
        	if (cursor.moveToFirst()) {
                do {
                	ExpenseEntry ee = new ExpenseEntry();
                	ee._id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                	ee.amount = cursor.getFloat(cursor.getColumnIndex(KEY_AMOUNT));
                	ee.category = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY));
                	ee.date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                	ee.notes = cursor.getString(cursor.getColumnIndex(NOTES));
                	exList.add(ee);
                }while(cursor.moveToNext());
        }
	}
        cursor.close();
        db.close();
        return exList;
}
	public ArrayList<ExpenseEntry> getCustomExpense(String cat,String from_date,String to_date){
		exList.clear();
		Cursor cursor;
		SQLiteDatabase db = this.getWritableDatabase();
		if (cat.equals("All"))
		cursor = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES + " WHERE date BETWEEN '"
	        		+from_date+"' AND '"+to_date+"' GROUP BY category", null); 
		
		else
        cursor = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES + " WHERE category="+"'"+cat+"' AND date BETWEEN '"
        		+from_date+"' AND '"+to_date+"'", null);  
       
        if(cursor.getCount()!=0){
        	if (cursor.moveToFirst()) {
                do {
                	ExpenseEntry ee = new ExpenseEntry();
                	ee._id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                	ee.amount = cursor.getFloat(cursor.getColumnIndex(KEY_AMOUNT));
                	ee.category = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY));
                	ee.date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                	ee.notes = cursor.getString(cursor.getColumnIndex(NOTES));
                	exList.add(ee);
                }while(cursor.moveToNext());
        }
	}
        cursor.close();
        db.close();
        return exList;
}
	
}
	

