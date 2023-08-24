package webDriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Fullscreen {

  //TODO !!! ТРЕБУЕТСЯ ВЕРСИЯ Google chrome 114 !!!

  Logger logger = LogManager.getLogger(Fullscreen.class);
  private WebDriver driver;
  private final String LOGIN = "vitaliy.permyakov@rtmis.ru";
  private final String PASSWORD = "MIHynStrY0vY";

  @BeforeEach
  public void before() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--start-fullscreen");
    driver = new ChromeDriver(options);
  }

  @AfterEach
  public void after() {
    if (driver != null) {
      driver.close();
      driver.quit();
    }
  }

  @Test
  public void taskThree() {
    driver.get("https://otus.ru");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    driver.findElement(By.cssSelector(".iOoJwQ")).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text'][@name='email']"))).sendKeys(LOGIN);
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys(PASSWORD);
    driver.findElement(By.cssSelector("button[type=button] div.sc-9a4spb-2"));
    Set<Cookie> cookie = driver.manage().getCookies();
    for (Cookie cookies : cookie) {
      logger.info("Name = " + cookies.getName() + ", " + "Value = " + cookies.getValue());

    }
  }
}
