/**
 * Description: Configuration for databases
 * @author Shaila Lewis
 * @since 04.12.26
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
    @Primary
    @Bean (name = "calgovDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.calgov") 
    public DataSource calgovDataDource(){
        return DataSourceBuilder.create().build();
    }
    
    @Bean (name = "commentsDataSource") 
    @ConfigurationProperties(prefix = "spring.datasource.comments")
    public DataSource commentsDataDource(){ 
        return DataSourceBuilder.create().build();
    }
}
