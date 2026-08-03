package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class HabrTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://habr.com/ru/feed/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void changeLogTest() {
        WebElement footerMenu = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div/div/div[3]/div/ul/li[1]/a"));
        footerMenu.click();

        List<WebElement> changeLog = driver.findElements(By.cssSelector("a[href='/ru/docs/changelog/'"));
        Assertions.assertFalse(changeLog.isEmpty(), "Changelog не найден");
    }

    @Test
    public void searchTest() {
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div/div/div[2]/a[1]"));
        searchIcon.click();

        WebElement search = driver.findElement(By.cssSelector("[class*='tm-search__input tm-input-text-decorated__input']"));
        String input = "Selenium";
        search.sendKeys(input);
        search.submit();
    }

    @Test
    public void menuTest() {
        WebElement menu = driver.findElement(By.cssSelector("[class*='burger-button tm-header__button tm-header__burger']"));
        menu.click();

        WebElement menuTest = driver.findElement(By.cssSelector("#app > div > div.tm-layout > div.expanded-menu > div > div > div.navigation-wrapper > nav > a:nth-child(9) > span"));
        menuTest.click();
    }
}
