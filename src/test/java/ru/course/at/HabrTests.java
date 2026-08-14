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
        WebElement footerMenu = driver.findElement(By.cssSelector("a[href='/ru/docs/help/']"));
        footerMenu.click();

        List<WebElement> changeLog = driver.findElements(By.cssSelector("a[href='/ru/docs/changelog/']"));
        Assertions.assertFalse(changeLog.isEmpty(), "Changelog не найден");
    }

    @Test
    public void searchTest() {
        WebElement searchIcon = driver.findElement(By.cssSelector("[class*='tm-header-user-menu__icon_search']"));
        searchIcon.click();

        WebElement search = driver.findElement(By.cssSelector("[class*='tm-input-text-decorated__input']"));
        String input = "Selenium";
        search.sendKeys(input);
        search.submit();
    }

    @Test
    public void menuTest() {
        WebElement menu = driver.findElement(By.cssSelector("[class*='tm-header__burger']"));
        menu.click();

        WebElement menuTest = driver.findElement(By.cssSelector("a[href='/ru/flows/quality_assurance/']"));
        menuTest.click();
    }
}
