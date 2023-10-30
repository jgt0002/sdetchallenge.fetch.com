package com.fetch.sdetchallenge.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UtilityComponents {
    WebDriver driver;

    public UtilityComponents(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By findByLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findByLocator));
    }

    public void printAndAcceptAlert() {
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }
}
