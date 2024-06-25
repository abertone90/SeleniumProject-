package front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MonthlyActivityPage extends BasePage{


    /** Succes Msg*/
    private By balanceMsg = By.xpath("//td[contains(text(),'*Balance includes deposits that may be subject to ')]");
    protected MonthlyActivityPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public String msgAccountBalance() throws InterruptedException {
        System.out.println("Se valida mensaje de cuenta creada: " + this.getText(balanceMsg));
        return this.getText(balanceMsg);
    }
    private  By accountNumber= By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]");
    private By accDetails = By.xpath("//h1[normalize-space()='Account Details']");
    private By goBtn = By.xpath("//input[@value='Go']");
    public void activityPeriod() throws InterruptedException {
        By accountTypeLocator = By.id("month");
        int accOptionValue = 0;
        selectOptionFromDropdown(accountTypeLocator, accOptionValue);
        Thread.sleep(1000);
    }
    public void activityType() throws InterruptedException {
        By accountTypeLocator = By.id("transactionType");
        int accOptionValue = 0;
        selectOptionFromDropdown(accountTypeLocator, accOptionValue);
        Thread.sleep(1000);
    }
    public String accDetailsMsg() throws InterruptedException {
        System.out.println("Se valida mensaje de detalles de cuenta" + this.getText(accDetails));
        return this.getText(accDetails);
    }

    public void clickGo() throws InterruptedException {
        this.click(goBtn);
    }
    public void clickAccountNumber() throws InterruptedException {
        this.click(accountNumber);
        Thread.sleep(1000);
    }
}
