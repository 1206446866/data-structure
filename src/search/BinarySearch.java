package search;

public class BinarySearch {

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//		for (int element : array) {
//			int test = binarySearch(array, element);
//			System.out.println(test);
//		}
		System.out.println(binarySearch(array, 10, 0, array.length - 1));
	}

//	二分法查找（需要排序）
	public static int binarySearch(int[] array, int element) {
		int max = array.length - 1;
		int min = 0, mid = 0;
		while (min <= max) {
			mid = (min + max) / 2;
			if (array[mid] == element)
				return mid;
			if (array[mid] < element)
				min = mid + 1;
			if (array[mid] > element)
				max = mid - 1;
		}
		return -1;
	}

	public static int binarySearch(int[] array, int element, int min, int max) {
		int mid = (min + max) / 2;
		while (min <= max) {
			if (array[mid] == element)
				return mid;
			if (array[mid] < element)
				return binarySearch(array, element, mid + 1, max);
			if (array[mid] > element)
				return binarySearch(array, element, min, mid - 1);
		}
		return -1;
	}
}
