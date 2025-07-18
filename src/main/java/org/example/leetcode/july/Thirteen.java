package org.example.leetcode.july;

import java.util.Arrays;

/**
 * @author qiaobao
 * @since 2025/7/13
 */
public class Thirteen {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        // 暴力解法就是用每一个运动员去遍历训练师列表，找到一个和他的能力相等或者第一个大于他的数值
        // 这里对训练师进行排序用二分法处理了
        // 好像可以对运动员进行排序，能力值最大和最小的在排序后训练师集合中的索引，这两个值就可以作为遍历初始的l,r
        int n = players.length;
        int m = trainers.length;
        Arrays.sort(trainers);
        Arrays.sort(players);
        int ans = 0;
        int leftPlay = 0;
        int rightPlay = n - 1;

        int leftPlayTrainer = binarySearch(-1, m, trainers, players[leftPlay]);
        if (leftPlayTrainer == -1) {
            // 最小能力值的运动员都没有找到教练其他就不用找了
            return 0;
        }
        ans++;
        int rightPlayTrainer = m;
        while (rightPlay > leftPlay) {
            int trainer = binarySearch(leftPlayTrainer, rightPlayTrainer, trainers, players[rightPlay]);
            rightPlay--;
            if (trainer == -1) {
                // 没有找到训练师，继续找下一个
                continue;
            }
            // 找到训练师结果需要加1，同时把这个位置作为右边界
            ans++;
            rightPlayTrainer = trainer;

        }

        return ans;
    }

    //  返回第一个等于目标值或者第一个大于目标值的索引  不存在返回 -1
    private int binarySearch(int l, int r, int[] trainers, int target) {
        if (r <= l + 1) {
            // 数组越界无法处理
            return -1;
        }
        while (r > l + 1) {
            int m = l + (r - l) / 2;
            if (trainers[m] >= target) {
                r = m;
            } else {
                l = m +1 ;
            }
        }
        if (l == (trainers.length - 1)) {
            return -1;
        }
        if (trainers[l + 1] >= target) {
            return l + 1;
        }
        return -1;
    }
}
