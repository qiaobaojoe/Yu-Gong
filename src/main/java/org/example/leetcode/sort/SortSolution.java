package org.example.leetcode.sort;

/**
 * @author qiaobao
 * @since 2025/6/28
 */
public class SortSolution {

    public int[] selectSort(int[] nums) {
        // 重新复习一下几个简单排序的实现，最后尝试一下经典的快排
        // 选择排序优化结构
        int n = nums.length;
        // 边界条件判断
        if (n == 0 || n == 1) {
            return nums;
        }
        // 选择最大的元素，放在队尾
        for (int i = n; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j < i; j++) {
                if (nums[j] > nums[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(nums, maxIndex, i - 1);
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            // 位置相同不需要处理
            return;
        }
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }


    public int[] insertSortArray(int[] nums) {
        // 插入排序，这里比较麻烦的两点：
        // 1.找到要插入的位置 2.数组的移动
        int n = nums.length;
        if (n == 1) {
            return nums;
        }
        for (int i = 1; i < n; i++) {
            // i 就是已经有序队列的长度，每一次都是最后一个元素参加排序
            int insertIndex = findInsertIndex(nums, i, nums[n - 1]);
            move(nums, insertIndex, nums[n - 1]);
        }

        return nums;
    }

    private int findInsertIndex(int[] nums, int i, int target) {
        // 这里可以用二分查找优化
        for (int j = 0; j < i; j++) {
            if (nums[j] > target) {
                return j;
            }
        }
        return i;
    }

    private void move(int[] nums, int insertIndex, int target) {
        int n = nums.length;
        if (insertIndex == n - 1) {
            // 要插入最后一个位置不需要移动
            nums[insertIndex] = target;
            return;
        }
        // 现将 from =  insertIndex + 1 向前挪动  to  = insertIndex + 1 +1 len = (nums.length - to)
        int destPos = insertIndex + 1;
        int len = n - destPos;
        System.arraycopy(nums, insertIndex, nums, destPos, len);
        nums[insertIndex] = target;
    }

    public int[] bubbleSortArray(int[] nums) {
        // 冒泡排序，每次遍历找到最大的元素，放到队尾，在遍历的过程中会发生交换
        int n = nums.length;
        if (n == 1) {
            return nums;
        }
        // 冒牌排序，和选择排序相同的是，每次遍历找到最大的元素放到队尾
        // 不同的是，冒泡排序一次遍历不会只交换一次数据，而是始终在交换，指针永远指向当前遍历数据
        for (int i = n - 1; i > 0; i--) {
            boolean noSwap = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    noSwap = false;
                }
            }
            if (noSwap) {
                return nums;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        SortSolution solution = new SortSolution();
        solution.insertSortArray(new int[]{5, 1, 2, 3});


    }
}
