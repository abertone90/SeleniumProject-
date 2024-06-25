package front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{


    /** Your Personal Details */
    private  By btnMyAccount = By.xpath("//a[normalize-space()='Register']");
    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By telephone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By userName = By.id("customer.username");
    private By pwd = By.id("customer.password");
    private By pwdConfirm = By.id("repeatedPassword");
    private  By btnRegister = By.xpath("(//input[@value='Register'])[1]");


    /** Succes Msg*/
    private By succesMsg = By.xpath("//p[contains(text(),'Your account was created successfully. You are now')]");


    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }



    public void clickCreateAcc() throws InterruptedException {
        this.click(btnMyAccount);
    }
    public void clickRegisterAcc() throws InterruptedException {
        this.click(btnRegister);
    }
    public void putName(String name) throws InterruptedException {
        this.sendText(name, firstName);
    }

    public void putLastName(String name) throws InterruptedException {
        this.sendText(name, lastName);
    }
    public void putAddress(String name) throws InterruptedException {
        this.sendText(name, address);
    }

    public void putCity(String name) throws InterruptedException {
        this.sendText(name, city);
    }
    public void putState(String name) throws InterruptedException {
        this.sendText(name, state);
    }
    public void putZipCode(String name) throws InterruptedException {
        this.sendText(name, zipCode);
    }
    public void putTelephone(String name) throws InterruptedException {
        this.sendText(name, telephone);
    }
    public void putSSN(String name) throws InterruptedException {
        this.sendText(name, ssn);
    }

    public void putUserName(String name) throws InterruptedException {
        this.sendText(name, userName);
    }


    public void putPass(String pass) throws InterruptedException {
        this.sendText(pass, pwd);
    }

    public void repitPass(String pass) throws InterruptedException {
        this.sendText(pass, pwdConfirm);
    }


    public void clickRegistrarse() throws InterruptedException {
        this.click(btnRegister);
        Thread.sleep(3000);
    }

    public String validaCuentaCreada() throws InterruptedException {
        System.out.println("Se valida mensaje de cuenta creada: " + this.getText(succesMsg));
        return this.getText(succesMsg);
    }
}
