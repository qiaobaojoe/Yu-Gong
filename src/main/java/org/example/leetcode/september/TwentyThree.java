package org.example.leetcode.september;

/**
 * @author qiaobao
 * @since 2025/9/23
 */
public class TwentyThree {
    public int compareVersion(String version1, String version2) {
        // 比较版本号的大小，首先需要将字符串用'.'分割开，生成的String数组，代表每一个阶段的版本号，需要去除前导0转换成数字
        String[] lists1 = version1.split("\\.");
        String[] lists2 = version2.split("\\.");

        int maxLen = Math.max(lists1.length,lists2.length);
        int minLen = Math.min(lists1.length,lists2.length);

        for (int i = 0 ; i < minLen ; i++){
            // 先比较两个列表相同长度的部分
            // 这里两个修改段如果不是相等的，直接返回结果不需要再比较了
            int update1 = convertInt(lists1[i]);
            int update2 = convertInt(lists2[i]);
            if (update1 > update2){
                return 1;
            }
            if (update1 < update2){
                return -1;
            }
        }
        // 上面的相同大小部分的修改段，比较完毕都是相等的
        if (maxLen == minLen){
            // 修改段等长，直接返回相等
            return 0;
        }
        String[] longList;
        int ans;
        if (lists1.length > lists2.length){
            longList = lists1;
            ans = 1;
        }else{
            longList = lists2;
            ans = -1;
        }
        for(int j = minLen; j < maxLen; j++){
            if (convertInt(longList[j]) > 0 ){
                return ans;
            }
        }
        return 0;
    }

    private int convertInt(String updateStr){
        int n = updateStr.length();
        int subIndex = n;
        for (int i = 0 ; i < n; i++){
            if (updateStr.charAt(i) != '0'){
                subIndex = i;
                break;
            }
        }
        if (subIndex == n){
            return 0;
        }
        return Integer.parseInt(updateStr.substring(subIndex));
    }

    public static void main(String[] args) {
        TwentyThree twentyThree = new TwentyThree();
        System.out.println(twentyThree.compareVersion("11", "10"));


    }

}
