package cn.zys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @program: road-health
 * @description: MyBatisConfig
 * @author: xiaozhang6666
 * @create: 2020-09-20 20:58
 **/
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@MapperScan("cn.zys.mapper")
public class MyBatisConfig {
    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driver;

    //整合连接池对象
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    //整合mybatis
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, PageInterceptor pageInterceptor) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("cn.zys.pojo");
//        PageInterceptor pageInterceptor = new PageInterceptor();
        //配置分页器
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        return factoryBean.getObject();
    }

    //创建事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //配置分页器
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        // 详见 com.github.pagehelper.page.PageParams
        Properties properties = new Properties();
//        p.setProperty("offsetAsPageNum", "false");
//        p.setProperty("rowBoundsWithCount", "false");
//        p.setProperty("reasonable", "false");
        // 设置数据库方言 ， 也可以不设置，会动态获取 高版本不设置会报错
        properties.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
