package com.arpan.irctc

import com.arpan.irctc.config.WebDriverManager
import com.arpan.irctc.model.JourneyDetails
import com.arpan.irctc.pages.HomePage
import com.arpan.irctc.pages.LoginPage


fun main() {
    val driver = WebDriverManager.webDriver

    val loginPage = LoginPage(driver)
    val homePage = HomePage(driver)
    val journeyDetails = JourneyDetails(
        "BHOPAL  JN - BPL",
        "GWALIOR - GWL",
        "17-10-2019",
        "Sleeper (SL)"
    )
    val userName = System.getenv("USERNAME") ?: "username"
    val password = System.getenv("PASSWORD") ?: "password"

    loginPage.goToIrctcHome()
    loginPage.doLogin(userName, password)
    homePage.waitTillLoginIsSuccessful()
    println("Login successful")
    homePage.fillJourneyDetails(journeyDetails)

    driver.close()
}
