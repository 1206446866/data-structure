package sorting;

import java.util.Arrays;

public class SelectSort {
	public static void main(String[] args) {
		int[] arr = {2,5,9,-5,8,-7};
		selectSort(arr);
	}
	
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			int minIndex = i;
			int minValue = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (minValue > arr[j]) {//˵���ٶ�����Сֵ����������С
//					System.out.println("��"+(i+1)+"����"+minValue+">"+arr[j]+"\t"+Arrays.toString(arr));
					minValue = arr[j];//����minValue
					minIndex = j;//����minIndex
				}
			}
			//		����Сֵ������arr[i]��������
			System.out.println("��"+(i+1)+"�ֽ�����"+Arrays.toString(arr));
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = minValue;
			}
		}
	}
	
	public static void m(int [] a) {
		
		
		
		
	}
}
