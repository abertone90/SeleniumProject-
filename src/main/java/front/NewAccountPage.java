package front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends BasePage{

    /** Customer Account*/
    private By newAcc = By.xpath("//a[normalize-space()='Open New Account']");
    private By openNewAcc= By.cssSelector("input[value='Open New Account']");
    private By accSuccessMessage = By.xpath("//p[normalize-space()='Congratulations, your account is now open.']");

    /** openAccount*/
    public void clickNewAccount() throws InterruptedException {
        this.click(newAcc);
    }
    public void accountType() throws InterruptedException {
        By accountTypeLocator = By.xpath("//select[@id='type']");
        int savingsOptionValue = 1;

        // Llamar al método de BasePage para seleccionar la opción "SAVINGS"
        selectOptionFromDropdown(accountTypeLocator, savingsOptionValue);

    }
    public void setAccountConfirmed() throws InterruptedException {
        this.click(openNewAcc);
        Thread.sleep(1000);
    }

    /** Succes Msg*/
    public String succesMsgAcc() throws InterruptedException {
        String successMessage = getText(accSuccessMessage).trim();
        System.out.println("Se valida mensaje de cuenta creada: " + successMessage);
        return successMessage;
    }

    public NewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }




}
