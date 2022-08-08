import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;

//@SpringBootApplication(scanBasePackages = "",exclude = ElasticsearchRestClientAutoConfiguration.class)
@MapperScan
@Slf4j
public class Application {

        public static void main(String[] args) {

            SpringApplication.run(Application.class, args);
        }


}
