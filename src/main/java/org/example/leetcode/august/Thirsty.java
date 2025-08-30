package org.example.leetcode.august;

/**
 * @author qiaobao
 * @since 2025/8/30
 */
public class Thirsty {
    public boolean isValidSudoku(char[][] board) {
        int[][] hashLine = new int[9][9];
        // 从左到右从上到下，对9个块进行标号
        int[][] hashBlock = new int[9][9];

        // 这里要利用缓存的空间局部性原理，只能按照行进行遍历
        for (int i = 0; i < 9; i++) {
            int[] hashRow = new int[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // 这个时候代表是数字
                    int cur = (int) board[i][j] - 49;
                    if (hashRow[cur] != 0) {
                        // 这里是判断每一行有没有重复的数字
                        return false;
                    }
                    if (hashLine[j][cur] != 0) {
                        // 这里是判断每一列有没有重复的数字
                        return false;
                    }
                    int index = calBlockIndex(i, j);
                    if (hashBlock[index][cur] != 0) {
                        // 这里是判断每一块有没有重复的数字
                        return false;
                    }
                    hashRow[cur] = 1;
                    hashLine[j][cur] = 1;
                    hashBlock[index][cur] = 1;
                }
            }
        }
        return true;
    }

    private int calBlockIndex(int i, int j) {
        if (i < 3 && j < 3) {
            return 0;
        }
        if (i < 6 && j < 3) {
            return 1;
        }
        if (i < 9 && j < 3) {
            return 2;
        }
        if (i < 3 && j < 6) {
            return 3;
        }
        if (i < 6 && j < 6) {
            return 4;
        }
        if (i < 9 && j < 6) {
            return 5;
        }
        if (i < 3 && j < 9) {
            return 6;
        }
        if (i < 6 && j < 9) {
            return 7;
        }

        return 8;
    }


    public static void main(String[] args) {
        Thirsty thirsty = new Thirsty();
        char[][] board = new char[9][9];
//        ["7",".",".",".","4",".",".",".","."]
        String one = "7...4....";
//        [".",".",".","8","6","5",".",".","."]
        String two = "...865...";
//        [".","1",".","2",".",".",".",".","."]
        String three = ".1.2.....";
//        [".",".",".",".",".","9",".",".","."]
        String four = ".....9...";
//        [".",".",".",".","5",".","5",".","."]
        String five = ".....5.5.";
//        [".",".",".",".",".",".",".",".","."]
        String six = ".........";
//        [".",".",".",".",".",".","2",".","."]
        String seven = "......2..";
        String eight = ".........";
        String nine = ".........";
        board[0] = one.toCharArray();
        board[1] = two.toCharArray();
        board[2] = three.toCharArray();
        board[3] = four.toCharArray();
        board[4] = five.toCharArray();
        board[5] = six.toCharArray();
        board[6] = seven.toCharArray();
        board[7] = eight.toCharArray();
        board[8] = nine.toCharArray();
        System.out.println(thirsty.isValidSudoku(board));
    }
}
