package me.slamke.spring.datasource;

import java.util.List;
import java.util.Random;

public class DataSourceKey {
    private String writeKey;
    private List<String> readKeys;

    public String getWriteKey() {
        return writeKey;
    }

    public List<String> getReadKeys() {
        return readKeys;
    }

    public void setWriteKey(String writeKey) {
        this.writeKey = writeKey;
    }

    public String getMaster() {
        return writeKey;
    }

    public void setReadKeys(List<String> keys) {
        readKeys = keys;
    }

    public String getSlave() {
        // TODO 优化负载均衡算法
        int index = new Random().nextInt(readKeys.size());
        return readKeys.get(index);
    }
}
