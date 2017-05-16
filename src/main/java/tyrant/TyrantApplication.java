package tyrant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement // 支持事务
@ComponentScan(basePackages = {"tyrant"})
public class TyrantApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(TyrantApplication.class);


	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(TyrantApplication.class);
	}

	public static void main(String[] args) {
		logger.debug("start...");
		SpringApplication.run(TyrantApplication.class, args);
		logger.debug("run...");
	}
}
