# BDD Cucumber Framework

![CI](https://github.com/keithbooccontridas-maker/BDDCucumberFramework/actions/workflows/ci.yml/badge.svg)

A Behavior-Driven Development (BDD) UI test automation framework for the [Para Bank](https://parabank.parasoft.com) demo application, built with Java, Selenium, Cucumber, and TestNG.

## Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 17 | Programming language |
| Maven | - | Build and dependency management |
| Cucumber | 7.20.1 | BDD framework |
| TestNG | 7.10.2 | Test runner and assertions |
| Selenium WebDriver | 4.49.0 | Browser automation |
| ExtentReports | 1.14.0 | HTML test reports |
| PicoContainer | 7.20.1 | Dependency injection |
| Apache Commons IO | 2.15.1 | File utilities (screenshots) |

## Features Covered

- **Login:** valid and negative scenarios
- **Open New Account:** checking and savings
- **Transfer Funds:** between the user's own accounts

## Project Structure

```
BDDCucumberFramework/
├── .github/workflows/ci.yml      # GitHub Actions CI
├── src/test/
│   ├── java/
│   │   ├── context/              # TestContext (shared driver and data)
│   │   ├── hooks/                # Setup, teardown, screenshot on failure
│   │   ├── pages/                # Page Objects
│   │   ├── runners/              # TestNG Cucumber runner
│   │   ├── stepDefinitions/      # LoginSteps, AccountSteps, TransferSteps
│   │   └── utils/                # ConfigReader, TestDataGenerator
│   └── resources/
│       ├── features/             # Login, OpenAccount, TransferFunds
│       ├── config.properties.example
│       └── extent.properties
└── pom.xml
```

## Prerequisites

- JDK 17
- Maven (or use the included `mvnw`)
- Google Chrome

## Setup

```bash
git clone https://github.com/keithbooccontridas-maker/BDDCucumberFramework.git
cd BDDCucumberFramework
copy src\test\resources\config.properties.example src\test\resources\config.properties
```

On macOS/Linux, use `cp` instead of `copy`.

## Running Tests

Run all tests:

```bash
mvn clean test
```

Run by tag:

```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="@negative"
mvn clean test -Dcucumber.filter.tags="@regression"
```

Run headless: set `headless=true` in `config.properties`.

## Reports

After a run, open `target/ExtentReport/ExtentReport.html` in a browser. Screenshots of failed scenarios are attached to the report.

## CI/CD

Every push triggers the GitHub Actions workflow, which runs the tests in headless Chrome and uploads the Extent report as an artifact.

## Design Practices

- Page Object Model (locators and actions in pages, assertions in step definitions)
- Explicit waits (`WebDriverWait`), no `Thread.sleep`
- Step definitions split by feature
- Dependency injection with PicoContainer
- Test data generated at runtime

## Reports Screenshot
![report.png](target/ExtentReport/Docs/report.png)

## Author

**Keith Contridas**
[LinkedIn](https://linkedin.com/in/keith-contridas) | [GitHub](https://github.com/keithbooccontridas-maker)

