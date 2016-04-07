# 基于Spring的数据读写分离
基于Spring实现了数据读写分离和读库的简单负载均衡

支持：
- 一个写库，多个读库
- 基于random的读库负载均衡

参考:
- http://wiki.xby1993.net/doku.php?id=spring:spring%E5%AE%9E%E7%8E%B0%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AF%BB%E5%86%99%E5
%88%86%E7%A6%BB
- https://github.com/igool/spring-jta-mybatis