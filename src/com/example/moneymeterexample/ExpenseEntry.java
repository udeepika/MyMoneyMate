package com.example.moneymeterexample;



public class ExpenseEntry {

	int _id;
	String date;
	String category;
	int amount;
	
	public ExpenseEntry(int _id,int amount,String category,String date){
		this._id = _id;
		this.amount = amount;
		this.category = category;
		this.date = date;
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
	public int getAmount(){
		return this.amount;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}
	
}


