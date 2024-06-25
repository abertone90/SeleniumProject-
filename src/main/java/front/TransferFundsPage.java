package front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsPage extends BasePage{

    /** Transfer Funds*/
    private By btnTransfer = By.xpath("//a[normalize-space()='Transfer Funds']");
    private By transferText = By.xpath("//h1[normalize-space()='Transfer Funds']");
    private By amount = By.xpath("//input[@id='amount']");
    private By confirmTransfer = By.xpath("//input[@value='Transfer']");
    private By transferComplete = By.xpath("//h1[normalize-space()='Transfer Complete!']");

    /**
     * Constructor de la página base.
     *
     * @param driver El controlador del navegador web.
     * @param wait
     */
    protected TransferFundsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public void clickBtnTransfer() throws InterruptedException {
        this.click(btnTransfer);
        Thread.sleep(1000);
    }
    public String transferText() throws InterruptedException {
        System.out.println("Se valida mensaje de advertencia del deposito: " + this.getText(transferText));
        return this.getText(transferText);
    }
    public void writeAmount(String monto) throws InterruptedException {
        this.sendText(monto, amount);
    }
    public void clickConfirmTransfer() throws InterruptedException {
        this.click(confirmTransfer);
        Thread.sleep(1000);
    }
    public String transferComplete() throws InterruptedException {
        System.out.println("Se valida mensaje de transferencia completada: " + this.getText(transferComplete));
        return this.getText(transferComplete);
    }
    public void cmbxTransfer() throws InterruptedException {
        By accountTypeLocator = By.id("toAccountId");
        int savingsOptionValue = 1;
        // Llamar al método de BasePage para seleccionar la opción "SAVINGS"
        selectOptionFromDropdown(accountTypeLocator, savingsOptionValue);
        Thread.sleep(1000);
    }
}
