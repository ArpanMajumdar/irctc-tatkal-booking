package com.arpan.irctc.pages

import com.arpan.irctc.model.JourneyDetails
import com.arpan.irctc.model.TrainDetails
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.Select
import java.time.Duration


class HomePage(private val driver: WebDriver) {

    private val logoutButton = By.ByLinkText("Logout")
    private val sourceStationSelector = By.ByXPath("//input[@placeholder='From*']")
    private val destinationStationSelector = By.ByXPath("//input[@placeholder='To*']")
    private val dateSelector = By.ByXPath("//input[@placeholder='Journey Date(dd-mm-yyyy)*']")
    private val findTrainsSelector = By.ByXPath("//button[@label='Find Trains']")
    private val trainCardsSelector = By.xpath("//div[contains(concat(' ', @class, ' '), ' train_avl_enq_box ')]")
    private val modifySearchButton = By.xpath("//button[text()='Modify Search']")
    private val trainClassDropdown = By.ByTagName("select")


    fun waitTillLoginIsSuccessful() {
        val wait = FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofMinutes(2))
            .pollingEvery(Duration.ofSeconds(5))
            .ignoring(NoSuchElementException::class.java)

        wait.until { _ ->
            driver.findElement(logoutButton)
        }
    }

    fun fillJourneyDetails(journeyDetails: JourneyDetails) {
        val sourceStationInput = driver.findElement(sourceStationSelector)
        val destinationStationInput = driver.findElement(destinationStationSelector)
        val dateInput = driver.findElement(dateSelector)
        val findTrainsButton = driver.findElement(findTrainsSelector)

        sourceStationInput.sendKeys(journeyDetails.sourceStation)
        destinationStationInput.sendKeys(journeyDetails.destinationStation)

        Thread.sleep(2000)

        dateInput.clear()
        dateInput.sendKeys(Keys.BACK_SPACE)
        dateInput.clear()
        dateInput.sendKeys(journeyDetails.dateOfJourney)
        dateInput.sendKeys(Keys.TAB)
        findTrainsButton.click()
    }

    fun selectTrain(trainDetails: TrainDetails) {
        waitForTrainSearchResults()

        val trainCards = driver.findElements(trainCardsSelector)
        val selectedCard =
            trainCards.filter { it.text.split("\n")[0].contains(trainDetails.trainNumber) }[0]
        val trainClassDropdown = Select(selectedCard.findElement(trainClassDropdown))
        trainClassDropdown.selectByVisibleText(trainDetails.travelClass)
        selectedCard.findElement(By.tagName("button")).click()
    }

    private fun waitForTrainSearchResults() {
        val wait = FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofMinutes(2))
            .pollingEvery(Duration.ofSeconds(5))
            .ignoring(NoSuchElementException::class.java)

        wait.until(ExpectedConditions.elementToBeClickable(modifySearchButton))
    }

}