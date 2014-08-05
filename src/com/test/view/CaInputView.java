package com.test.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;

public class CaInputView {

	public interface InputHappend {
		public void operandIn(String operand);

		public void operateIn(String operate);
	}

	private List<Button> operands;
	private List<Button> operates;

	private CaInputView.InputHappend dlg;

	public CaInputView(Activity ac, CaInputView.InputHappend delegate) {
		dlg = delegate;
		operands = new ArrayList<Button>();
		operates = new ArrayList<Button>();

		Resources res = ac.getResources();
		for (int i = 0; i <= 9; i++) {
			if (i <= 5) {
				int id_operate = res.getIdentifier("Operate" + i, "id",
						ac.getPackageName());
				Button btn_operate = (Button) ac.findViewById(id_operate);
				operates.add(btn_operate);
			}

			int id_operand = res.getIdentifier("Operand" + i, "id",
					ac.getPackageName());
			Button btn_operand = (Button) ac.findViewById(id_operand);
			operands.add(btn_operand);
		}
		
		for(Button btn:operands){
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Button cli_btn = (Button) v;
					String text = cli_btn.getText().toString();
					dlg.operandIn(text);
				}
			});
		}
		
		for(Button btn:operates){
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Button cli_btn = (Button)v;
					String text = cli_btn.getText().toString();
					dlg.operateIn(text);
				}
			});
		}
	}
}
