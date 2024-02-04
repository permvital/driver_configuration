package webDriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Fullscreen {

  private Logger logger = LogManager.getLogger(Fullscreen.class);
  private WebDriver driver;
  private final String LOGIN = "permvpva@yandex.ru";
  private final String PASSWORD = "fdgkj8w9Ds!";

  @BeforeAll
  public static void beforeAll(){
    WebDriverManager.chromedriver().setup();
  }

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
  public void testThree() {
    driver.get("https://otus.ru");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.manage().deleteAllCookies();
    driver.findElement(new By.ByXPath("//button[text()='Войти']")).click();
    WebElement loginInput = driver.findElement(new By.ByXPath("//input[@name='email']"));
    loginInput.clear();
    loginInput.sendKeys(LOGIN);
    WebElement passInput = driver.findElement(new By.ByXPath("//input[@type='password']"));
    passInput.clear();
    passInput.sendKeys(PASSWORD);
    driver.findElement(new By.ByXPath("//button/div[text()='Войти']")).click();
    logger.info(String.format("Выводим в логи Cookies с otus.ru %s", driver.manage().getCookies().toString()));
  }
}

//      @Test
//      public void testThree() {
//        driver.get("https://otus.ru");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.findElement(By.cssSelector(".iOoJwQ")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='email']"))).sendKeys(LOGIN);
////    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text'][@name='email']"))).sendKeys(LOGIN);
//        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(PASSWORD);
//        driver.findElement(By.cssSelector("button[type=button] div.sc-9a4spb-2"));
//        Set<Cookie> cookie = driver.manage().getCookies();
//        for (Cookie cookies : cookie) {
//          logger.info("Name = " + cookies.getName() + ", " + "Value = " + cookies.getValue());
//    }
//  }
//}
