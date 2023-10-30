package com.fetch.sdetchallenge.pageobjects;

import com.fetch.sdetchallenge.utility.UtilityComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WeighingPage extends UtilityComponents {
    WebDriver driver;

    public WeighingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "left_0")
    WebElement leftBowlInput0;

    @FindBy(id = "left_1")
    WebElement leftBowlInput1;

    @FindBy(id = "left_2")
    WebElement leftBowlInput2;

    @FindBy(id = "right_0")
    WebElement rightBowlInput0;

    @FindBy(id = "right_1")
    WebElement rightBowlInput1;

    @FindBy(id = "right_2")
    WebElement rightBowlInput2;

    @FindBy(xpath = "//button[text()='Reset']")
    WebElement resetButton;

    @FindBy(id = "weigh")
    WebElement weighButton;

    @FindBy(id = "coin_0")
    WebElement coin0;

    @FindBy(id = "coin_1")
    WebElement coin1;

    @FindBy(id = "coin_2")
    WebElement coin2;

    @FindBy(id = "coin_3")
    WebElement coin3;

    @FindBy(id = "coin_4")
    WebElement coin4;

    @FindBy(id = "coin_5")
    WebElement coin5;

    @FindBy(id = "coin_6")
    WebElement coin6;

    @FindBy(id = "coin_7")
    WebElement coin7;

    @FindBy(id = "coin_8")
    WebElement coin8;

    @FindBy(css = "div[class='game-info'] ol li")
    List<WebElement> weighingsList;

    By firstWeighResultsSelector = By.cssSelector("div[class='game-info'] ol li");
    By secondWeighResultsSelector = By.cssSelector("div[class='game-info'] ol li:nth-child(2)");

    public void goToURL() {
        driver.get("http://sdetchallenge.fetch.com/");
    }

    public void firstWeigh() {
        leftBowlInput0.sendKeys("0");
        leftBowlInput1.sendKeys("1");
        leftBowlInput2.sendKeys("2");

        rightBowlInput0.sendKeys("3");
        rightBowlInput1.sendKeys("4");
        rightBowlInput2.sendKeys("5");
        weighButton.click();
    }

    public String getResults(By findByLocator) {
        waitForElementToAppear(findByLocator);
        return driver.findElement(findByLocator).getText();
    }

    public void secondWeighIfEqual() {
        resetButton.click();
        leftBowlInput0.sendKeys("6");
        rightBowlInput0.sendKeys("7");
        weighButton.click();
    }

    public void secondWeighIfGreaterThan() {
        resetButton.click();
        leftBowlInput0.sendKeys("3");
        rightBowlInput0.sendKeys("4");
        weighButton.click();
    }

    public void secondWeighIfLessThan() {
        resetButton.click();
        leftBowlInput0.sendKeys("0");
        rightBowlInput0.sendKeys("1");
        weighButton.click();
    }

    public void printFakeBarNum(WebElement barNumber) {
        System.out.println("Gold bar number " + barNumber.getText() + " is fake!");
    }

    public void determineFakeBar() {
        String firstWeighResults = getResults(firstWeighResultsSelector);
        String secondWeighResults;

        switch (firstWeighResults) {
            case "[0,1,2] = [3,4,5]" -> {
                secondWeighIfEqual();
                secondWeighResults = getResults(secondWeighResultsSelector);
                switch (secondWeighResults) {
                    case "[6] < [7]" -> {
                        coin6.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin6);
                    }
                    case "[6] > [7]" -> {
                        coin7.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin7);
                    }
                    case "[6] = [7]" -> {
                        coin8.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin8);
                    }
                }
            }
            case "[0,1,2] > [3,4,5]" -> {
                secondWeighIfGreaterThan();
                secondWeighResults = getResults(secondWeighResultsSelector);
                switch (secondWeighResults) {
                    case "[3] < [4]" -> {
                        coin3.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin3);
                    }
                    case "[3] > [4]" -> {
                        coin4.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin4);
                    }
                    case "[3] = [4]" -> {
                        coin5.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin5);
                    }
                }
            }
            case "[0,1,2] < [3,4,5]" -> {
                secondWeighIfLessThan();
                secondWeighResults = getResults(secondWeighResultsSelector);
                switch (secondWeighResults) {
                    case "[0] < [1]" -> {
                        coin0.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin0);
                    }
                    case "[0] > [1]" -> {
                        coin1.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin1);
                    }
                    case "[0] = [1]" -> {
                        coin2.click();
                        printAndAcceptAlert();
                        printFakeBarNum(coin2);
                    }
                }
            }
        }
    }

    int i = 1;

    public void printAllWeighings() {
        for (WebElement e : weighingsList) {
            System.out.println("Weigh Results " + i + ": " + e.getText());
            i++;
        }
    }
}
