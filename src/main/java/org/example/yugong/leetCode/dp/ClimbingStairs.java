package org.example.yugong.leetCode.dp;

/**
 * @author qiaobao
 * @since 2021-02-04
 */
public class ClimbingStairs {

    public int climbingStairs(int n) {
        if(n<2){
            return n;
        }


        int remainder = n/2;
        int module = n%2;
        int count = 0;
        int curRemainder = remainder;


        do{
            count = count +1;
            curRemainder = curRemainder -1;
            double pow = Math.pow(curRemainder, (remainder - curRemainder) * 2);
            count = (int) (count + pow);

        }while(curRemainder>0);

        count = count + count*module*2;
        count = count+1;
        return count;

    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbingStairs(3));
    }





}

