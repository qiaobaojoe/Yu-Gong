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

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; true; i++) {
            x = 0; y = 0; a = 0; b = 0;
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });
            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            if (x == 0 && y == 0) {
                System.out.println("i=" + i);
                break;
            }
        }
    }
}
