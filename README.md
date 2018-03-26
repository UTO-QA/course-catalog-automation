# Class-Search-Automation
<p>
Introduction:<br>
This is a Behavioural Driven Development model for automation. The Testcases are provided in <b>Feature Files</b>.<br>
The feature files comprise of <br>
Scenario <br>
Given <br>
  When <br>
  Then <br>
</p>

<p>
Usage:<br>
Import the Class-Search Automation project and Open using Eclipse.<br>
Navigate to /Class-Search Automation/src/edu/asu/classsearch/testrunner/ClassSearchTestsWithReport.java <br><br>
Set the features variable to the feature file you want to execute. Eg. features="src/edu/asu/classearch/features/test.feature".<br>
<br>Now Set the format variable to the locaiton where the reports are to be generated. Eg. format = "pretty","json:C:/Users/_username_/Dropbox (ASU)/automation/Reports/cucumber.json" <br>
Run this using JUnit Configuration.

Edit the following lines in the project's pom.xml<br>
&lt;outputDirectory&gt;C:/Users/_username_/Dropbox (ASU)/automation/Reports/cucumber-html-reports&lt;/outputDirectory&gt;
&lt;cucumberOutput&gt;C:/Users/_username_/Dropbox (ASU)/automation/Reports/cucumber.json&lt;/cucumberOutput&gt;
</p>
<p>
Once the tests have been executed. Run the same file using mvn verify.<br>

The results are located at /Class-Search Automation/target/cucumber-html-reports/cucumber-html-reports
</p>
