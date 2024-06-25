package front;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testTransferFunds {
    private WebDriver driver;
    private WebDriverWait wait;
    private TransferFundsPage transferFundsPage;
    private String username = "test";
    private String password = "test";

    @BeforeAll
    public static void createReport() {
        System.out.println("<<< COMIENZAN LOS TEST DE Transferencia>>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        transferFundsPage = new TransferFundsPage(driver, wait);


        transferFundsPage.setup();
        transferFundsPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }
    public void Login() throws InterruptedException {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);

        newAccountPage.putUserName(username);
        newAccountPage.putPass(password);
        newAccountPage.clickLogIn();

    }

    @Test
    @Tag("SummaryOfAccountsView")
    @Tag("EXITOSO")
    public void accountSumary() throws InterruptedException {
        Login();

        transferFundsPage.clickBtnTransfer();
        String resultado = transferFundsPage.transferText();
        assertEquals("Transfer Funds", resultado);
        transferFundsPage.writeAmount("100");
        transferFundsPage.clickConfirmTransfer();
        String resultado2 = transferFundsPage.transferComplete();
        assertEquals("Transfer Complete!", resultado2);


    }

    @AfterEach
    public void cerrar() {
        transferFundsPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE Transferencia>>>");
    }
}