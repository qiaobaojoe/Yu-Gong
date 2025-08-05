package org.example.leetcode.august;

/**
 * @author qiaobao
 * @since 2025/8/5
 */
public class Five {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int unPutCount = fruits.length;
        int[] basketsFull = new int[n];
        for (int fruit : fruits) {
            for (int j = 0; j < n; j++) {
                if (basketsFull[j] == 0) {
                    // 篮子没有放东西
                    if (fruit <= baskets[j]) {
                        basketsFull[j] = 1;
                        unPutCount--;
                        break;
                    }
                }
            }
        }

        return unPutCount;

    }

    public static void main(String[] args) {
        Five five = new Five();
        int[] fruits = {3,6,1};
        int[] baskets = {6,4,7};
        System.out.println(five.numOfUnplacedFruits(fruits, baskets));
    }
}
