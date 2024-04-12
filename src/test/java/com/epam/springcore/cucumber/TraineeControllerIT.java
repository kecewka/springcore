package com.epam.springcore.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TraineeControllerIT {

    private String basePath = "http://localhost:8123";
    @Autowired
    private TestRestTemplate restTemplate;
    private String url;
    private String requestJson;
    private ResponseEntity<String> response;

    @Given("guest calls endpoint {string} with the following JSON request body:")
    public void theUserSendsAGETRequestToWithTheFollowingJSONRequest1(String path, String jsonRequest) {
        this.url = basePath + path;
        requestJson = jsonRequest;
    }

    @When("user sends a POST request in order to register")
    public void theUserSendsAPOSTRequestInOrderToRegister1() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    @Then("the response code should be {int}")
    public void theResponseStatusCodeShouldBe1(int statusCode) {
        System.out.println(response.toString());
        assertEquals(statusCode, response.getStatusCodeValue());
    }

}
