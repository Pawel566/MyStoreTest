package steps.AddAddress;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AddAddressSteps {

    private WebDriver driver;

    @Given("I'm login on my store page with email {string} and password {string}")
    public void i_m_login_on_my_store_page_with_email_and_password(String email, String password) {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/WebDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://mystore-testlab.coderslab.pl");

        WebElement signInButton = driver.findElement(By.cssSelector("#_desktop_user_info > div > a"));
        signInButton.click();

        driver.findElement(By.id("field-email")).sendKeys(email);
        driver.findElement(By.id("field-password")).sendKeys(password);
        driver.findElement(By.id("submit-login")).click();
    }


    @When("I click addresses button")
    public void iClickAddressesButton() {
        WebElement LoginButton = driver.findElement(By.cssSelector("#footer_account_list > li:nth-child(4) > a"));
        LoginButton.click();
    }

    @And("I click on {string}")
    public void iClickOn(String arg0) {
        WebElement CreateNewAddressButton = driver.findElement(By.cssSelector("#content > div.addresses-footer > a"));
        CreateNewAddressButton.click();
    }

    @And("I enter alias {string}, address {string}, city {string}, zip {string}, country {string}, phone {string}")
    public void iEnterAliasAddressCityZipCountryPhone(String alias, String address, String city, String zip, String country, String phone) {

        driver.findElement(By.id("field-alias")).sendKeys(alias);
        driver.findElement(By.id("field-address1")).sendKeys(address);
        driver.findElement(By.id("field-city")).sendKeys(city);
        driver.findElement(By.id("field-postcode")).sendKeys(zip);
        driver.findElement(By.id("field-id_country")).sendKeys(country);
        driver.findElement(By.cssSelector("#field-phone")).sendKeys(phone);
    }


    @Then("I send form and i can see message {string}")
    public void iSendFormAndICanSeeMessage(String expectedText) {
        WebElement saveButton = driver.findElement(By.cssSelector("#content > div > div > form > footer > button"));
        saveButton.click();
        WebElement alert = driver.findElement(By.cssSelector("#notifications > div > article"));
        Assert.assertTrue("Alert should be displayed", alert.isDisplayed());
        Assert.assertEquals(expectedText, alert.getText());
    }

    @And("Close browser")
    public void closeBrowser() {
        driver.quit();
    }
}
