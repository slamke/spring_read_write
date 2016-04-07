package me.slamke.spring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 数据源操作
 */
public class DataSourceKeyHolder {

    private static Logger logger = LoggerFactory.getLogger(DataSourceKeyHolder.class);

    //线程本地环境
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    //设置数据源
    public static void setDataSourceKey(String dataSourceKey) {
        contextHolder.set(dataSourceKey);
    }
    //获取数据源
    public static String getDataSourceKey() {
        String key = contextHolder.get();
        logger.info("Thread:"+Thread.currentThread().getName()+" dataSource key is "+ key);
        return key;
    }
    //清除数据源
    public static void clearDataSourceKey() {
        contextHolder.remove();
    }
}
