package org.example.yugong.project;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author qiaobao
 * @since --
 */
public class ArriveShopListener implements ReadListener<DemoData> {

    List<String> handleList = Lists.newArrayList();

    public static String sqlFormat = "update fpl_expire_return_attribute set verify_arrive_shop_date = '%S' where order_id =%S and status = 1 and verify_arrive_shop_date is null ;";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        String format = String.format(sqlFormat, sdf.format(demoData.getArriveDate()), demoData.getId());
        System.out.println(format);
        handleList.add(format);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
