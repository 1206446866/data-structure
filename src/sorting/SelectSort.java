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
				if (minValue > arr[j]) {//说明假定的最小值，并不是最小
//					System.out.println("第"+(i+1)+"大轮"+minValue+">"+arr[j]+"\t"+Arrays.toString(arr));
					minValue = arr[j];//重置minValue
					minIndex = j;//重置minIndex
				}
			}
			//		将最小值，放在arr[i]，即交换
			System.out.println("第"+(i+1)+"轮交换："+Arrays.toString(arr));
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = minValue;
			}
		}
	}
	
	public static void m(int [] a) {
		
		
		
		
	}
}
