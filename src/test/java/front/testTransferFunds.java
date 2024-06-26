package front;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testTransferFunds {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/FrontEnd-Transfer-Test.html");
    static ExtentReports extent;

    private WebDriver driver;
    private WebDriverWait wait;
    private TransferFundsPage transferFundsPage;
    private String username = "test";
    private String password = "test";

    @BeforeAll
    public static void createReport() {
        System.out.println("<<< COMIENZAN LOS TEST DE Transferencia>>>");
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
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

    public void login() throws InterruptedException {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.putUserName(username);
        newAccountPage.putPass(password);
        newAccountPage.clickLogIn();
    }

    @Test
    @Tag("TransferFunds")
    @Tag("FRONTEND")
    @Tag("EXITOSO")
    public void transferFundsTest() throws InterruptedException {
        ExtentTest test = extent.createTest("Transfer Funds");
        test.log(Status.INFO, "Comienza el Test");

        login();

        transferFundsPage.clickBtnTransfer();
        String resultado = transferFundsPage.transferText();
        assertEquals("Transfer Funds", resultado);

        transferFundsPage.writeAmount("100");
        transferFundsPage.clickConfirmTransfer();
        String resultado2 = transferFundsPage.transferComplete();
        assertEquals("Transfer Complete!", resultado2);

        test.log(Status.PASS, "Transferencia completada con éxito");
    }

    @AfterEach
    public void cerrar() {
        transferFundsPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE Transferencia>>>");
    }
}
