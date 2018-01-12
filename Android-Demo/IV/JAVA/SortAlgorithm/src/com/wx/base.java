package com.wx;

import java.util.LinkedHashMap;
import java.util.Map;

public class base {
	
	public static void main(String[] args) {
		//bubbleSort();
		//choiceSort();
		//insertSort();
		//quickSort();
		linkHashMap();
	}
	
	//ð������: �Ƚ��������ڵ�Ԫ�أ���ֵ���Ԫ�ؽ������Ҷˡ�O(n*n)
	static void bubbleSort() {
		int[] datas = {8,3,1,5,2,7,6,9,0,4};
		int temp = 0;
		for (int i = 0 ; i < datas.length - 1 ; i++) {
			for (int j = 0 ; j < datas.length - 1 - i ; j++) {
				if (datas[j] > datas[j+1]) {
					temp = datas[j];
					datas[j] = datas[j+1];
					datas[j+1] = temp;
				}
			}
		}
		
		for (int i = 0 ; i < datas.length ; i++) {
			System.out.print(datas[i]+" ");
		}
	}
	
	//ѡ������: ÿһ�˴Ӵ�����ļ�¼��ѡ����С��Ԫ�أ�˳��������ź�����������ֱ��ȫ����¼������ϡ�
	//ʱ�临�Ӷ�O(n*n) ����������ð������ ���������� ���ʱn-1
	static void choiceSort() {
		int[] datas = {8,3,1,5,2,7,6,9,0,4};
		int temp = 0;
		for (int i = 0 ; i < datas.length - 1 ; i++) {
			int key = i;
			for (int j = i+1 ; j < datas.length ; j++) {
				if (datas[j] < datas[key]) {
					key = j;
				}
			}
			if (key != i) {
				temp = datas[i];
				datas[i] = datas[key];
				datas[key] = temp;
			}
			
		}
		
		for (int i = 0 ; i < datas.length ; i++) {
			System.out.print(datas[i]+" ");
		}
	}

	//��������: ͨ�������������У�����δ�������ݣ��������������дӺ���ǰɨ�裬�ҵ���Ӧ��λ�ò����롣
	//O(n*n) ����������ð�������ѡ������
	static void insertSort() {
		int[] datas = {8,3,1,5,2,7,6,9,0,4};
		int temp = 0;
		int j = 0;
		for (int i = 1 ; i < datas.length ; i++) {
			temp = datas[i];
			for (j = i ;j > 0 && temp < datas[j-1]; j--) {
				datas[j] = datas[j-1];
			}
			if (i != j) datas[j] = temp;
		}
		
		for (int i = 0 ; i < datas.length ; i++) {
			System.out.print(datas[i]+" ");
		}
	}
	
	//��������O(nlog2n): ͨ��һ�����򽫴������¼�ָ�ɶ����������֣�����һ���ּ�¼�Ĺؼ��־�����һ���ֹؼ���С����ֱ���������ּ�����������ֱ��������������
	static void quickSort() {
		int[] datas = {8,3,1,5,2,7,6,9,0,4,8,7,10};
		qSort(datas, 0, datas.length-1);
		for (int i = 0 ; i < datas.length ; i++) {
			System.out.print(datas[i]+" ");
		}
	}
	
	static void qSort(int[] arr , int low , int high) {
		if (low < high) {
			int pivot = partition(arr, low, high);
			qSort(arr , low, pivot-1);
			qSort(arr , pivot+1 , high );
		}
	}
	
	//��������
	static int partition(int[] arr,int low,int high) {
		int pivot = arr[low];
		while(low < high) {
			while (low<high && arr[high]>=pivot) --high;
			arr[low] = arr[high];
			while(low<high && arr[low]<=pivot) ++low;
			arr[high] = arr[low];
		}
		arr[low] = pivot;
		return low;
	}
	
	//linkedHashMap
	static void linkHashMap() {
		LinkedHashMap<Integer, String>  map = new LinkedHashMap<>(0,0.75f,true);
		map.put(0, "0");
		map.put(4, "0");
		map.put(3, "0");
		map.put(2, "0");
		map.put(1, "0");
		map.get(1);
		map.get(2);
		map.get(3);
		map.get(4);
		
		for(Map.Entry<Integer, String> entry: map.entrySet()) {
			System.out.println(entry.getKey()+"  "+entry.getValue());
		}
	}
	
}
