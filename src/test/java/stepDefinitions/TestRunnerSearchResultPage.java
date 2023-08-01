package stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/searchResultPageDataInDataTable.feature", glue= {"stepDefinitions"},
tags = "@DataInDataTable",
monochrome= true, dryRun = false,
plugin = {"pretty", "junit:target/JUnitReports/report.xml",
		"json:target/JSONReports/report.json",
		"html:target/HtmlReport/HtmlReports"}
		)
public class TestRunnerSearchResultPage {

}
