package com.wx;

public class learn1 {

	public static void main(String[] args) {
		
	}
	
    /**
     * 1.��һ����ά�����У�ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ�
	 * �µ�����˳�����������һ������������������һ����ά�����һ���������ж�
     * �������Ƿ��и�������	
     */
	private boolean sort1(int[][] arrays,int data) {
		
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
	
}
