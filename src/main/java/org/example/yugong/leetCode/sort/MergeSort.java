package org.example.yugong.leetCode.sort;

/**
 * @author qiaobao
 * @since 2021-02-19
 */
public class MergeSort {

    public int[] sortArray(int[] nums) {
        if (nums == null) {
            return nums;
        }
        int length = nums.length;
        if (length < 2) {
            return nums;
        }
        mergeSort(nums, 0, length - 1);

        return nums;
    }

    public int[] mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return nums;
        }
        if (left < right) {
            int mid = (left + right) / 2;
            int[] leftArray = mergeSort(nums, left, mid);
            int[] rightArray = mergeSort(nums, mid + 1, right);
            int[] merge = merge(nums, left, mid, right);
            return merge;
        }
        return nums;
    }

    //    合并两个有序数组
    private int[] merge(int[] nums, int left, int mid, int right) {
        return nums;
    }


    public void merge(int[] A, int m, int[] B, int n) {
        if (n < 1) {
            return;
        }
        if (m < 1) {
            for (int k = 0; k < n; k++) {
                A[k] = B[k];
            }
            return;
        }
        int i = 0;
        int j = 0;
        int cur = 0;
        int[] tem = new int[m + n];
        while (i < m || j < n) {
            if (i == m) {
                tem[cur] = A[i];
                i++;
                cur++;
                continue;
            }
            if (j == n) {
                tem[cur] = B[j];
                j++;
                cur++;
                continue;
            }
            if (A[i] > B[j]) {
                tem[cur] = B[j];
                j++;
            } else {
                tem[cur] = A[i];
                i++;
            }
            cur++;
        }

        for (int k = 0; k < m + n; k++) {
            A[k] = tem[k];
        }
    }


    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] a = new int[]{1, 2, 3, 0, 0, 0};
        int[] b = new int[]{2, 5, 6};
        mergeSort.merge(a, 3, b, 3);
    }
}
