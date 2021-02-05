package org.example.yugong.leetCode.dp;

/**
 * @author qiaobao
 * @since 2021-02-04
 */
public class ExampleOnZhiFu {
    int yiyuan = 1;
    int wuyuan = 5;
    int shiyiyuan = 11;


    public int minBillSize(int total) {
        if (total < 1) {
            return 99999999;
        }
        if (total < 5) {
            System.out.println(total+"张一块的");
            return total;
        }

        if (total == wuyuan) {
            System.out.println("1张"+wuyuan+"块的");
            return 1;
        }

        if (total == shiyiyuan) {
            System.out.println("1张"+shiyiyuan+"块的");
            return 1;
        }





        Integer minBillSizeWu = null;
        Integer minBillSizeShiYi = null;

        int cur = yiyuan;
        int min = minBillSize(total - yiyuan);
        System.out.println(total - yiyuan+"时，最少发费"+min);

        if (total > 5) {
             minBillSizeWu = minBillSize(total - wuyuan);
            System.out.println(total - wuyuan+"时，最少发费"+minBillSizeWu);
        }

        if (total > 11) {
             minBillSizeShiYi = minBillSize(total - shiyiyuan);
            System.out.println(total - shiyiyuan+"时，最少发费"+minBillSizeShiYi);
        }


        if (null != minBillSizeWu) {
            if (min > minBillSizeWu) {
                cur = wuyuan;
                min = minBillSizeWu;

            }
        }

        if (null != minBillSizeShiYi) {
            if (min > minBillSizeShiYi) {
                cur = shiyiyuan;
                min = minBillSizeShiYi;
            }
        }




        System.out.println("1张"+cur+"块的"+total);
        return min + 1;


    }

    public static void main(String[] args) {
        ExampleOnZhiFu exampleOnZhiFu = new ExampleOnZhiFu();

        System.out.println(exampleOnZhiFu.minBillSize(15));
    }


}
