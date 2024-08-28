package com.lukmanhidayah.catalog.config;

import javax.sql.DataSource;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Configuration
public class DataSourceProxyBeanProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof DataSource) {
      ProxyFactory proxyFactory = new ProxyFactory(bean);
      proxyFactory.setProxyTargetClass(false);
      proxyFactory.addAdvice(new ProxyDataSourceInterceptor((DataSource) bean));
      return proxyFactory.getProxy();
    }

    return bean;
  }

  private static class ProxyDataSourceInterceptor implements MethodInterceptor {
    private final DataSource dataSource;

    public ProxyDataSourceInterceptor(DataSource dataSource) {
      super();
      this.dataSource = ProxyDataSourceBuilder.create(dataSource).countQuery().logQueryBySlf4j(SLF4JLogLevel.INFO)
          .build();
    }

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
      Method proxyMethod = ReflectionUtils.findMethod(dataSource.getClass(), invocation.getMethod().getName());

      if (proxyMethod != null) {
        return proxyMethod.invoke(dataSource, invocation.getArguments());
      }
      return invocation.proceed();
    }

  }

}
