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



public class ExpenseEntry {

	int _id;
	String date;
	String category;
	float amount;
	String notes;
	
	public ExpenseEntry(int _id,float amount,String category,String date,String notes){
		this._id = _id;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.notes = notes;
	}
	
	public ExpenseEntry() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId(){
		return this._id;
	}
	public void setId(int id){
		this._id = id;
	}
	public String getDate(){
		return this.date;
	}
	public void setDate(String date){
		this.date = date;
	}
	public void setCategory(String category){
		this.category = category;
	}
	public String getCategory(){
		return this.category;
	}
	public float getAmount(){
		return this.amount;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}
	public void setNotes(String notes){
		this.notes = notes;
	}
	public String getNotes(){
		return this.notes;
	}
	
}


