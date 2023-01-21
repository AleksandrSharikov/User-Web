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
/*
   @Autowired
   private Environment env;

    @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource .setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }
 /*
 @Bean
   public LocalSessionFactoryBean getSessionFactory() {
      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
      factoryBean.setDataSource(getDataSource());
      
      Properties props=new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

      factoryBean.setHibernateProperties(props);
      factoryBean.setAnnotatedClasses(new Class[] {User.class});
      return factoryBean;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());
      return transactionManager;


   @Bean
   public LocalContainerEntityManagerFactoryBean emf(){
      Properties properties = new Properties();
    /*  props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

      properties.put("javax.persistence.jdbc.driver", env.getProperty("db.driver"));
      properties.put("javax.persistence.jdbc.url", "db.url");
      properties.put("javax.persistence.jdbc.user", "db.username"); //if needed
      properties.put("javax.persistence.jdbc.password", "db.password"); //if needed

      properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

      LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
     // emf.setPersistenceProvider(org.hibernate.jpa.HibernatePersistenceProvider);
      emf.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class); //If your using eclipse or change it to whatever you're using
      emf.setPackagesToScan("model"); //The packages to search for Entities, line required to avoid looking into the persistence.xml
      emf.setPersistenceUnitName("leaseManagementPU");
      emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      emf.setJpaProperties(properties);
      emf.setJtaDataSource(getDataSource());
      emf.setLoadTimeWeaver(new ReflectiveLoadTimeWeaver()); //required unless you know what your doing
      return emf;
   }*/
@Autowired
private Environment env;
 //  @PersistenceContext
  // public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor;

   @Bean//(destroyMethod = "close")
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

      /*properties.put(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH, env.getProperty(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH));
      properties.put(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE));
      properties.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
      properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

      properties.put(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "none");
      properties.put(AvailableSettings.USE_CLASS_ENHANCER, "false");*/
      properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
      System.out.println(8);
      return properties;
   }

}
