package com.test.view;

import android.app.Activity;
import android.widget.TextView;

import com.test.calculator.R;

public class CaOutputView {
	
	private TextView tv;
	
	public CaOutputView(Activity ac) {
		// TODO Auto-generated constructor stub
		tv = (TextView)ac.findViewById(R.id.ResultOutput);
	}

	public void OutputData(String str) {
		tv.setText(str);
	}
}
