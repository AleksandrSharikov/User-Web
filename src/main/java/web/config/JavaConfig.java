package web.config;

import model.User;
import org.hibernate.ejb.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackageClasses = JavaConfig.class)
public class JavaConfig {

@Autowired
private Environment env;
   //@PersistenceContext
  // public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor;

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      System.out.println(4);
      return dataSource;
   }

   @Bean
   public JpaTransactionManager jpaTransactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
      System.out.println(5);
      return transactionManager;
   }

   private HibernateJpaVendorAdapter vendorAdaptor() {
      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      vendorAdapter.setShowSql(true);
      System.out.println(6);
      return vendorAdapter;
   }


   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
      entityManagerFactoryBean.setDataSource(dataSource());
      entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
      entityManagerFactoryBean.setPackagesToScan("model");
      entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
      System.out.println(7);

      return entityManagerFactoryBean;
   }

   private Properties jpaHibernateProperties() {

      Properties properties = new Properties();


      properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
      System.out.println(8);
      return properties;
   }

}
