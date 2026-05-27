package pe.edu.upeu.api_orders;

import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
    // Al quitarle (classes = ApiOrdersApplication.class), obligamos a Spring 
    // a buscar de forma automática la raíz @SpringBootApplication sin marearse.
}