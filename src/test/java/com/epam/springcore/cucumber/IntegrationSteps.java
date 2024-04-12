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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationSteps {

    //@Value("${app.test.base-path}")
    private String basePath = "http://localhost:8123";

    @Autowired
    private TestRestTemplate restTemplate;
    private ResponseEntity<String> response;
    private String requestJson;
    private String url;
    private String fullPath;
    private String requestBody;
    private String newToken;

    @Given("user calls endpoint {string} with the following JSON request body:")
    public void theUserSendsAGETRequestToWithTheFollowingJSONRequest(String path, String jsonRequest) {
        this.url = basePath + path;
        requestJson = jsonRequest;
    }

    @When("the user sends a POST request")
    public void theUserPerformsAPOSTRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + newToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        System.out.println(response.toString());
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    @Given("trainee calls endpoint {string} with the following JSON request body:")
    public void givenClientCallsEndpointWithRequestBody(String endpoint, String jsonRequest) {
        this.fullPath = "http://localhost:8123/" + endpoint;
        this.requestBody = jsonRequest;
    }

    @When("the trainee sends a POST request in order to authenticate")
    public void theTraineeSendsAPOSTRequestInOrderToAuthenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForEntity(fullPath,
                    entity, String.class);
            newToken = response.getBody().trim();
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getStatusCode());
        }
    }

    @Then("the authentication response status code should be {int}")
    public void checkAuthResponseStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCodeValue());
    }


}
