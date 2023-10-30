package com.fetch.sdetchallenge.test;

import com.fetch.sdetchallenge.pageobjects.WeighingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IdentifyFakeBarTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/justi/Documents/Dependencies/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        WeighingPage weighingPage = new WeighingPage(driver);
        weighingPage.goToURL();
        weighingPage.firstWeigh();

        weighingPage.determineFakeBar();
        weighingPage.printAllWeighings();
    }
}
