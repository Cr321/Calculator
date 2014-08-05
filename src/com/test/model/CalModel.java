package com.test.model;

import java.util.Stack;

import com.test.interfaces.ICalculator;

public class CalModel implements ICalculator{
	
	private Stack<String> dataStack = new Stack<String>(); 
	
	private boolean isOperate = false;
	
	public Stack<String> getDataStack() {
		return dataStack;
	}

	public boolean isOperate() {
		return isOperate;
	}

	public void setDataStack(Stack<String> dataStack) {
		this.dataStack = dataStack;
	}

	public void setOperate(boolean isOperate) {
		this.isOperate = isOperate;
	}



	public static double popOpOffStack(Stack<String> stack){
		
		double result = 0;
		
		double operand = Double.valueOf(stack.pop());
		
		if(stack.isEmpty()){
			return operand;
		}
		
		String operate = stack.pop();
		
		if(operate.equals("+")){
			result = CalModel.popOpOffStack(stack)+operand;
		}
		else if(operate.equals("-")){
			result = CalModel.popOpOffStack(stack)-operand;
		}
		else if(operate.equals("*")){
			result = CalModel.popOpOffStack(stack)*operand;
		}
		else if(operate.equals("/")){
			result = CalModel.popOpOffStack(stack)/operand;
		}
		
		return result;
	}

	@Override
	public void pushOperand(String operand) {
		dataStack.add(operand);
		isOperate = false;
	}

	@Override
	public double pushOperate(String operate) {
		double result = 0;
		if(this.isOperate){
			//如果前一个是操作符，则将它替换
			dataStack.pop();
			System.out.println("1");
		}
				
		@SuppressWarnings("unchecked")
		Stack<String> tmpStack = (Stack<String>)dataStack.clone();
		result = CalModel.popOpOffStack(tmpStack);
		dataStack.add(operate);
		this.isOperate = true;
		
		return result;
	}

	@Override
	public void reset() {
		dataStack.removeAllElements();
		this.isOperate = false;
	}

}
