package com.example.inventory_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {
Context context;
    String listItem[];
    String listId[];
    String listDepartment[];
    String listQuantity[];
    String listDate[];
    LayoutInflater inflater;
public CustomBaseAdapter(Context ctx,String item[],String id[], String department[], String quantity[], String date[]){
    this.listItem=item;
    this.listId=id;
    this.listDepartment=department;
    this.listQuantity=quantity;
    this.listDate=date;
    inflater=LayoutInflater.from(ctx);



}


    @Override
    public int getCount() {
        return listItem.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         convertView = inflater.inflate(R.layout.activity_admin_history, null);
        TextView textView1=(TextView) convertView.findViewById(R.id.textView1);
        TextView textView2=(TextView) convertView.findViewById(R.id.textView2);
        TextView textView3=(TextView) convertView.findViewById(R.id.textView3);
        TextView textView4=(TextView) convertView.findViewById(R.id.textView4);
        TextView textView5=(TextView) convertView.findViewById(R.id.textView5);
        textView1.setText(listItem[position]);
        textView2.setText(listId[position]);
        textView3.setText(listDepartment[position]);
        textView4.setText(listQuantity[position]);
        textView5.setText(listDate[position]);

        return convertView;
    }
}
