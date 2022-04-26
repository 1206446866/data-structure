package sorting;

public class InsertSort {
	public static void main(String[] args) {
		
	}
	
	public static void insertSort(int[] keys,boolean asc) {
		for(int i=1;i<keys.length;i++) {
			int x = keys[i],j;
			for(j=i-1;asc?x<keys[j]:keys[j]<x;j--) {
				keys[j+1] = keys[j];
			}
			keys[j+1] = keys[j];
		}
	}
}
