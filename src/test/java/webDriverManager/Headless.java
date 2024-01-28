package webDriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Headless {
  //TODO  Рекомендуется очистить кэш и куки  браузера Google Chrome перед запуском АТ.
  //TODO Так же перед запуском АТ проверить, что браузер был ранее закрыт в развернутом режиме - кнопка "Развернуть" (главная панель сверху ("заголовок") программы, средняя кнопка).
  private WebDriver driver;

  @BeforeEach
  public void before() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--headless");
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
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector("input[name='q'")).sendKeys("ОТУС");
    driver.findElement(By.cssSelector("input[name='q'")).submit();
    Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...",
        driver.findElement(By.cssSelector("#r1-0 a[data-testid='result-title-a'] span")).getText());
  }
}