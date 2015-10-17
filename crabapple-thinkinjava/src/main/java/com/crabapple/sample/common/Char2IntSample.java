package com.crabapple.sample.common;

public class Char2IntSample {
	// 快速排序(从小到大 ),arr为整数数组,base为比较基准的下标
	public static void quickSort(int[] arr, int base, int low, int hight) {
		int i = hight;
		int j = low;
		int tempIndex = base; // 记录被移动的数据的原始位置,开始为关键数据的下标
		int key = arr[base]; // 关键数据
		if (i == j)
			return;
		while (j < i) {
			while (j < i) {
				if (arr[i] < key) {
					arr[tempIndex] = arr[i];
					tempIndex = i;
					break; // 跳出循环
				}
				i--;
			}
			while (j < i) {
				if (arr[j] > key) {
					arr[tempIndex] = arr[j];
					tempIndex = j;
					break; // 跳出循环
				}
				j++;
			}
		}
		arr[i] = key;
		if (low <= i - 1)
			quickSort(arr, (low + i - 1) / 2, low, i - 1);
		if (i + 1 < hight)
			quickSort(arr, (hight + i - 1) / 2, i + 1, hight);
		/*
		 * // 默认以数组第一个数据为基准 if(low<i-1) quickSort(arr,low,low,i-1);
		 * if(i+1<hight) quickSort(arr,i+1,i+1,hight);
		 */
	}

	// 快速排序(从小到大 ),arr为整数数组,关键字为数组第一个数据---参考别人
	public static void quickSort1(int[] arr, int low, int hight) {
		int i = hight;
		int j = low;
		int key = arr[low]; // 关键数据,以此为基准进行比较

		if (i == j)
			return;

		while (j < i) {
			// 从右向左寻找第一个小于关键字的数据
			while (i > j && arr[i] > key) {
				i--;
			}
			arr[j] = arr[i];

			// 从左向右寻找第一个大于关键字的数据
			while (j < i && arr[j] < key) {
				j++;
			}
			arr[i] = arr[j];
		}
		arr[i] = key;

		if (low <= i - 1)
			quickSort1(arr, low, i - 1);
		if (i + 1 < hight)
			quickSort1(arr, i + 1, hight);
	}

	// 输出数组数据
	public static void show(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}

	// main()函数
	public static void main(String[] args) {
		// Scanner scan = new Scanner(System.in);
		char c = '9';
		System.out.println(c);
		System.out.println((int) c);
		System.out.println((c - 0));
		System.out.println(Character.getNumericValue(c));
		int[] arr = new int[] { 3, 7, 9, 2, 1, 6, 4 };
		show(arr);
		// quickSort1(arr,0,arr.length-1);
		quickSort(arr, arr.length / 2, 0, arr.length - 1);
		System.out.println("排序后:");
		show(arr);
	}
}
