Markdown

# Java Selenium Factory Design Pattern

This repository demonstrates the implementation of the **Factory Design Pattern** in a Selenium Automation Framework. The primary goal of this project is to abstract the complexity of creating different `WebDriver` instances (Chrome, Firefox, Edge, etc.), making the framework highly scalable for **Cross-Browser Testing**.


## 🎯 Why Factory Pattern?

In a standard Selenium project, test classes often contain complex `if-else` or `switch` statements to handle different browser setups. The Factory Pattern solves this by:

1.  **Centralizing Logic**: Moving all browser initialization logic into a single Factory class.
2.  **Decoupling**: Test scripts do not need to know *how* a browser is created; they only ask for a specific browser type.
3.  **Scalability**: Adding a new browser (e.g., Safari or Headless Chrome) only requires changes in the Factory, not in existing tests.

## 🛠️ Tech Stack

* **Language**: Java (JDK 8+)
* **Automation**: Selenium WebDriver
* **Test Runner**: TestNG
* **Build Tool**: Maven
* **Design Pattern**: Factory Method Pattern

## 📂 Project Structure

```text
JavaSeleniumFactoryPattern
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── factory
│   │               ├── DriverFactory.java       # The Factory Class (Central Hub)
│   │               ├── BrowserType.java         # Enum for browser names (Optional)
│   │               └── DriverManager.java       # Interface/Abstract logic for Drivers
│   ├── test
│   │   └── java
│   │       └── com
│   │           └── tests
│   │               └── LoginTest.java           # Test Class utilizing the Factory
├── pom.xml                                      # Dependencies
└── testng.xml                                   # Suite Configuration
⚙️ How It Works
1. The Factory Class
The DriverFactory class contains a static method that accepts a browser name (String or Enum) and returns the corresponding WebDriver instance.
```

Java

public class DriverFactory {
    public static WebDriver getDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            // Setup Chrome options & driver
            return new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            // Setup Firefox options & driver
            return new FirefoxDriver();
        }
        throw new IllegalArgumentException("Browser not supported: " + browserName);
    }
}
2. The Test Class
The test script is clean and agnostic of the driver creation details.

Java

@Test
public void testGoogleSearch() {
    // We simply ask the factory for a driver
    WebDriver driver = DriverFactory.getDriver("chrome");
    
    driver.get("[https://google.com](https://google.com)");
    // ... validation logic
}
📦 Dependencies
Ensure your pom.xml includes:

selenium-java

testng

webdrivermanager (Recommended for managing browser binaries automatically)

🏃‍♂️ How to Run
Via Command Line (Maven)
You can pass the browser type as a system property (if your code is set up to read it):

Bash

mvn test -Dbrowser=chrome
Via TestNG XML
You can configure your testng.xml to pass parameters for parallel execution across browsers:

XML

<suite name="FactorySuite" parallel="tests" thread-count="2">
    <test name="Chrome Test">
        <parameter name="browser" value="chrome"/>
        <classes><class name="com.tests.LoginTest"/></classes>
    </test>
    <test name="Firefox Test">
        <parameter name="browser" value="firefox"/>
        <classes><class name="com.tests.LoginTest"/></classes>
    </test>
</suite>
🤝 Contributing
Fork the repository.

Create your feature branch.

Add support for a new browser type (e.g., Edge or Safari).

Submit a Pull Request.

📚 References
Factory Method Pattern - Refactoring Guru

Selenium WebDriver Documentation
