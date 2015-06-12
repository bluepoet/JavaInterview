package net.bluepoet.chap6;

import java.util.LinkedList;

import com.google.common.collect.Lists;

public class Stack {
	private final LinkedList<Integer> stack;

	public Stack() {
		stack = new LinkedList<Integer>();
	}

	public Stack(LinkedList<Integer> initialState) {
		this.stack = initialState;
	}
	
	public void push(int number) {
		stack.add(0, number);
	}
	
	public Integer pop() {
		return stack.remove(0);
	}
	
	public Stack filter(StackPredicate filter) {
		LinkedList<Integer> initialState = Lists.newLinkedList();
		for(Integer integer : stack) {
			if(filter.isValid(integer)) {
				initialState.add(integer);
			}
		}
		
		return new Stack(initialState);
	}
}