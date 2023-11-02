package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin= {"html:/target/Destination/reports.html",
		"pretty"},features="src/test/java/features",glue="stepDefinitions",monochrome=true)
public class Runner extends AbstractTestNGCucumberTests{
	
}