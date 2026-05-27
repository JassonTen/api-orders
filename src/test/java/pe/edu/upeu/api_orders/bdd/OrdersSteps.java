package pe.edu.upeu.api_orders.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pe.edu.upeu.api_orders.model.Order;

public class OrdersSteps {

    // 🎯 Captura el puerto aleatorio real que Spring Boot levantó en Jenkins
    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<Order> response;

    @Given("the orders API is up")
    public void the_orders_api_is_up() {
        // Pasa directo
    }

    @When("I send a POST request to {string} with name {string} and price {double}")
    public void i_send_a_post_request(String path, String name, Double price) {
        Order order = new Order();
        order.setCustomer(name); 
        order.setAmount(price);
        
        // 🎯 Construimos la URL usando el puerto dinámico real (ej: http://localhost:54321/api/orders)
        // Esto evita usar la clase TestRestTemplate y salta el puerto de Jenkins de raíz
        String url = "http://localhost:" + port + path;
        response = restTemplate.postForEntity(url, order, Order.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }
}