package webDriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class Headless {

  //TODO !!! ТРЕБУЕТСЯ ВЕРСИЯ Google chrome 114 !!!

  private WebDriver driver;
  private void enterTextArea(WebElement element, String text){
    element.clear();
    element.sendKeys(text);
  }
  @BeforeEach
  public void before() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
//    options.addArguments("headless");
    options.addArguments("--headless");
//    options.addArguments("--start-fullscreen");
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
  public void testOne() {
    driver.get("https://duckduckgo.com/");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector("input[id='search_form_input_homepage']")).sendKeys("ОТУС");
    driver.findElement(By.cssSelector("#search_button_homepage")).click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", driver.findElement(By.cssSelector("#r1-0 a[data-testid='result-title-a'] span")).getText());
  }
}