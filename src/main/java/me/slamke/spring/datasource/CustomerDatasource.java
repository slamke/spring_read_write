package me.slamke.spring.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import me.slamke.spring.util.DataSourceKeyHolder;

/**
 * 获取数据源（依赖于spring）
 */
public class CustomerDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceKeyHolder.getDataSourceKey();
    }
}
