package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends PageFather{
    @FindBy(id = "user")
    private WebElement nombreField;
    @FindBy(id = "apellidopaterno")
    private WebElement apellido1Field;
    @FindBy(id = "apellidomaterno")
    private WebElement apellido2Field;
    @FindBy(id = "mail")
    private WebElement mailField;
    @FindBy(id = "clave1")
    private WebElement clave1Field;
    @FindBy(id = "clave2")
    private WebElement clave2Field;
    private Select paisDrop;
    private Select cedulaDrop;
    @FindBy(id = "cedula_colombia")
    private WebElement cedulaColField;
    @FindBy(xpath = "//*[@id='tipodireccion_0' and @value='male']")
    private WebElement genderButton;
    private Select birthDayDrop;
    private Select birthMonthDrop;
    private Select birthYearDrop;
    @FindBy(id = "celular")
    private WebElement cellphoneField;
    @FindBy(id = "agreelegaleId")
    private WebElement termsButton;
    @FindBy(id = "boton_Ar")
    private WebElement saveButton;
    @FindBy(id = "mensajeCelVacio")
    private WebElement missingCelLabel;
    @FindBy(id = "mensajeClave2Incorrecto2")
    private WebElement uncoincidentPass;
    @FindBy(className = "mensajes")
    private WebElement invalidMail;

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public RegisterPage faultyRegister(String faultType, String[] fields){
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user")));
        switch (faultType){
            case "1":
                insertText(fields[0],nombreField);
                insertText(fields[1],apellido1Field);
                insertText(fields[2],apellido2Field);
                insertText(fields[3],mailField);
                insertText(fields[4],clave1Field);
                insertText(fields[5],clave2Field);
                insertText(fields[6],cedulaColField);
                //Fault type = "1" : No cellphone is written

                paisDrop = new Select(webDriver.findElement(By.id("status")));
                paisDrop.selectByVisibleText("Colombia");

                cedulaDrop = new Select(webDriver.findElement(By.id("cedula")));
                cedulaDrop.selectByVisibleText("Cédula de ciudadanía");
                
                birthDayDrop = new Select(webDriver.findElement(By.id("day")));
                birthDayDrop.selectByVisibleText(fields[7]);

                birthMonthDrop = new Select(webDriver.findElement(By.id("month")));
                birthMonthDrop.selectByVisibleText(fields[8]);

                birthYearDrop = new Select(webDriver.findElement(By.id("year")));
                birthYearDrop.selectByVisibleText(fields[9]);

                genderButton.click();
                termsButton.click();
                saveButton.click();

                break;
            case "2":
            case "3":
                insertText(fields[0],nombreField);
                insertText(fields[1],apellido1Field);
                insertText(fields[2],apellido2Field);
                insertText(fields[3],mailField);
                insertText(fields[4],clave1Field);
                insertText(fields[5],clave2Field);
                insertText(fields[6],cedulaColField);
                insertText(fields[10],cellphoneField);

                paisDrop = new Select(webDriver.findElement(By.id("status")));
                paisDrop.selectByVisibleText("Colombia");

                cedulaDrop = new Select(webDriver.findElement(By.id("cedula")));
                cedulaDrop.selectByVisibleText("Cédula de ciudadanía");

                birthDayDrop = new Select(webDriver.findElement(By.id("day")));
                birthDayDrop.selectByVisibleText(fields[7]);

                birthMonthDrop = new Select(webDriver.findElement(By.id("month")));
                birthMonthDrop.selectByVisibleText(fields[8]);

                birthYearDrop = new Select(webDriver.findElement(By.id("year")));
                birthYearDrop.selectByVisibleText(fields[9]);

                genderButton.click();
                termsButton.click();
                saveButton.click();
                break;

            default:
                return this;
        }
        return this;
    }

    public String validateFaultRegistration(String fault_type){
        switch (fault_type){
            case "1":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("mensajeCelVacio")));
                return missingCelLabel.getText();
            case "2":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("mensajeClave2Incorrecto2")));
                return uncoincidentPass.getText();
            case "3":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.className("mensajes")));
                return invalidMail.getText();
        }
        return "Invalid fault_type";
    }



}
