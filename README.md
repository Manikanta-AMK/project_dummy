# GBHSR Test Automation Framework
 
Cucumber + Selenium + Java + Maven test automation framework for the ODPP application.
 
---
 
# Overview
 
This framework is designed to automate end-to-end testing of the ODPP web application using:
 
- Java 24
- Selenium WebDriver
- Cucumber BDD
- Maven
- Page Object Model (POM)
- Extent Reports
- VS Code / Eclipse
 
The framework provides:
 
- Reusable Page Objects
- Centralized Configuration Management
- Screenshot Capture on Failure
- Detailed Extent Reporting
- Common Utility Functions
- Tag-Based Test Execution
- Cross-Browser Support
 
---
 
# Prerequisites
 
Install the following software before executing the framework:
 
| Software | Version |
|-----------|-----------|
| Java JDK | 24 |
| Apache Maven | 3.9+ |
| Microsoft Edge | Latest |
| VS Code | Latest |
| Git | Latest |
 
---
 
# Recommended VS Code Extensions
 
## Java Extensions
 
- Extension Pack for Java
- Language Support for Java by Red Hat
- Maven for Java
 
## Cucumber Extensions
 
- Cucumber (Gherkin) Full Support
 
---
 
# Project Structure
 
```text
src
├── test
│   ├── java
│   │   ├── BaseFactory
│   │   ├── CommonLibraries
│   │   ├── Hooks
│   │   ├── PageObjects
│   │   ├── StepDefinitions
│   │   ├── TestRunner
│   │   └── Utilities
│   │
│   └── resources
│       ├── Features
│       ├── Config
│       └── TestData
│
├── Reports
├── Screenshots
└── pom.xml
```
 
---
 
# Running Tests
 
## Execute All Tests
 
```cmd
mvn test
```
 
## Execute Tagged Scenarios
 
```cmd
mvn test "-Dcucumber.filter.tags=@Signin"
```
 
Examples:
 
```cmd
mvn test -Dcucumber.filter.tags=@Smoke
mvn test -Dcucumber.filter.tags=@Signin
mvn test -Dcucumber.filter.tags=@Regression
```
 
## Execute Multiple Tags
 
```cmd
mvn test -Dcucumber.filter.tags="@Smoke and @Signin"
```
 
## Clean and Execute
 
```cmd
mvn clean test
```
 
## PowerShell Users
 
```powershell
mvn test "-Dcucumber.filter.tags=@Signin"
mvn clean test "-Dcucumber.filter.tags=@Smoke"
```
 
---
 
# Reports
 
## Extent Report
 
Generated automatically after execution.
 
Example location:
 
```text
Reports/ExtentReport.html
```
 
---
 
# Screenshots
 
Screenshots are captured automatically for failed scenarios.
 
```text
Screenshots/
```
 
---
 
# Useful Commands
 
```cmd
java -version
mvn -version
mvn clean install
```
 
---
 
# Best Practices
 
- Maintain locators only in Page Objects.
- Use explicit waits instead of Thread.sleep().
- Store test data in configuration files.
- Avoid hard-coded values.
- Reuse common library methods.
- Use meaningful tags for execution control.
 
---
 
# Author
 
ODPP Automation Team
 
Version: 1.0