package com.example.moneymeterexample;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.TextView;

public class listViewAdapter extends BaseAdapter{
	
	public ArrayList<HashMap> listMap;
    Activity activity;
 
    public listViewAdapter(Activity activity, ArrayList<HashMap> list) {
        super();
        this.activity = activity;
        this.listMap = list;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listMap.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listMap.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();
        System.out.println("Got into getView!!");
        if (convertView == null)
        {	
        	convertView = inflater.inflate(R.layout.table_row, null);
        	holder = new ViewHolder();
        	holder.txtFirst = (TextView)convertView.findViewById(R.id.ID_CELL);
        	holder.txtSecond = (TextView)convertView.findViewById(R.id.DATE_CELL);
        	holder.txtThird = (TextView)convertView.findViewById(R.id.CATEGORY_CELL);
        	holder.txtFourth = (TextView)convertView.findViewById(R.id.AMOUNT_CELL);
        }
        else{
        	holder = (ViewHolder) convertView.getTag();
        }
        
        
        HashMap map = listMap.get(position);
        holder.txtFirst.setText(map.get(TableColumnConstants.ID).toString());
        holder.txtSecond.setText(map.get(TableColumnConstants.DATE).toString());
        holder.txtThird.setText(map.get(TableColumnConstants.CATEGORY).toString());
        holder.txtFourth.setText(map.get(TableColumnConstants.AMOUNT).toString());
        
		return convertView;
	}
	
	private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
        TextView txtFourth;
   }
	

}
