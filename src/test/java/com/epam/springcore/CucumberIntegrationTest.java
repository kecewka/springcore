package com.epam.springcore;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:/features/controller/", glue = {"com.epam.springcore.cucumber"})
public class CucumberIntegrationTest {
}
