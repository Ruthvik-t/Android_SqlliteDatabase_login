package com.example.admin.database1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 09-04-2015.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler lh;
        if(row == null){
            LayoutInflater lf = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = lf.inflate(R.layout.customlist,parent,false);
            lh = new LayoutHandler();
            lh.uname = (TextView)row.findViewById(R.id.UserName);
            lh.upwd = (TextView) row.findViewById(R.id.UserPassword);
            row.setTag(lh);
        }
        else{
            lh =(LayoutHandler) row.getTag();

        }
        DataProvider dp =(DataProvider) this.getItem(position);
        lh.uname.setText(dp.getName());
        lh.upwd.setText(dp.getPassword());

        return row;
    }

    static class LayoutHandler{
        TextView uname,upwd;
    }
}
