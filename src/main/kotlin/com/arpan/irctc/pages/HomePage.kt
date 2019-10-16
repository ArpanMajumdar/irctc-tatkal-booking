package com.arpan.irctc.pages

import com.arpan.irctc.model.JourneyDetails
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.FluentWait
import java.time.Duration

class HomePage(private val driver: WebDriver) {

    private val sourceStationSelector = By.ByXPath("//input[@placeholder='From*']")
    private val destinationStationSelector = By.ByXPath("//input[@placeholder='To*']")
    private val dateSelector = By.ByXPath("//input[@placeholder='Journey Date(dd-mm-yyyy)*']")
    private val findTrainsSelector = By.ByXPath("//button[@label='Find Trains']")

    fun waitTillLoginIsSuccessful() {
        val wait = FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofMinutes(5))
            .pollingEvery(Duration.ofSeconds(15))
            .ignoring(NoSuchElementException::class.java)

        wait.until { _ ->
            driver.findElement(By.ByLinkText("Logout"))
        }
    }

    fun fillJourneyDetails(journeyDetails: JourneyDetails) {
        val sourceStationInput = driver.findElement(sourceStationSelector)
        val destinationStationInput = driver.findElement(destinationStationSelector)
        val dateInput = driver.findElement(dateSelector)
        val findTrainsButton = driver.findElement(findTrainsSelector)

        sourceStationInput.sendKeys(journeyDetails.sourceStation)
        destinationStationInput.sendKeys(journeyDetails.destinationStation)

        dateInput.clear()
        dateInput.sendKeys(journeyDetails.dateOfJourney)
        dateInput.sendKeys(Keys.TAB)
        findTrainsButton.click()
    }

}