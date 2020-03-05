package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends PageFather {

    @FindBy(id = "header-login-modal")
    private WebElement miCuentaButton;
    @FindBy(id = "emailAddress")
    private WebElement emailField;
    @FindBy(className = "InputPassword__inputText__2IUUv")
    private WebElement passField;
    @FindBy(xpath = "//button[@class='Button__main__1NDc9 Button__green__1fhy5']")
    private WebElement iniciaButton;
    @FindBy(xpath = "//div[@class='fb-masthead-login__name re-design-cl__name']")
    private WebElement userWelcome;
    @FindBy(className = "Login__errorText__13IML")
    private WebElement loginError;
    @FindBy(className = "fb-filter-header__log-out")
    private WebElement logOutButton;
    @FindBy(xpath = "//div[@class='fb-masthead-login__name re-design-cl__name login-redesing_logout-box']")
    private WebElement iniciaLabel;
    @FindBy(xpath = "//p/a[text()='Regístrate']")
    private WebElement registerButton;
    @FindBy(id = "acc-alert-close")
    private WebElement closePopUpButton;
    @FindBy(id = "searchQuestionSolr")
    private WebElement searchBar;
    @FindBy(xpath = "//a[@class='fb-masthead__util-bar__link fb-masthead-search__box__button-search']")
    private WebElement searchButton;


    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public HomePage openLoginDialog() {
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("header-login-modal")));
        miCuentaButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("Modal__modalcontent__2yJz6")));
        return this;
    }

    public HomePage login(String username, String password) {
        emailField.click();
        emailField.clear();
        emailField.sendKeys(username);

        passField.click();
        passField.clear();
        passField.sendKeys(password);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class='Button__main__1NDc9 Button__green__1fhy5']")));
        iniciaButton.click();


        return this;
    }

    public HomePage logOut() {
        Actions hoverer = new Actions(webDriver);
        hoverer.moveToElement(userWelcome).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@class='fb-filter-header__log-out']")));
        logOutButton.click();
        return this;
    }

    public RegisterPage openRegisterForm() {
        openLoginDialog();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p/a[text()='Regístrate']")));
        registerButton.click();
        return new RegisterPage(webDriver);
    }

    public ResultsPage search(String searchQuery) {
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));
        /*
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("acc-alert-close")));
        closePopUpButton.click();

         */

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("searchFormSolr")));
        insertText(searchQuery, searchBar);
        searchButton.click();
        return new ResultsPage(webDriver);
    }


    public String getUserWelcome() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='fb-masthead-login__name re-design-cl__name']")));
        return userWelcome.getText();
    }

    public String getLoginErrorText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("Login__errorText__13IML")));
        return loginError.getText();
    }

    public String getMiCuentaButtonText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='fb-masthead-login__name re-design-cl__name login-redesing_logout-box']")));
        return iniciaLabel.getText();
    }


}
