package kr.co.tlab.ng.configuration;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created by JinPark on 2016-12-21.
 */
@Configuration
@EnableTransactionManagement
public class Persistence {

    private static final String jdbcDriver = "org.mariadb.jdbc.Driver";

    private static final String jdbcUrl = "jdbc:mariadb://192.168.99.100:3301/ngApp?autoReconnection=true";

    private static final String jdbcUserName = "ngApp";

    private static final String jdbcPwd = "55211";

    private static final int jdbcInitSize = 5;

    private static final int jdbcMaxActive = 5;

    private static final int jdbcMaxIdle = 5;

    private static final int jdbcMinIdle = 5;

    private Logger logger = LoggerFactory.getLogger(Persistence.class);

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        logger.info("ENTRY sessionFactory");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(this.getTomcatDataSource());
        sessionFactory.setPackagesToScan( "kr.co.tlab.ng.model.entity", "kr.co.tlab.ng.configuration.converter" );
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource getTomcatDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUserName);
        dataSource.setPassword(jdbcPwd);
        dataSource.setInitialSize(jdbcInitSize);
        dataSource.setMaxActive(jdbcMaxActive);
        dataSource.setMaxIdle(jdbcMaxIdle);
        dataSource.setMinIdle(jdbcMinIdle);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationInterval(3600000);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    /**
     *  @Repository 클래스들에 대해 자동으로 예외를 Spring의 DataAccessException으로 일괄 변환해준다.
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        logger.info("ENTRY hibernateProperties");
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "none");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
                setProperty("hibernate.connection.useUnicode", "true");
                setProperty("hibernate.connection.characterEncoding", "UTF-8");
                setProperty("hibernate.connection.charSet", "UTF-8");
            }
        };
    }
}
