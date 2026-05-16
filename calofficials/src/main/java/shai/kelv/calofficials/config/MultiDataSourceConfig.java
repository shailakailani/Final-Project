/**
 * Description: Configuration for databases, sets calgov as the primary (default) one.
 * @author Shaila Lewis
 * @since 05.15.26
 */

package shai.kelv.calofficials.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;                     
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary; 

@Configuration
public class MultiDataSourceConfig {
    /**
     * Method creates calgov database connection factory
     * @return DataSource connection
     */
    @Primary
    @Bean (name = "calgovDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.calgov") 
    public DataSource calgovDataDource(){
        return DataSourceBuilder.create().build();
    }
    
    /**
     * Method creates comments database
     * @return DataSource connection
     */
    @Bean (name = "commentsDataSource") 
    @ConfigurationProperties(prefix = "spring.datasource.comments")
    public DataSource commentsDataDource(){ 
        return DataSourceBuilder.create().build();
    }
}
