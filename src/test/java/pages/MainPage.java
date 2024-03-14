package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver driver;
    @FindBy(xpath = "//*[@id=\"sample-report\"]/div/div/div[1]/div/a")
    private WebElement elementNull;

    public WebElement getElement() {
        return elementNull;
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
}
