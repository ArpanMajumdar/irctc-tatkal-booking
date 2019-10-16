package com.arpan.irctc.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class LoginPage(private val driver: WebDriver) {

    private val irctcUrl = "https://www.irctc.co.in/nget/train-search"
    private val loginButtonSelector = By.ById("loginText")
    private val userNameInputSelector = By.ById("userId")
    private val passwordInputSelector = By.ById("pwd")

    fun goToIrctcHome() {
        driver.get(irctcUrl)
    }

    fun doLogin(userName: String, password: String) {
        val loginButton = driver.findElement(loginButtonSelector)
        loginButton.click()

        Thread.sleep(1000)

        val userNameInput = driver.findElement(userNameInputSelector)
        userNameInput.sendKeys(userName)

        val passwordInput = driver.findElement(passwordInputSelector)
        passwordInput.sendKeys(password)
    }
}