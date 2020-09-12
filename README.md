# VisualBI_todo
Automation code repo for todo application

# Framework FAQs
1) The automation code repository is built using Maven and TestNG framework
2) Set the web driver path in globalsettings.properties file under the package src/main/resources
3) Execute the automation suite using testng.xml file
4) Under the src/test/java
  - 4.1 businesscomponents : Will hold all the Test Cases with repect to each Page
  - 4.2 pages:
    - Page object model is followed
    - Every screen will have a Page Class
    - The objects/locators/elements will be defined in the respective Page Class
    - Functionality related to the Pages will be performed through the respective Page Class
  - 4.3 reusable:
    - This package will have BaseDriver.java where the webdriver will be initialized based on the browser and also it will be assinged in ThreadLocal to support multi-threading
    - Operations.java will have the methods that can be reused across different test cases
  - 4.4 utilities:
    - This package will have the CoreUtil class where all the element related operations are done to bring more reusability and reduce boilerplate code
5) TestNG assertions are used for the validation points
6) Extent Report is also integrated to show step wise result where the result will be generated as ToDos.html

