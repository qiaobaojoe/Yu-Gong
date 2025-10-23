package org.example.leetcode.october;

/**
 * @author qiaobao
 * @since 2025/10/23
 */
public class TwentyThree {

    public boolean hasSameDigits(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        //    0 asc 码是 48
        int[] list = new int[n];
        for (int i = 0; i < n ; i++){
            list[i] = (int)chars[i] - 48;
        }
        while( n > 2 ){
            for(int j = 0; j < n -1 ; j ++){
                list[j] = (list[j+1] + list[j]) % 10;
            }
            n--;
        }

        return list[0] == list[1];

    }

    public static void main(String[] args) {
        TwentyThree twentyThree = new TwentyThree();
        System.out.println(twentyThree.hasSameDigits("3902"));
        System.out.println(twentyThree.hasSameDigits("34789"));
    }

}
