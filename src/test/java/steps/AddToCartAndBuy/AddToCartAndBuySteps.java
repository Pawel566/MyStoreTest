package steps.AddToCartAndBuy;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddToCartAndBuySteps {

    private WebDriver driver;

    public void takeScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            System.err.println("Failed to save screenshot!");
        }
            
        }

        @Given("I'm login to buy a product with email {string} and password {string}")
        public void iMLoginToBuyAProductWithEmailEmailAndPassword (String email, String password){
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


        @When("I search product {string} and select it")
        public void iSearchProductAndSelectIt (String product){
            driver.findElement(By.cssSelector("#search_widget > form > input.ui-autocomplete-input")).sendKeys(product);
            WebElement myProduct = driver.findElement(By.xpath("/html/body/ul/li[1]/a/span"));
            myProduct.click();
        }

        @When("I choose size and i change quanity")
        public void iChooseSizeAndIChangeQuanityTo () throws InterruptedException {
            driver.findElement(By.id("group_1")).sendKeys("M");

            WebElement quanityProductsbutton = driver.findElement(By.cssSelector("#add-to-cart-or-refresh > div.product-add-to-cart.js-product-add-to-cart > div > div.qty > div > span.input-group-btn-vertical > button.btn.btn-touchspin.js-touchspin.bootstrap-touchspin-up"));
            for (int i = 0; i < 5; i++) {
                quanityProductsbutton.click();
                Thread.sleep(500);
            }
        }

        @And("I add to cart selected product and proceed to checkout")
        public void iAddToCartSelectedProductAndProceedToCheckout () {
            WebElement AddToCartButton = driver.findElement(By.cssSelector("#add-to-cart-or-refresh > div.product-add-to-cart.js-product-add-to-cart > div > div.add > button"));
            AddToCartButton.click();
            WebElement ProceedCheckoutButton = driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
            ProceedCheckoutButton.click();
            WebElement ProceedCheckoutSecondButton = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"));
            ProceedCheckoutSecondButton.click();
        }

        @When("I choose the shipping address")
        public void iChooseTheShippingAddress () {
            List<WebElement> addresses = driver.findElements(By.cssSelector(".js-address-item input[type='radio']"));
            if (!addresses.isEmpty()) {
                Random random = new Random();
                int addressListSize = random.nextInt(addresses.size());
                WebElement randomAddress = addresses.get(addressListSize);
                randomAddress.click();
                WebElement continueButton = driver.findElement(By.cssSelector("#checkout-addresses-step > div > div > form > div.clearfix > button"));
                continueButton.click();
            }

        }

        @And("I select delivery and payment method")
        public void iSelectDeliveryAndPaymentMethod () {
            WebElement deliveryMethod = driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/div/div[1]/div[1]"));
            deliveryMethod.click();
            WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button"));
            continueButton.click();
            WebElement paymentMethod = driver.findElement(By.xpath("//*[@id=\"payment-option-1-container\"]/span"));
            paymentMethod.click();
        }

        @Then("I agree to the terms of service and place order")
        public void iAgreeToTheTermsOfServiceAndPlaceOrder () {
            WebElement agreeCheckbox = driver.findElement(By.xpath("//*[@id=\"conditions_to_approve[terms-and-conditions]\"]"));
            agreeCheckbox.click();
            WebElement pleaceOrderButton = driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button"));
            pleaceOrderButton.click();
        }

        @And("I do screenshot my order")
        public void iDoScreenshotMyOrder () {
            takeScreenshot("OrderConfirmScreenshot");

        }
    }
