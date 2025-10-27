package org.example.leetcode.october;

import java.util.Scanner;

/**
 * @author qiaobao
 * @since 2025/10/27
 */
public class TwentySeven {
    public static void main(String[] args) {
        System.out.println(encrypt("abcdefg1"));
        System.out.println(decrypt("0BCDEFGH"));
    }
//  要区分英文字母和字符串两种情况

    //  大写字母asc  65 - 90
//  小写字母asc  97 - 122
//  加密函数
    public static String encrypt(String str) {
        int n = str.length();
        char[] ans = new char[n];
        char[] old = str.toCharArray();
        for (int i = 0; i < n ; i++) {
            int cur = (int)old[i];
            if (isNum(cur)) {
//              数字增加 1，9 转换成 0，记住数字和字母的 asc 码很有必要
                cur++;
                if (cur > 57) {
                    cur = 48;
                }
                ans[i] = (char)cur;
                continue;
            }
            if (isUpper(cur)) {
//              大写要转换小写，后移一位Z 转换为 a
                cur += 33;
                if (cur > 122) {
                    cur = 97;
                }
                ans[i] = (char)cur;
                continue;
            }
            if (isLower(cur)) {
//              小写要转换大写，后移一位z 转换为 A
                cur -= 31;
                if (cur > 90) {
                    cur = 65;
                }
            }

            ans[i] = (char)cur;
        }

        return String.valueOf(ans);
    }

    private static boolean isNum(int asc) {
        //  数字的 asc 码范围：48 - 57
        if (asc >= 48 && asc <= 57 ) {
            return true;
        }
        return false;
    }

    private static boolean isUpper(int asc) {
        if (asc >= 65 && asc <= 90 ) {
            return true;
        }
        return false;
    }

    private static boolean isLower(int asc) {
        if (asc >= 97 && asc <= 122 ) {
            return true;
        }
        return false;
    }



    public static String decrypt(String str) {
        int n = str.length();
        char[] ans = new char[n];
        char[] old = str.toCharArray();
        for (int i = 0; i < n ; i++) {
            int cur = (int)old[i];
            if (isNum(cur)) {
//              数字减少 1，0 转换成 9，记住数字和字母的 asc 码很有必要
                cur--;
                if (cur < 48) {
                    cur = 57;
                }
                ans[i] = (char)cur;
                continue;
            }
            if (isUpper(cur)) {
//              大写要转换小写，前移一位 A 转换为 z
                cur += 31;
                if (cur < 97) {
                    cur = 122;
                }
                ans[i] = (char)cur;
                continue;
            }
            if (isLower(cur)) {
//              小要转换大写，前移一位a 转换为 Z
                cur -= 33;
                if (cur < 65) {
                    cur = 90;
                }
            }

            ans[i] = (char)cur;
        }

        return String.valueOf(ans);
    }
}
