
package web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackageClasses = JavaConfig.class)
public class JavaConfig {

   private final ApplicationContext applicationContext;
   private final Environment env;

   public JavaConfig(ApplicationContext applicationContext, Environment env) {
      this.applicationContext = applicationContext;
      this.env = env;
   }


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

      LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
      emf.setJpaVendorAdapter(vendorAdaptor());
      emf.setDataSource(dataSource());
      emf.setPackagesToScan("model");
      emf.setJpaProperties(jpaHibernateProperties());
      System.out.println(7);

      return emf;
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

