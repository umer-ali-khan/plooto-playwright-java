# Plooto Use case automation framework by using Microsoft Playwright - Java API
to run, open maven config in editor and
in command line provide
test -Denv=qa -Dbrowser=edge -Dtool=knockoutjs -f pom.xml

This framework is built using Microsoft Playwright which has auto scroll, auto wait for elements.
Variety of element locator strategies are used.
Fetching/scraping payment details data into POJOs and then serialize them to comapre against the json string.


The framework support multi browser configs : chrome, firefox, edge
The framework supports adding locators for any future tool like react
The framework has no explicit wait for element defined

Future:
Integrate it with Allure, Cucumber if needed
Paramterized tests for variety of clients to check the end to end test for any company and data
Integrate it with Selenium grid for parallel and cross browser testing
