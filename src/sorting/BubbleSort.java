package sorting;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = {3,9,-1,10,-2,99,-56,9};
		System.out.println("排序前："+Arrays.toString(arr));
		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(simpledateformat.format(date));
		bubbleSort(arr);
		System.out.println(simpledateformat.format(new Date()));
	}
	
	
//	时间复杂度O(n^2)
	public static void bubbleSort(int[] arr) {
		int temp = 0;
		boolean flag = false;//标识变量，表示是否交换过
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			} 
			System.out.printf("第"+(i+1)+"轮");
			System.out.println(Arrays.toString(arr));
		if(!flag) //若没交换过则跳出，否则恢复默认值
			break;
		else 
			flag = false;
		}
	}
//	改良，支持二维数组按第一列排序
	public static void bubbleSort(Object[][] arr) {
		Object[] temp = new Object[arr[0].length];
		boolean flag = false;//标识变量，表示是否交换过
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if ((int)arr[j][0] > (int)arr[j + 1][0]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			} 
		if(!flag) //若没交换过则跳出，否则恢复默认值
			break;
		else 
			flag = false;
		}
	}
}
