package com.example.nodeterministas;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Main extends Activity {
    ChkListAdapter adapter;
    ListView list;
    CheckBox chkAll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = (ListView)findViewById(R.id.list);
        fillList();
    }

    private void fillList() {
        //numero aprox de contactos
        int num = 50;
        adapter = new ChkListAdapter(num);
        for(int i = 0; i < num; i++)
            adapter.addItem(new Item(String.valueOf(i), "item number " + i));
        list.setAdapter(adapter);
    }

    private class ChkListAdapter extends BaseAdapter {
        private ArrayList<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;
        private boolean[] itemSelection;
        public ChkListAdapter(int size) {
            inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemSelection = new boolean[size];
        }

        public void addItem(final Item item) {
            items.add(item);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public String getItem(int position) {
            return items.get(position).toString();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.row, null);
            final ViewHolder holder = new ViewHolder();
            holder.chkItem = (CheckBox)convertView.findViewById(R.id.chkItem);
            holder.chkItem.setOnCheckedChangeListener(new OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    itemSelection[position] = holder.chkItem.isChecked();
                }
            });

            holder.chkItem.setChecked(itemSelection[position]);
            convertView.setTag(holder);
            holder.chkItem.setText(getItem(position));
            return convertView;
        }

        public int getItemsLength() {
            if(itemSelection == null) return 0;
            return itemSelection.length;
        }

        public void set(int i, boolean b) {
            itemSelection[i] = b;
        }

    }

    public static class ViewHolder {
        public CheckBox chkItem;
    }

}


