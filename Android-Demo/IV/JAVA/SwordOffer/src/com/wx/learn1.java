package com.wx;

import java.util.Stack;

public class learn1 {

	public static void main(String[] args) {
		//System.out.println(replace2("We are happy."));
	}
	
    /**
     * 1.��һ����ά�����У�ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ�
	 * �µ�����˳�����������һ������������������һ����ά�����һ���������ж�
     * �������Ƿ��и�������	
     */
	private static boolean sort1(int[][] arrays,int data) {
		
		for (int i = 0;i<arrays.length;i++) {
			if(arrays[i][0] > data) return false;
			for (int j = 0 ; j < arrays[i].length;i++) {
				if (arrays[i][j] == data) {
					return true;
				} else if (arrays[i][j]>data) {
					break;
				}
			} 
		}
		
		return false;
	}

	/**
	 * 2.��ʵ��һ�����������ַ����е�ÿ���ո��滻��"%20"�����硰We are happy.������
	 * �����We%20are%20happy.����
	 */
	private static String replace2(String data) {
		return data.replace(" ", "%20");
	}
	
	/**
	 * 3.�����������ͷ��㣬��β��ͷ��������ӡ��ÿ������ֵ
	 */
	class Entry3 {
		private int value;
		private Entry3 next;
	}
	private static void logEntry3(Entry3 root) {
		Stack<Entry3> stack = new Stack<>();
		while (root != null) {
			stack.push(root);
			root = root.next;
		}
		Entry3 temp;
		while(!stack.isEmpty()) {
			temp = stack.pop();
			System.out.println(temp);
		}
	}
	
	/**
	 * 4.����ĳ��������ǰ���������������Ľ�������ؽ����ö����������������ǰ��
     * ��������������Ľ���ж������ظ������֡����磺ǰ��������У� 1, 2, 4, 7, 3,
	 * 5, 6, 8��������������У�4, 7, 2, 1, 5, 3, 8��6}���ؽ����������������ͷ��㡣
	 */
	static class BinaryTreeNode {
		int value;
		BinaryTreeNode leftNode;
		BinaryTreeNode rightNode;
	}
	private static BinaryTreeNode  contruct4(int[] preOrder,int ps,int pe,int[] middleOrder,int ms,int me) {
		
		if (ps > pe) return null;
		
		int value = preOrder[ps];
		int index = ms;
		while(index <= me && middleOrder[index] != value) {
			index++;
		}
		
		if (index > me) {
			throw new RuntimeException();
		}
		
		BinaryTreeNode node = new BinaryTreeNode();
		node.value = value;
		node.leftNode = contruct4(preOrder, ps+1, ps+index-ms, middleOrder, ms, index-1);
		node.rightNode = contruct4(preOrder, ps+index-ms+1, pe, middleOrder, index+1, me);
		return node;
	}
	
	/**
	 * ������ջʵ��һ�����С����е��������£���ʵ��������������appendTail ��
	 * deleteHead���ֱ�����ڶ���β����������ڶ���ͷ��ɾ�����Ĺ��ܡ�
	 */
	static class Queue5<T> {
		
		Stack<T> stack1 = new Stack<>();
		
		Stack<T> stack2 = new Stack<>();
		
		public void appendTail(T t) {
			
			while (!stack2.isEmpty()) {
				stack1.add(stack2.pop());
			}
			
			stack1.add(t);
		}
		
		public T deleteHead() {
			
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
			
			if (stack2.isEmpty()) {
				throw new RuntimeException();
			}
			
			return stack2.pop();
		}
	}
	
}