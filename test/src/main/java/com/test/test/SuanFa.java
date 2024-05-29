package com.test.test;

public class SuanFa {
    public static void main(String[] args) {
        hnooi(2, "塔1", "塔2", "塔3");
    }

    public static void hnooi(Integer n, String a, String b, String c) {
        if (n == 1) {
            System.out.println(a + "移动到" + c);
        } else {
            hnooi(n - 1, a, c, b);
            System.out.println(a + "移动到" + c);
            hnooi(n - 1, b, a, c);
        }
    }

    // 二分查找法
    public static int binarySearch(int[] arr, int target) {
        int left = 1;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target < arr[mid]) {
                right = mid - 1;
            } else if (target > arr[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 二分查找法
    public static int binarySearch2(int[] arr, int target, int left, int right) {
        if (target < arr[left] || target > arr[right]) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (target < arr[mid]) {
            return binarySearch2(arr, target, left, mid - 1);
        } else {
            return binarySearch2(arr, target, mid + 1, right);
        }
    }


    // 冒泡排序
    public static void maopao(int[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            for (int j = 0; j < s.length - 1 - i; j++) {
                if (s[j] > s[j + 1]) {
                    int temp = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = temp;
                }
            }
        }
    }

    // 快速排序
    public static void quick_sort(int s[], int l, int r) {
        if (l < r) {
            int i = l;
            int j = r;
            int pivot = s[l];

            while (i < j) {
                // 从右向左找小于pivot的数
                while (i < j && s[j] >= pivot)
                    j--;
                if (i < j) {
                    s[i++] = s[j];
                }
                // 从左向右找大于pivot的数
                while (i < j && s[i] < pivot)
                    i++;
                if (i < j)
                    s[j--] = s[i];
            }
            s[i] = pivot;
            quick_sort(s, l, i - 1);
            quick_sort(s, i + 1, r);
        }
    }

}

