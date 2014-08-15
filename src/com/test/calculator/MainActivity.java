package com.test.calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.test.model.CalModel;
import com.test.view.CaInputView;
import com.test.view.CaInputView.InputHappend;
import com.test.view.CaOutputView;

public class MainActivity extends Activity implements InputHappend {

	private CaInputView civ;
	private CaOutputView cov;
	private static CalModel calModel;

	private String number = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		civ = new CaInputView(this, this);
		cov = new CaOutputView(this);
		calModel = new CalModel();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void operandIn(String operand) {
		// TODO Auto-generated method stub
		if (number.length() < 9) {
			number = number.equals("0") ? operand : number + operand;
			calModel.setOperate(false);
			cov.OutputData(number);
		}
	}

	@Override
	public void operateIn(String operate) {
		// TODO Auto-generated method stub
		if (operate.equals("c")) {
			calModel.reset();
			number = "0";
			cov.OutputData(number);
			return;
		}
		// 如果前一个输入的是运算符，就不用进行一次操作数的压栈了
		if (!calModel.isOperate())
			calModel.pushOperand(number);

		double result = calModel.pushOperate(operate);
		// int tmp = Double.valueOf(result).intValue();
		BigDecimal tmp = new BigDecimal(result);
		if (tmp.toPlainString().length() <= 9)
			cov.OutputData(tmp.stripTrailingZeros().toPlainString());
		else {
			if (tmp.stripTrailingZeros().toString().length() <= 10)
				cov.OutputData(tmp.stripTrailingZeros().toString());
			else {
				String array[] = tmp.stripTrailingZeros().toString().split("E");
				System.out.println(10 - 1 - array[1].length());
				cov.OutputData(array[0].substring(0,10 - 1 - array[1].length()-1)
						+ "E" + array[1]);
			}
		}
		number = "0";
	}
}
