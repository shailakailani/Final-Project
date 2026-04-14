/**
 * Description: Configuration for comments.db
 * @author Shaila Lewis
 * @since 04.12.26
 */

package shai.kelv.calofficials.config;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "shai.kelv.calofficials.comments.repo",
    entityManagerFactoryRef = "commentsEntityManagerFactory",
    transactionManagerRef = "commentsTransactionManager"
)
public class CommentsJPAConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean commentsEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("commentsDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("shai.kelv.calofficials.comments.entity")
                .persistenceUnit("commentsPU")
                .build();
    }

    @Bean
    public PlatformTransactionManager commentsTransactionManager(
            @Qualifier("commentsEntityManagerFactory") EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }
}


  