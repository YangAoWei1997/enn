package com.enn.commodity.synergistic.config.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;

@Repository 
@Configuration
@ConfigurationProperties(prefix = "primary.datasource.druid")
@MapperScan(basePackages = "com.enn.commodity.synergistic.dao", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataBaseConfig {

    /**
     * dao层的包路径
     */
    static final String PACKAGE = "com.enn.commodity.synergistic.dao";

    /**
     * mapper文件的相对路径
     */
    private static final String MAPPER_LOCATION = "/mapper/*.xml";
    
    private static final String CONFIG_LOCATIN = "/mybatis.cfg.xml";
    
    private static final String TYPE_ALIASES_PACKAGE = "com.enn.commodity.synergistic.entity";
    @Value("${primary.datasource.druid.filters}")
    private String filters;
    @Value("${primary.datasource.druid.url}")
    private String url;
    @Value("${primary.datasource.druid.username}")
    private String username;
    @Value("${primary.datasource.druid.password}")
    private String password;
    @Value("${primary.datasource.druid.driverClassName}")
    private String driverClassName;
    @Value("${primary.datasource.druid.initialSize}")
    private int initialSize;
    @Value("${primary.datasource.druid.minIdle}")
    private int minIdle;
    @Value("${primary.datasource.druid.maxActive}")
    private int maxActive;
    @Value("${primary.datasource.druid.maxWait}")
    private long maxWait;
    @Value("${primary.datasource.druid.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${primary.datasource.druid.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${primary.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${primary.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${primary.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${primary.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${primary.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${primary.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    // 主数据源使用@Primary注解进行标识
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "primary.datasource.base")
    public DataSource primaryDataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        // 监控统计拦截的filters
        druid.setFilters(filters);

        // 配置基本属性
        druid.setDriverClassName(driverClassName);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setUrl(url);
        //初始化时建立物理连接的个数
        druid.setInitialSize(initialSize);
        //最大连接池数量
        druid.setMaxActive(maxActive);
        //最小连接池数量
        druid.setMinIdle(minIdle);
        //获取连接时最大等待时间，单位毫秒。
        druid.setMaxWait(maxWait);
        //间隔多久进行一次检测，检测需要关闭的空闲连接
        druid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接在池中最小生存的时间
        druid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测连接是否有效的sql
        druid.setValidationQuery(validationQuery);
        //建议配置为true，不影响性能，并且保证安全性。
        druid.setTestWhileIdle(testWhileIdle);
        //申请连接时执行validationQuery检测连接是否有效
        druid.setTestOnBorrow(testOnBorrow);
        druid.setTestOnReturn(testOnReturn);
        //是否缓存preparedStatement，也就是PSCache，oracle设为true，mysql设为false。分库分表较多推荐设置为false
        druid.setPoolPreparedStatements(poolPreparedStatements);
        // 打开PSCache时，指定每个连接上PSCache的大小
        druid.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        return druid;
//    	 return DataSourceBuilder.create().build();
    }

    // 创建该数据源的事务管理
    @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(primaryDataSource());
    }

    // 创建Mybatis的连接会话工厂实例
    @Primary
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primaryDataSource);  // 设置数据源bean
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PrimaryDataBaseConfig.MAPPER_LOCATION));  // 设置mapper文件路径
        sessionFactory.setConfigLocation(new ClassPathResource(PrimaryDataBaseConfig.CONFIG_LOCATIN));
        sessionFactory.setTypeAliasesPackage(PrimaryDataBaseConfig.TYPE_ALIASES_PACKAGE);
        return sessionFactory.getObject();
    }
}
