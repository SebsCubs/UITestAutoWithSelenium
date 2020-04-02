package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends PageFather {
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
    @FindBy(id = "cedula_colombia")
    private WebElement cedulaColField;
    @FindBy(xpath = "//*[@id='tipodireccion_0' and @value='male']")
    private WebElement genderButton;
    @FindBy(id = "celular")
    private WebElement cellphoneField;
    @FindBy(id = "agreelegaleId")
    private WebElement termsButton;
    @FindBy(id = "boton_Ar")
    private WebElement saveButton;

    private By missingCelLabel = By.id("mensajeCelVacio");
    private By uncoincidentPass = By.id("mensajeClave2Incorrecto2");
    private By invalidMail = By.className("mensajes");


    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
    }

    public RegisterPage faultyRegister(String faultType, String[] fields) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user")));
        fillRegisterForm(fields,faultType);
        return this;
    }

    public String getFaultLabelText(String fault_type) {
        switch (fault_type) {
            case "1":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(missingCelLabel));
                return webDriver.findElement(missingCelLabel).getText();
            case "2":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(uncoincidentPass));
                return webDriver.findElement(uncoincidentPass).getText();
            case "3":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(invalidMail));
                return webDriver.findElement(invalidMail).getText();
        }
        return "Invalid fault_type";
    }

    private void fillRegisterForm(String[] fields, String fault_type){
        insertText(fields[0], nombreField);
        insertText(fields[1], apellido1Field);
        insertText(fields[2], apellido2Field);
        insertText(fields[3], mailField);
        insertText(fields[4], clave1Field);
        insertText(fields[5], clave2Field);
        insertText(fields[6], cedulaColField);
        if(!fault_type.equals("1")){
            insertText(fields[10], cellphoneField);
        }
        Select paisDrop = new Select(webDriver.findElement(By.id("status")));
        paisDrop.selectByVisibleText("Colombia");

        Select cedulaDrop = new Select(webDriver.findElement(By.id("cedula")));
        cedulaDrop.selectByVisibleText("Cédula de ciudadanía");

        Select birthDayDrop = new Select(webDriver.findElement(By.id("day")));
        birthDayDrop.selectByVisibleText(fields[7]);

        Select birthMonthDrop = new Select(webDriver.findElement(By.id("month")));
        birthMonthDrop.selectByVisibleText(fields[8]);

        Select birthYearDrop = new Select(webDriver.findElement(By.id("year")));
        birthYearDrop.selectByVisibleText(fields[9]);

        genderButton.click();
        termsButton.click();
        saveButton.click();
    }


}
