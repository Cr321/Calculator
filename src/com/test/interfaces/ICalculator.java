package com.test.interfaces;

public interface ICalculator {

	//将数字压栈
	public void pushOperand(String operand);
	
	//将操作符压榨
	public double pushOperate(String operate);
	
	public void reset();
}
