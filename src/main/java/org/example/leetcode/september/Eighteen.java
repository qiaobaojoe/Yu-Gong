package org.example.leetcode.september;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author qiaobao
 * @since 2025/9/18
 */
public class Eighteen {
    private final Map<Integer, Integer> indexToNumber = new HashMap<>();
    private final Map<Integer, TreeSet<Integer>> numberToIndices = new HashMap<>();

    public void change(int index, int number) {
        // 移除旧数据
        Integer oldNumber = indexToNumber.get(index);
        if (oldNumber != null) {
            numberToIndices.get(oldNumber).remove(index);
        }

        // 添加新数据
        indexToNumber.put(index, number);
        numberToIndices.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }

    public int find(int number) {
        TreeSet<Integer> indices = numberToIndices.get(number);
        return indices == null || indices.isEmpty() ? -1 : indices.first();
    }

    public static void main(String[] args) {
        Eighteen eighteen = new Eighteen();
        eighteen.change(0, 1);
        eighteen.change(1, 2);
        eighteen.change(2, 1);
        System.out.println(eighteen.find(1));
        System.out.println(eighteen.find(2));
    }

}
