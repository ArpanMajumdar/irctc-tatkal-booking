package com.arpan.irctc.config

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

object WebDriverManager {

    private val driverPath: String = WebDriverManager::class.java.getResource("/chromedriver").file

    val webDriver: WebDriver by lazy {
        System.setProperty("webdriver.chrome.driver", driverPath)
        val driver = ChromeDriver()
        driver.manage().deleteAllCookies()
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS)
        driver.manage().window().fullscreen()
        return@lazy driver
    }
}