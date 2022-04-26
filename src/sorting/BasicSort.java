package sorting;

import java.util.Arrays;

/**
 * 	基数排序
 * @author 黑岩射手
 *
 */
public class BasicSort {
	public static void main(String[] args) {
		int[] arrays = new int[] {53,542,3,63,14,214,154,748,616};
		sort(arrays);
		basic(arrays,3);
		
	}
	
	@SuppressWarnings("unused")
	public static void sort(int[] arrays) {
//		判断最大数字
		int max = 0;
		for(int i=0;i<arrays.length;i++) {
			if(arrays[i]>max) {
				max = arrays[i];
			}
		}
//		取得最大数字长度
		int maxLength = (max+"").length();
		 
		int[][] bucket = new int[10][arrays.length];
		
		int [] bucketElementCount = new int[10];
		
		
		for(int j=0;j<arrays.length;j++) {
			int locationElement = arrays[j]%10;
			bucket[locationElement][bucketElementCount[locationElement]] = arrays[j];
			bucketElementCount[locationElement]++;
		}
		
		int index = 0;
		for(int k=0;k<bucketElementCount.length;k++) {
			if(bucketElementCount[k]!=0){
				for(int l=0;l<bucketElementCount[k];l++) {
					arrays[index++] = bucket[k][l];
				}
			}
			bucketElementCount[k] = 0;
		}
		
		
		for(int j=0;j<arrays.length;j++) {
			int locationElement = arrays[j]/10%10;
			bucket[locationElement][bucketElementCount[locationElement]] = arrays[j];
			bucketElementCount[locationElement]++;
		}
		
		int indx = 0;
		for(int k=0;k<bucketElementCount.length;k++) {
			if(bucketElementCount[k]!=0) {
				for(int l=0;l<bucketElementCount[k];l++) {
					arrays[indx++] = bucket[k][l];
				}
			}
			bucketElementCount[k]=0;
		}
		
		
		for(int j=0;j<arrays.length;j++) {
			int locationElement = arrays[j]/100%10;
			bucket[locationElement][bucketElementCount[locationElement]] = arrays[j];
			bucketElementCount[locationElement]++;
		}
		
		int ind = 0;
		for(int k=0;k<bucketElementCount.length;k++) {
			if(bucketElementCount[k]!=0) {
				for(int l=0;l<bucketElementCount[k];l++) {
					arrays[ind++] = bucket[k][l];
				}
			}
			bucketElementCount[k]=0;
		}
		System.out.println(Arrays.toString(arrays));
	}
	
	public static void basic(int[] arrays,int maxLength) {
		 
		int[][] bucket = new int[10][arrays.length];
		
		int [] bucketElementCount = new int[10];
		for(int u=1;u<maxLength;u*=10) {
		for(int j=0;j<arrays.length;j++) {
			int locationElement = arrays[j]/u%10;
			
			bucket[locationElement][bucketElementCount[locationElement]] = arrays[j];
			bucketElementCount[locationElement]++;
		}
		
		int index = 0;
		for(int k=0;k<bucketElementCount.length;k++) {
			if(bucketElementCount[k]!=0){
				for(int l=0;l<bucketElementCount[k];l++) {
					arrays[index++] = bucket[k][l];
				}
			}
			bucketElementCount[k] = 0;
		}
		}
		
		System.out.println(Arrays.toString(arrays));
	}
}
