package pe.edu.upeu.api_orders.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pe.edu.upeu.api_orders.model.Order;

public class OrdersSteps {

    // 🎯 SOLUCIÓN: Usamos el cliente de pruebas de Spring para que inyecte el puerto dinámico de forma interna
    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<Order> response;

    @Given("the orders API is up")
    public void the_orders_api_is_up() {
        assertNotNull(restTemplate);
    }

    @When("I send a POST request to {string} with name {string} and price {double}")
    public void i_send_a_post_request(String path, String name, Double price) {
        Order order = new Order();
        order.setCustomer(name); 
        order.setAmount(price);
        
        // 🎯 SOLUCIÓN: Usamos solo el 'path' relativo (ej: "/api/orders"). 
        // TestRestTemplate le añade automáticamente el "http://localhost:PUERTO_ALEATORIO" sin chocar con Jenkins.
        response = restTemplate.postForEntity(path, order, Order.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }
}