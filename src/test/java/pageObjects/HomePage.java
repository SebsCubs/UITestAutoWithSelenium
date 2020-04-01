package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends PageFather {

    private By miCuentaButton = By.id("header-login-modal");
    private By iniciaSesionPopUp = By.xpath("//div[@id='header-login-modal']//span[contains(text(),'Inicia sesi')]");
    private By registerButton = By.xpath("//div[@id='header-login-modal']/descendant::a[contains(text(),'Reg') and @href='/falabella-co/myaccount/register.jsp']");
    private By iniciaButton = By.xpath("//div[@id='header-login-modal']/descendant::button[@class='Button__main__1NDc9 Button__green__1fhy5']");
    private By userWelcome = By.xpath("//div[@id='header-login-modal']/descendant::div[contains(text(),'Bienvenid')]");
    private By logOutButton = By.xpath("//div[@id='header-login-modal']/descendant::a[contains(text(),'Cerrar sesi')]");
    private By iniciaLabel = By.xpath("//div[@id='header-login-modal']/descendant::div[contains(text(),'Inicia ses')]");
    private By closePopUpButton = By.id("acc-alert-close");
    private By searchButton = By.xpath("//form[@id='searchFormSolr']/child::a");
    private By passField = By.xpath("//input[@type='password']");
    private By emailField = By.id("emailAddress");
    private By searchBar = By.id("searchQuestionSolr");
    private By loginError = By.xpath("//div[@id='header-login-modal']/following::span[contains(text(),'E-mail o clave incorrecta.')]");


    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));
    }

    public HomePage openLoginDialog() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                miCuentaButton));
        webDriver.findElement(miCuentaButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(iniciaSesionPopUp));
        return this;
    }

    public HomePage login(String username, String password) {
        webDriver.findElement(miCuentaButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(iniciaSesionPopUp));

        insertText(username,webDriver.findElement(emailField));
        insertText(password,webDriver.findElement(passField));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(iniciaButton));
        webDriver.findElement(iniciaButton).click();

        return this;
    }

    public HomePage logOut() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(userWelcome));
        Actions hoverer = new Actions(webDriver);
        hoverer.moveToElement(webDriver.findElement(userWelcome)).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
        webDriver.findElement(logOutButton).click();
        return this;
    }

    public RegisterPage openRegisterForm() {
        openLoginDialog();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        webDriver.findElement(registerButton).click();
        return new RegisterPage(webDriver);
    }

    public ResultsPage search(String searchQuery) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(closePopUpButton));
            webDriver.findElement(closePopUpButton).click();
        }catch (ElementClickInterceptedException e){ }

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        insertText(searchQuery, webDriver.findElement(searchBar));
        webDriver.findElement(searchButton).click();
        return new ResultsPage(webDriver);
    }

    public ProductPage goToFirstResult(String searchQuery) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(userWelcome));
        ResultsPage resultsPage = search(searchQuery);
        return resultsPage.getFirstResult();
    }

    public ProductPage addFirstResultToCart(String searchQuery){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(userWelcome));
        ProductPage productPage = goToFirstResult(searchQuery);
        return  productPage.addItemToCart();
    }


    public String getUserWelcome() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(userWelcome));
        return webDriver.findElement(userWelcome).getText();
    }

    public String getLoginErrorText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(loginError));
        return webDriver.findElement(loginError).getText();
    }

    public String getMiCuentaButtonText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(iniciaLabel));
        return webDriver.findElement(iniciaLabel).getText();
    }


}
