/**
 * Description: Configuration for calgov.db
 * @author Shaila Lewis
 * @since 04.12.26
 */

package shai.kelv.calofficials.config;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "shai.kelv.calofficials.calgov.repo",
    entityManagerFactoryRef = "calgovEntityManagerFactory",
    transactionManagerRef = "calgovTransactionManager"
)
public class CalgovJPAConfig {
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean calgovEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("calgovDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("shai.kelv.calofficials.calgov.entity")
                .persistenceUnit("calgovPU")
                .build();
    }

    @Bean
    public PlatformTransactionManager calgovTransactionManager(
            @Qualifier("calgovEntityManagerFactory") EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }
}


  