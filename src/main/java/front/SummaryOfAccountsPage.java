package front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SummaryOfAccountsPage extends BasePage{


    /** Account VIew */
    private  By btnAccView = By.xpath("//a[normalize-space()='Accounts Overview']");

    /** Succes Msg*/
    private By balanceMsg = By.xpath("//td[contains(text(),'*Balance includes deposits that may be subject to ')]");


    protected SummaryOfAccountsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void clickAccView() throws InterruptedException {
        this.click(btnAccView);
    }

    public String msgAccountBalance() throws InterruptedException {
        System.out.println("Se valida mensaje de cuenta creada: " + this.getText(balanceMsg));
        return this.getText(balanceMsg);
    }
}
