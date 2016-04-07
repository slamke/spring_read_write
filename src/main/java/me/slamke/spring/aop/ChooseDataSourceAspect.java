package me.slamke.spring.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.slamke.spring.annotation.ChooseDataSource;
import me.slamke.spring.datasource.DataSourceKey;
import me.slamke.spring.util.DataSourceKeyHolder;
import me.slamke.spring.util.ReflectUtil;

/**
 * 执行dao方法之前的切面
 * 获取datasource对象之前往 @see DataSourceKeyHolder 中指定当前线程数据源路由的key
 */
@Component
@Aspect
public class ChooseDataSourceAspect {

    @Autowired
    private DataSourceKey dataSourcekey;

    public DataSourceKey getDataSourcekey() {
        return dataSourcekey;
    }

    public void setDataSourcekey(DataSourceKey dataSourcekey) {
        this.dataSourcekey = dataSourcekey;
    }

    protected static final ThreadLocal<String> preDatasourceHolder = new ThreadLocal<String>();


    @Pointcut("@annotation(me.slamke.spring.annotation.ChooseDataSource)")
    public void methodWithChooseAnnotation() {

    }


    /**
     * 根据@ChooseDataSource的属性值设置不同的dataSourceKey,以供DynamicDataSource
     */
    @Before("methodWithChooseAnnotation()")
    public void changeDataSourceBeforeMethodExecution(JoinPoint jp) {
        //拿到anotation中配置的数据源
        String resultDS = determineDatasource(jp);

        //没有配置实用默认数据源
        if (resultDS == null) {
            DataSourceKeyHolder.setDataSourceKey(null);
            return;
        }
        preDatasourceHolder.set(DataSourceKeyHolder.getDataSourceKey());
        //将数据源设置到数据源持有者
        if(resultDS.equals(ChooseDataSource.master)){
            DataSourceKeyHolder.setDataSourceKey(dataSourcekey.getMaster());
        }else if(resultDS.equals(ChooseDataSource.slave)){
            DataSourceKeyHolder.setDataSourceKey(dataSourcekey.getSlave());
        }

    }

    /**
     * 如果需要修改获取数据源的逻辑，请重写此方法
     */
    @SuppressWarnings("rawtypes")
    protected String determineDatasource(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Class targetClass = jp.getSignature().getDeclaringType();
        String dataSourceForTargetClass = resolveDataSourceFromClass(targetClass);
        String dataSourceForTargetMethod = resolveDataSourceFromMethod(
                targetClass, methodName);
        String resultDS = determinateDataSource(dataSourceForTargetClass,
                dataSourceForTargetMethod);
        return resultDS;
    }


    /**
     * 方法执行完毕以后，数据源切换回之前的数据源。
     * 比如foo()方法里面调用bar()，但是bar()另外一个数据源，
     * bar()执行时，切换到自己数据源，执行完以后，要切换到foo()所需要的数据源，以供
     * foo()继续执行。
     */
    @After("methodWithChooseAnnotation()")
    public void restoreDataSourceAfterMethodExecution() {
        DataSourceKeyHolder.setDataSourceKey(preDatasourceHolder.get());
        preDatasourceHolder.remove();
    }


    @SuppressWarnings("rawtypes")
    private String resolveDataSourceFromMethod(Class targetClass,
                                               String methodName) {
        Method m = ReflectUtil.findUniqueMethod(targetClass, methodName);
        if (m != null) {
            ChooseDataSource choDs = m.getAnnotation(ChooseDataSource.class);
            return resolveDataSourcename(choDs);
        }
        return null;
    }

    /**
     * 确定最终数据源，如果方法上设置有数据源，则以方法上的为准，如果方法上没有设置，则以类上的为准，如果类上没有设置，则使用默认数据源
     */
    private String determinateDataSource(String classDS, String methodDS) {
        // 两者必有一个不为null,如果两者都为Null，也会返回Null
        return methodDS == null ? classDS : methodDS;
    }


    private String resolveDataSourceFromClass(Class targetClass) {
        ChooseDataSource classAnnotation = (ChooseDataSource) targetClass
                .getAnnotation(ChooseDataSource.class);
        // 直接为整个类进行设置
        return null != classAnnotation ? resolveDataSourcename(classAnnotation)
                : null;
    }

    /**
     * 组装DataSource的名字
     */
    private String resolveDataSourcename(ChooseDataSource ds) {
        return ds == null ? null : ds.value();
    }

}
