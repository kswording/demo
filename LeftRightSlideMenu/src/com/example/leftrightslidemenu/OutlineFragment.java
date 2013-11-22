package com.example.leftrightslidemenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OutlineFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.outline, container,false);
		ListView list = (ListView) v.findViewById(R.id.main_list);
		String[] leftdata = new String[]{"选择主题","招商银行1","工商银行1","广发银行1"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, leftdata);
		list.setAdapter(adapter);
		return v;
	}
}
