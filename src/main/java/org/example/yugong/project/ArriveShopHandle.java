package org.example.yugong.project;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;

import java.io.File;
import java.util.List;

/**
 * @author qiaobao
 * @since --
 */
public class ArriveShopHandle {

    public static void main(String[] args) {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = "/Users/dasouche/Desktop/到店时间处理.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        ArriveShopListener arriveShopListener = new ArriveShopListener();
        EasyExcel.read(fileName, DemoData.class, arriveShopListener).sheet().doRead();
        List<String> handleList = arriveShopListener.handleList;
    }

}
