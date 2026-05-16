/**
 * Description: Program Entry Point
 * @author Shaila Lewis, Kelvin Myat
 * @date 04.09.26
 */

package shai.kelv.calofficials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"api", "api.service","shai.kelv.calofficials"})
public class CalOfficialsApplication {
	/**
	 * Program entry point
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CalOfficialsApplication.class, args);
	}

}
